package com.example.moneysaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneysaver.model.TienMat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThemTienMat extends AppCompatActivity {
    TextView tienmatHT;
    Button btnSaveM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tien_mat);
        btnSaveM = findViewById(R.id.btnSaveM);
        tienmatHT = findViewById(R.id.soduHT);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("TienMat");

        btnSaveM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(tienmatHT.getText())){
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            double result =0;
                            for(DataSnapshot ds : snapshot.getChildren()){
                                TienMat tienHT = ds.getValue(TienMat.class);
                                double tienTon = tienHT.getTienHT();
                                double tienThem = Double.parseDouble(tienmatHT.getText()+"");
                                result = (tienTon + tienThem);

                            }
                            snapshot.child("1").child("tienHT").getRef().setValue(result);
                            Intent intent = new Intent(getApplicationContext(),FirstPage.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    Toast.makeText(ThemTienMat.this, "Đéo được lưu", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}