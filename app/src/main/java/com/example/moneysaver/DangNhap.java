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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;

public class DangNhap extends AppCompatActivity {
    private EditText text_id,text_pass;
    private Button button_login, button_register;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    public SQLite sqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        //anh xa

        text_id         = findViewById(R.id.textid);
        text_pass       = findViewById(R.id.textpass);
        button_login    = findViewById(R.id.buttonlogin);
        loginButton     = findViewById(R.id.login_button);
        button_register = findViewById(R.id.buttonregister);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");

        //tao database
        sqLite = new SQLite(this, "taikhoan.sqlite",null, 1);
        sqLite.queryData("CREATE TABLE IF NOT EXISTS user(username VARCHAR(15),  pass VARCHAR(15), status INTEGER, IdVí VARCHAR(15))");

//        sqLite.queryData("INSERT INTO user VALUES ('nct','1234',1,'')");
        checkLogin();

        //setAction cho button_login
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(text_id.getText());
                String pass = String.valueOf(text_pass.getText());
                if(TextUtils.isEmpty(id) || TextUtils.isEmpty(pass)){
                    Toast.makeText(DangNhap.this, "Mày chưa nhập id hay pass nha tml !!!", Toast.LENGTH_SHORT).show();
                }else{
                    Cursor dataInformation = sqLite.getData("SELECT * FROM user WHERE username ='" + id + "' AND pass='" + pass + "'");
                    if(dataInformation != null) {
                        while (dataInformation.moveToNext()){
                            Intent it_nav = new Intent(DangNhap.this, FirstPage.class);
                            it_nav.putExtra("name",dataInformation.getString(1));
                            sqLite.queryData("UPDATE user SET status = 2 where username ='" + dataInformation.getString(0) + "'");
                            startActivity(it_nav);
                        }
                    }else{
                        Toast.makeText(DangNhap.this, "Sai thông tin !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //buttonLogin
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_register = new Intent(DangNhap.this, DangKy.class);
                startActivity(it_register);
            }
        });

        //setAction cho loginbutton face
        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("user_gender"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(DangNhap.this, FirstPage.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Log.d("Demo","Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Demo","Error");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void checkLogin(){
        Cursor getAccLogin = sqLite.getData("SELECT * FROM user WHERE status = 2");
        if(getAccLogin != null){
            Intent i = new Intent(DangNhap.this, FirstPage.class);
            startActivity(i);
        }
    }
}