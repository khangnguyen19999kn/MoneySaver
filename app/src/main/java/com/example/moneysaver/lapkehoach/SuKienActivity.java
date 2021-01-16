package com.example.moneysaver.lapkehoach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysaver.ChonNhom;
import com.example.moneysaver.FirstPage;
import com.example.moneysaver.R;
import com.example.moneysaver.ThemKeHoach;
import com.example.moneysaver.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuKienActivity extends AppCompatActivity {
    Button btnThemSuKien;
    Button btnVeSoGiaoDich;
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
    }
    private void writeNewStudent(String name,int age){

    }
}