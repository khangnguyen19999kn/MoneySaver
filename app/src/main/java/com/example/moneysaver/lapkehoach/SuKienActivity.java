package com.example.moneysaver.lapkehoach;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysaver.R;
import com.example.moneysaver.model.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuKienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_kien);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Student");
        Student student = new Student("Nguyễn Tấn Nghĩa",23);
        myRef.child("students").child("17130133").setValue(student);
    }
    private void writeNewStudent(String name,int age){

    }
}