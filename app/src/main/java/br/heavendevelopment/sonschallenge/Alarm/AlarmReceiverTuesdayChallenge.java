package br.heavendevelopment.sonschallenge.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.heavendevelopment.sonschallenge.Activity.DesafioActivity;

/**
 * Created by yuri on 18/03/18.
 */

public class AlarmReceiverTuesdayChallenge extends BroadcastReceiver {

    String TAG = "AlarmReceiverChallenge";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub


        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                NotificationScheduler.setReminderTuesdayChallenge(context, AlarmReceiverTuesdayChallenge.class);
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
        NotificationScheduler.showNotificationTuesdayChallenge(context, DesafioActivity.class,
                "Hora de falar algo para o Espírito Santo", "O que Ele é para você?");

    }
}
