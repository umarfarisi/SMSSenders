package learn.com.smssender.dataaccess;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import learn.com.smssender.dataaccess.callback.SenderDACallBack;
import learn.com.smssender.model.Sender;
import learn.com.smssender.utils.GlobalVariable;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class SenderDataAccess {
    public void getAllSenders(SenderDACallBack<List<Sender>> getAllSendersCallback){

        ContentResolver connResolver = GlobalVariable.APPLICATION_CONTEXT.getContentResolver();

        Uri smsUri = Uri.parse("content://sms/inbox");
        Cursor smsCursor = connResolver.query(smsUri,new String[]{"_id","address"},null,null,null);

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

        List<Sender> data = new ArrayList<>();

        for(Map.Entry<String, Integer> smsDatum: smsData.entrySet()){
            Cursor contactCursor = connResolver.query(contactUri
                    ,new String[]{ContactsContract.Contacts.DISPLAY_NAME}
                    ,ContactsContract.CommonDataKinds.Phone.NUMBER +" = ?"
                    ,new String[]{smsDatum.getKey()},null);
            if(contactCursor.moveToNext()){
                data.add(new Sender(contactCursor.getString(0),smsDatum.getKey(),smsDatum.getValue()));
            }

        }

        getAllSendersCallback.onSuccess(data);
    }
}
