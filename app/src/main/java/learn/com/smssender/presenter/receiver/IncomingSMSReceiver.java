package learn.com.smssender.presenter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import org.greenrobot.eventbus.EventBus;

import learn.com.smssender.presenter.receiver.event.IncomingSMSEvent;

/**
 * @author Muhammad Umar Farisi
 * @created 05/05/2017
 */
public class IncomingSMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            final Object[] pdus = (Object[]) bundle.get("pdus");

            for (int i = 0; i < pdus.length; i++) {

                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String senderNumber = currentMessage.getDisplayOriginatingAddress();

                EventBus.getDefault().post(new IncomingSMSEvent(senderNumber));

            }
        }
    }

}
