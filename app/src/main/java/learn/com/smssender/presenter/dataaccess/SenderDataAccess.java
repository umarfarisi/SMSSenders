package learn.com.smssender.presenter.dataaccess;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learn.com.smssender.presenter.dataaccess.callback.SenderDACallBack;
import learn.com.smssender.model.Sender;
import learn.com.smssender.support.utils.ContentProviderUtils;
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

                Cursor smsCursor = ContentProviderUtils.getSMSInbox(resolver);

                Map<String, Integer> smsData = new HashMap<>();
                while(smsCursor.moveToNext()){
                    String senderName = smsCursor.getString(1);
                    if(smsData.containsKey(senderName)){
                        smsData.put(senderName,1+smsData.get(senderName));
                    }else {
                        smsData.put(senderName, 1);
                    }
                }

                List<Sender> senders = new ArrayList<>();

                for(Map.Entry<String, Integer> smsDatum: smsData.entrySet()){
                    Cursor contactCursor = ContentProviderUtils.getContactInfo(resolver,smsDatum.getKey());
                    if(contactCursor.moveToNext()){
                        senders.add(new Sender(contactCursor.getString(0),smsDatum.getKey(),smsDatum.getValue()));
                    }else{
                        senders.add(new Sender(smsDatum.getKey(),smsDatum.getKey(),smsDatum.getValue()));
                    }

                }

                Collections.sort(senders, new Comparator<Sender>() {
                    @Override
                    public int compare(Sender sender1, Sender sender2) {
                        return sender2.getNumberOfSending() - sender1.getNumberOfSending();
                    }
                });

                return senders;
            }

            @Override
            protected void onPostExecute(List<Sender> senders) {
                getAllSendersCallback.onSuccess(senders);
            }

        }.execute();
    }
}
