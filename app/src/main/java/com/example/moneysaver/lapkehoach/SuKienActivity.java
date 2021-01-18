package com.example.moneysaver.lapkehoach;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysaver.ChonNhom;
import com.example.moneysaver.FirstPage;
import com.example.moneysaver.R;
import com.example.moneysaver.SQLite;
import com.example.moneysaver.ThemKeHoach;
import com.example.moneysaver.helper.ListLoaiHoatDongHelper;
import com.example.moneysaver.model.LoaiHoatDong;
import com.example.moneysaver.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SuKienActivity extends AppCompatActivity {
    Button btnThemSuKien;
    Button btnVeSoGiaoDich;
    ListView lv_sukien;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private SQLite sqLite;
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_kien);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Student");
//        Student student = new Student("Nguyễn Tấn Nghĩa",23);
//        myRef.child("students").child("17130133").setValue(student);

        btnThemSuKien = findViewById(R.id.btnThemSuKien);
        btnVeSoGiaoDich = findViewById(R.id.btnVeSoGiaoDich);
        lv_sukien       = findViewById(R.id.listViewSuKien);
        arrayList       = new ArrayList<>();
        sqLite = new SQLite(this, "taikhoan.sqlite",null, 1);

        btnThemSuKien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(AnimationUtils.loadAnimation(SuKienActivity.this,R.anim.anim_click));
                Intent intent  = new Intent(SuKienActivity.this, ThemKeHoach.class);
                startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
            }
        });
        btnVeSoGiaoDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(AnimationUtils.loadAnimation(SuKienActivity.this,R.anim.anim_click));
                Intent intent  = new Intent(SuKienActivity.this, FirstPage.class);
                startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
            }
        });

        Cursor all_ke_hoach = sqLite.getData("SELECT * FROM kehoach");
        while (all_ke_hoach.moveToNext()){
            String result = "";
            result += "ngày: "+all_ke_hoach.getString(3)+"\n";
            for(LoaiHoatDong loaiHoatDong: ListLoaiHoatDongHelper.getListLoaiHD()){
                if(all_ke_hoach.getInt(1) == loaiHoatDong.getId()){
                    result += "loại hoạt động: "+ loaiHoatDong.getTenHoatDong()+"\n";
                    break;
                }
            }
            result += "tiền: " +String.valueOf(all_ke_hoach.getInt(2))+"\n";
            result += "ghi chú: "+all_ke_hoach.getString(4) +"\n";

//            result += all_ke_hoach.getString(5);
            arrayList.add(result);

        }
        arrayAdapter = new ArrayAdapter<>(SuKienActivity.this, android.R.layout.simple_list_item_1, arrayList);
        lv_sukien.setAdapter(arrayAdapter);
    }
    private void writeNewStudent(String name,int age){

    }
}