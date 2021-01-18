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



    }

}