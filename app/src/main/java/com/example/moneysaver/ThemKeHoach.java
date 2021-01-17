package com.example.moneysaver;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.moneysaver.datasource.ChiTieuDataSource;
import com.example.moneysaver.lapkehoach.AlarmReceiver;
import com.example.moneysaver.lapkehoach.SuKienActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class ThemKeHoach extends AppCompatActivity {
    Button btnThemKH;
    EditText tienKH,ghiChuKH;
    TextView chonNhomKH,ngayKH;
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

        btnThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from view
//                insertData();

                //
                intentAlamr.putExtra("tien",tienKH.getText().toString());
                intentAlamr.putExtra("ngay",ngayKH.getText().toString());
                intentAlamr.putExtra("ghichu",ghiChuKH.getText().toString());
                pendingIntent = PendingIntent.getBroadcast(
                        ThemKeHoach.this,0,intentAlamr,PendingIntent.FLAG_UPDATE_CURRENT
                );
                calendar = Calendar.getInstance();
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis()+60000,pendingIntent);

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

}