
package com.example.moneysaver.lapkehoach;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.moneysaver.FirstPage;
import com.example.moneysaver.R;
import com.example.moneysaver.ThemKeHoach;
import com.example.moneysaver.datasource.ChiTieuDataSource;

public class AlarmReceiver extends BroadcastReceiver {
    private ChiTieuDataSource chiTieuDataSource;
    @Override
    public void onReceive(Context context, Intent intent) {
        chiTieuDataSource = new ChiTieuDataSource(context);
        int idLoaiHoatDong = intent.getExtras().getInt("idhoatdong");
        int tien = Integer.parseInt(intent.getExtras().getString("tien"));
        String ngay = intent.getExtras().getString("ngay");
        String ghiChu = intent.getExtras().getString("ghichu");
//        intent.getExtras().getString("ngay");
//        intent.getExtras().getString("ghichu");
//        intent.getExtras().getInt("idhoatdong");
        chiTieuDataSource.createChiTieu(idLoaiHoatDong,tien,
                ngay,ghiChu,"user1vi1");

//        Intent intent2 = new Intent(AlarmReceiver.this, FirstPage.class);


    }
//    public static PendingIntent getNotificationPendingIntent(Context context, String title, String desc) {
//        Notification notification = NotificationUtils.buildNotification(context, title, desc);
//        Intent intent = new Intent(context, AlarmReceiver.class);
//        intent.putExtra(CommonConstraint.NOTIFICATION_LABEL, notification);
//        int reqCode = 1;
//        return PendingIntent.getBroadcast(context, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//    }


}