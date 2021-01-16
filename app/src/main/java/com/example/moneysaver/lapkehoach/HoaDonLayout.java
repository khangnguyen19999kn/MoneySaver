package com.example.moneysaver.lapkehoach;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysaver.R;
import com.example.moneysaver.model.LoaiHoatDong;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HoaDonLayout extends AppCompatActivity {
    private ArrayList<LoaiHoatDong> list= null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_layout);
        readData();




        
    }
    private void readData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Query allLoai = database.getReference().child("LoaiHoatDong");



        allLoai.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    list.add(item.getValue(LoaiHoatDong.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // throw an error if setValue() is rejected
                throw databaseError.toException();
            }
        });
        if(list!=null && list.size() >0){
            for (LoaiHoatDong i : list){
                Log.d("i23",i.getId()+"");
            }
        }

    }
}