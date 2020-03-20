package com.phong.hocbroadcastreceiver_internetmanifest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.app.NotificationCompat;

public class InternetBroadcastReceiver extends BroadcastReceiver {
    NotificationManager notificationManager;
    int id = 100;
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null){
            displayNotification("Máy không có Internet", context);
        } else {
            displayNotification("Máy có Internet", context);
        }
    }

    //Hàm dùng Notification
    private void displayNotification(String msg, Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("Thông báo Internet");
        builder.setContentText(msg);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        //Tạo Intent để tương tác với Notification:
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("STATUS","Hoàng Tuấn Phong thông báo:" + msg);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent =
                taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id,builder.build());
    }
}
