package com.example.moneysaver;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.moneysaver.datasource.ChiTieuDataSource;
import com.example.moneysaver.lapkehoach.AlarmReceiver;
import com.example.moneysaver.lapkehoach.SuKienActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ThemKeHoach extends AppCompatActivity {
    Button btnThemKH;
    EditText tienKH,ghiChuKH,ngayKH;
    TextView chonNhomKH;
    private ChiTieuDataSource chiTieuDataSource;
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ke_hoach);

        final Intent intentAlamr = new Intent(ThemKeHoach.this, AlarmReceiver.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(ThemKeHoach.this, FirstPage.class);

        btnThemKH = findViewById(R.id.btnThemKH);
        tienKH = findViewById(R.id.nhapTienKH);
        ghiChuKH = findViewById(R.id.noteKH);
        chonNhomKH = findViewById(R.id.nhomKH);
        ngayKH = findViewById(R.id.ngayKH);

        createNotificationChannel();

        btnThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from view
//                insertData();
                //
                addNotification(ngayKH.getText().toString());
                //
                intentAlamr.putExtra("tien",tienKH.getText().toString());
                intentAlamr.putExtra("ngay",ngayKH.getText().toString());
                intentAlamr.putExtra("ghichu",ghiChuKH.getText().toString());
                pendingIntent = PendingIntent.getBroadcast(
                        ThemKeHoach.this,0,intentAlamr,PendingIntent.FLAG_UPDATE_CURRENT
                );
                calendar = Calendar.getInstance();
                long timeNow = getTime(calendar.getTime());
                long timeScheduling = getTime(ngayKH.getText().toString());
                long time = timeScheduling-timeNow;
//                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+60000,pendingIntent);
//                alarmManager.set(AlarmManager.RTC_WAKEUP,getTime(ngayKH.getText().toString())-calendar.getTimeInMillis(),pendingIntent);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+time,pendingIntent);

                //
                v.startAnimation(AnimationUtils.loadAnimation(ThemKeHoach.this,R.anim.anim_click));
                Intent intent  = new Intent(ThemKeHoach.this, FirstPage.class);

                startActivity(intent);
            }
        });
        chonNhomKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(ThemKeHoach.this,R.anim.anim_click));
                Intent intent  = new Intent(ThemKeHoach.this, ChonNhom.class);
                startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
            }
        });

    }

    private String getDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        String ngay = dinhDangNgay.format(calendar.getTime());
        return ngay;
    }
    private  long getTime(String date){
        Calendar calendar = Calendar.getInstance();
        long time=0;
        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateTime = dinhDangNgay.parse(date);
            time =dateTime.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  time;
    }
    private  long getTime(Date date){
        Calendar calendar = Calendar.getInstance();
        long time=0;
//        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");

            Date dateTime = date;
            time =dateTime.getTime();


        return  time;
    }
    public void insertData(){

                chiTieuDataSource = new ChiTieuDataSource(ThemKeHoach.this);
                chiTieuDataSource.createChiTieu(1001,Integer.parseInt(tienKH.getText().toString()),
                        ngayKH.getText().toString(),ghiChuKH.getText().toString(),"user1vi1");

    }
//    private void scheduleNotification(Context context, long time, int idToDo) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        PendingIntent pendingIntent = IntentFactory.getNotificationPendingIntent(this, "ToDo", "DemoToDo");
//        alarmMng.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
//    }
    private void createNotificationChannel(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel-th01", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


    }
    private  void addNotification(String mesg){
        String title ="MoneySaver";
        String mes ="bạn có một kế hoạch giao dịch vào ngày: "+mesg;
        Intent notificationIntent = new Intent(this,FirstPage.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra("title", title);
        notificationIntent.putExtra("mes", mes);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel-th01")
                .setSmallIcon(R.drawable.notificationicon2)
                .setContentTitle(title)
                .setContentText(mes)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());

    }

}