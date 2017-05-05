package learn.com.smssender.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class IncomingSMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d("MASUKKKKK","NOMOR: 11111111");

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                final Object[] pdus = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdus.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    String senderNumber = currentMessage.getDisplayOriginatingAddress();

                    Log.d("MASUKKKKK","NOMOR: "+senderNumber);

                }
            }
        } catch (Exception e) {
            Log.d("MASUKKKKK","ERRROR "+e.getMessage());
        }
    }

}
