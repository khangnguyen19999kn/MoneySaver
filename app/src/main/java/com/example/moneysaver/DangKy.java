package com.example.moneysaver;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysaver.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class DangKy extends AppCompatActivity {
    private TextInputLayout txt_layout_id, txt_layout_pass1, txt_layout_pass2;
    private TextInputEditText txt_acc, txt_pass1, txt_pass2;
    private Button buttonCreate,buttonBack;
    private Intent intent;
    private SQLite sqLite;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        //anh xa
        txt_layout_id           = (TextInputLayout)  findViewById(R.id.text_id_error_register);
        txt_layout_pass1        = (TextInputLayout)  findViewById(R.id.text_pass_error_register);
        txt_layout_pass2        = (TextInputLayout)  findViewById(R.id.text_pass2_error_register);
        txt_acc                 = (TextInputEditText) findViewById(R.id.txtID_register);
        txt_pass1               = (TextInputEditText) findViewById(R.id.text_pass_register);
        txt_pass2               = (TextInputEditText) findViewById(R.id.text_pass2_register);
        buttonBack              = (Button) findViewById(R.id.btnBack);
        buttonCreate            = (Button) findViewById(R.id.btnCreate);
        intent = new Intent(DangKy.this,DangNhap.class);
        final ArrayList<String>           listString          =   new ArrayList<>();
        final ArrayList<TextInputLayout>  listTextInputLayout =   new ArrayList<>();
        listTextInputLayout.add(txt_layout_id);
        listTextInputLayout.add(txt_layout_pass1);
        listTextInputLayout.add(txt_layout_pass2);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
        //setAction
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai bao cac thanh phan
                for (int i = 0 ; i < listTextInputLayout.size(); i++){
                    listTextInputLayout.get(i).setError(null);
                }
                int count = 0;
                listString.clear();
                final String username  = String.valueOf(txt_acc.getText()).trim();
                final String pass1 = String.valueOf(txt_pass1.getText()).trim();
                final String pass2 = String.valueOf(txt_pass2.getText()).trim();
                listString.add(username);
                listString.add(pass1);
                listString.add(pass2);


                //kiem tra cac truong da dc nhap hay chua
                for (int i = 0; i < 3; i++) {
                    if(TextUtils.isEmpty(listString.get(i))){
                        listTextInputLayout.get(i).setError("Bạn chưa nhập trường này !");
                    }else if (!TextUtils.isEmpty(listString.get(i))){
                        count++;
                        listTextInputLayout.get(i).setError(null);
                    }
                }

                //neu cac truong da đc nhap vao het
                if(count == listString.size()) {
                    final boolean[] success = {false};
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                User user = (User) dataSnapshot.getValue(User.class);
                                if(user.getId().equals(txt_acc.getText().toString())) {
                                    success[0] = true;
                                    txt_layout_id.setError("Tài khoản đã có người đăng ký <3");
                                    break;
                                }
                            }
                            if(!success[0]){
                                if (pass1.equals(pass2)) {
                                    Pattern p = Pattern.compile("^[a-zA-Z0-9]{0,9}$");
                                    if (p.matcher(pass1).find()) {
                                        database = FirebaseDatabase.getInstance();
                                        myRef = database.getReference("User");
                                        User newUser = new User(username, pass1, 1, "",1);
                                        myRef.child(username).setValue(newUser);
                                        Toast.makeText(DangKy.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        txt_layout_pass1.setError("Pass dưới 9 ký tự !");
                                        txt_layout_pass2.setError("Pass dưới 9 ký tự !");
                                    }
                                } else {
                                    txt_layout_pass1.setError("Hai trường dữ liệu chưa trùng nhau !");
                                    txt_layout_pass2.setError("Hai trường dữ liệu chưa trùng nhau !");
                                }
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    }
            }
        });

    }
}

