package com.example.moneysaver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.moneysaver.fragment.BaoCaoFragment;
import com.example.moneysaver.fragment.LapKeHoachFragment;
import com.example.moneysaver.fragment.SoGiaoDichFragment;
import com.example.moneysaver.fragment.TaiKhoanFragment;
import com.example.moneysaver.fragment.ThemGiaoDichFragment;
import com.example.moneysaver.model.TienMat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.DecimalFormat;

public class FirstPage extends AppCompatActivity {
    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment =null;
    private TextView addTienMat;
    private TextView tienHienCo, tienMatTong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        chipNavigationBar = findViewById(R.id.chipNavigation);
        chipNavigationBar.setItemSelected(R.id.sogiaodich,true);
        addTienMat = findViewById(R.id.themTienMat);
        tienHienCo = findViewById(R.id.tienHienCo);
        tienMatTong = findViewById(R.id.moneyCur);
        
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new SoGiaoDichFragment()).commit();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TienMat");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TienMat tienHT = snapshot.child("1").getValue(TienMat.class);
                DecimalFormat df = new DecimalFormat("#");
                df.setMaximumFractionDigits(10);
                tienMatTong.setText(df.format(tienHT.getTienHT())+" đ");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        addTienMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuyenQuaKia = new Intent(getApplicationContext(),ThemTienMat.class);
                startActivity(chuyenQuaKia);
            }
        });

        Intent intentFromThemTien = this.getIntent();
        Bundle bundle = intentFromThemTien.getExtras();
        if(bundle!=null){

            Double tienHT = bundle.getDouble("tienVe");

        }

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.sogiaodich:
                        fragment = new SoGiaoDichFragment();
                        break;
                    case R.id.baocao:
                        fragment = new BaoCaoFragment();
                        break;
                    case R.id.themgiaodich:
                        fragment = new ThemGiaoDichFragment();
                        break;
                    case R.id.lapkehoach:
                        fragment = new LapKeHoachFragment();
                        break;
                    case R.id.taikhoan:
                        fragment = new TaiKhoanFragment();
                        break;
                }
                if(fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                }

            }
        });




    }

}