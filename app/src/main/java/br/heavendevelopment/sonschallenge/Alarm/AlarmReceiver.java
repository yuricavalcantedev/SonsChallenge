package br.heavendevelopment.sonschallenge.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import br.heavendevelopment.sonschallenge.Activity.DesafioActivity;
import br.heavendevelopment.sonschallenge.Activity.MainActivity;

/**
 * Created by Jaison on 17/06/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";
    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        sharedPreferences = context.getSharedPreferences("preferencesMain",Context.MODE_PRIVATE);
        String alarme = sharedPreferences.getString("alarme", "07:00");
        String x [] = alarme.split(":");
        int hora = Integer.parseInt(x[0]);
        int minutos = Integer.parseInt(x[1]);

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                NotificationScheduler.setReminder(context, AlarmReceiver.class, hora, minutos);
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
        NotificationScheduler.showNotification(context, DesafioActivity.class,
                "Hora de ir mais perto a Jesus", "Você quer ler agora?");

    }
}


