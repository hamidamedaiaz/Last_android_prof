package edu.polytech.td4ex3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.Service;
import android.os.IBinder;
import android.util.Log;
import android.media.AudioAttributes;



public class NotificationService extends Service {
    private final String TAG = "frallo "+getClass().getSimpleName();
    private final String CHANNEL_ID = "notif_channel_id";
    private Uri uriSound;



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel(NotificationManager.IMPORTANCE_HIGH);
        Log.d(TAG,"service has created the notification channel");
        return START_STICKY;
    }


    public void stopService(){
        stopSelf();
    }

    private void createNotificationChannel(int importance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.deleteNotificationChannel(CHANNEL_ID); // Supprime l'ancien canal

            CharSequence name = "Nom du canal";
            String description = "Description du canal";
            uriSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getApplicationContext().getPackageName() + "/" + R.raw.mj);

            //uriSound  = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.setSound(uriSound, audioAttributes); // Applique le son personnalisé ici
            notificationManager.createNotificationChannel(channel);
        }
    }


    // Définir le Binder local
    public class LocalBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }
    }

    private final IBinder binder = new LocalBinder(); // Crée un objet de type LocalBinder

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder; // Retourne le binder local
    }

    // Exemple de méthode publique que l'Activity pourrait appeler
    public void sendNotification(String title, String message) {
        // Affiche la notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Crée la notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setContentTitle(title != null ? title : "Titre par défaut")
                    .setContentText(message != null ? message : "Message par défaut")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setSound(uriSound);
            notificationManager.notify(1, builder.build());
        }
    }
}

