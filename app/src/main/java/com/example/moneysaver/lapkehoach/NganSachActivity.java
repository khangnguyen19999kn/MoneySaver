package com.example.moneysaver.lapkehoach;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysaver.R;
import com.example.moneysaver.model.LoaiHoatDong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NganSachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngan_sach);
        ArrayList<LoaiHoatDong> listLoaiHoatDong = new ArrayList<>();
        listLoaiHoatDong.add(new LoaiHoatDong(1001,"Cafe","cafe.png",0));
        listLoaiHoatDong.add(new LoaiHoatDong(1002,"Mua sắm","cafe.png",0));
        listLoaiHoatDong.add(new LoaiHoatDong(1003,"Thiết bị điện tử","cafe.png",0));
        listLoaiHoatDong.add(new LoaiHoatDong(1004,"Nhà Hàng","cafe.png",0));
        listLoaiHoatDong.add(new LoaiHoatDong(1005,"Sách Vở","cafe.png",0));


    }
    private void w(final LoaiHoatDong loaiHoatDong){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("LoaiHoatDong");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // getValue() returns Long

                myRef.child(loaiHoatDong.getId()+"").setValue(loaiHoatDong);  // <= Change to ++count
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // throw an error if setValue() is rejected
                throw databaseError.toException();
            }
        });
    }
}