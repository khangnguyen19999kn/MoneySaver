package com.example.moneysaver;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.moneysaver.datasource.ChiTieuDataSource;
import com.example.moneysaver.lapkehoach.SuKienActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class ThemKeHoach extends AppCompatActivity {
    Button btnThemKH;
    EditText tienKH,ghiChuKH;
    TextView chonNhomKH,ngayKH;
    private ChiTieuDataSource chiTieuDataSource;
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ke_hoach);
        btnThemKH = findViewById(R.id.btnThemKH);
        tienKH = findViewById(R.id.nhapTienKH);
        ghiChuKH = findViewById(R.id.noteKH);
        chonNhomKH = findViewById(R.id.nhomKH);
        ngayKH = findViewById(R.id.ngayKH);

        btnThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from view
                //insert data
                chiTieuDataSource = new ChiTieuDataSource(ThemKeHoach.this);
                chiTieuDataSource.createChiTieu(1001,Integer.parseInt(tienKH.getText().toString()),
                        ngayKH.getText().toString(),ghiChuKH.getText().toString(),"user1vi1");

                //
                v.startAnimation(AnimationUtils.loadAnimation(ThemKeHoach.this,R.anim.anim_click));
                Intent intent  = new Intent(ThemKeHoach.this, SuKienActivity.class);
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
        ngayKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(ThemKeHoach.this,R.anim.anim_click));
                Intent intent  = new Intent(ThemKeHoach.this, ActivityDate.class);
                startActivity(intent);
            }
        });
    }private String getDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        String ngay = dinhDangNgay.format(calendar.getTime());
        return ngay;
    }
}