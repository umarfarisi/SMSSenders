package learn.com.smssender.presenter.dataaccess;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learn.com.smssender.presenter.dataaccess.callback.SenderDACallBack;
import learn.com.smssender.model.Sender;
import learn.com.smssender.support.utils.GlobalVariable;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class SenderDataAccess {
    public void getAllSenders(final SenderDACallBack<List<Sender>> getAllSendersCallback){

        new AsyncTask<Void,Void,List<Sender>>(){

            private ContentResolver resolver;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                resolver = GlobalVariable.APPLICATION_CONTEXT.getContentResolver();
            }

            @Override
            protected List<Sender> doInBackground(Void... params) {

                Uri smsUri = Uri.parse("content://sms/inbox");
                Cursor smsCursor = resolver.query(smsUri,new String[]{"_id","address"},null,null,null);

                Map<String, Integer> smsData = new HashMap<>();

                while(smsCursor.moveToNext()){
                    String senderName = smsCursor.getString(1);
                    if(smsData.containsKey(senderName)){
                        smsData.put(senderName,1+smsData.get(senderName));
                    }else {
                        smsData.put(senderName, 1);
                    }
                }

                Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                List<Sender> senders = new ArrayList<>();

                for(Map.Entry<String, Integer> smsDatum: smsData.entrySet()){
                    Cursor contactCursor = resolver.query(contactUri
                            ,new String[]{ContactsContract.Contacts.DISPLAY_NAME}
                            ,ContactsContract.CommonDataKinds.Phone.NUMBER +" = ?"
                            ,new String[]{smsDatum.getKey()},null);
                    if(contactCursor.moveToNext()){
                        senders.add(new Sender(contactCursor.getString(0),smsDatum.getKey(),smsDatum.getValue()));
                    }else{
                        senders.add(new Sender(smsDatum.getKey(),smsDatum.getKey(),smsDatum.getValue()));
                    }

                }
                return senders;
            }

            @Override
            protected void onPostExecute(List<Sender> senders) {
                getAllSendersCallback.onSuccess(senders);
            }

        }.execute();
    }
}
