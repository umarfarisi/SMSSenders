package learn.com.smssender.support.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * @author Muhammad Umar Farisi
 * @created 07/05/2017
 */
public class ContentProviderUtils {

    public static Cursor getSMSInbox(ContentResolver resolver){
        Uri smsUri = Uri.parse("content://sms/inbox");
        return resolver.query(smsUri,new String[]{"_id","address"},null,null,null);
    }

    public static Cursor getContactInfo(ContentResolver resolver, String number){
        Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        return resolver.query(contactUri
                ,new String[]{ContactsContract.Contacts.DISPLAY_NAME}
                ,ContactsContract.CommonDataKinds.Phone.NUMBER +" = ?"
                ,new String[]{number},null);
    }

}
