package com.example.moneysaver;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moneysaver.admin.AdminPage;

import com.example.moneysaver.model.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class DangNhap extends AppCompatActivity {
    private TextInputEditText text_id,text_pass;
    private TextInputLayout textInputLayoutId, textInputLayoutPass;
    private Button button_login;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private SQLite sqLite;
    private TextView txtRegister;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        //anh xa

        text_id             = (TextInputEditText) findViewById(R.id.textid);
        text_pass           = (TextInputEditText) findViewById(R.id.textpass);
        textInputLayoutId   = (TextInputLayout) findViewById(R.id.text_id_error);
        textInputLayoutPass = (TextInputLayout) findViewById(R.id.text_pass_error);
        button_login        = (Button) findViewById(R.id.buttonlogin);
        loginButton         = (LoginButton) findViewById(R.id.login_button);
        txtRegister         = (TextView) findViewById(R.id.txtregister);
        final ArrayList<String> listString          =   new ArrayList<>();
        final ArrayList<TextInputLayout>  listTextInputLayout =   new ArrayList<>();
        listTextInputLayout.add(textInputLayoutId);
        listTextInputLayout.add(textInputLayoutPass);

        //tao database
        sqLite = new SQLite(this, "taikhoan.sqlite",null, 1);
        sqLite.queryData("CREATE TABLE IF NOT EXISTS user1(username VARCHAR(15), pass VARCHAR(15), status INTEGER, IdVí VARCHAR(15), level INTEGER)");
        checkLogin();

        //setAction cho button_login
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0 ; i < listTextInputLayout.size(); i++){
                    listTextInputLayout.get(i).setError(null);
                }
                final String id = String.valueOf(text_id.getText()).trim();
                final String pass = String.valueOf(text_pass.getText()).trim();
                int count = 0;
                listString.clear();
                listString.add(id);
                listString.add(pass);

                for (int i = 0; i < 2; i++) {
                    if(TextUtils.isEmpty(listString.get(i))){
                        listTextInputLayout.get(i).setError("Bạn chưa nhập trường này !");
                    }else if (!TextUtils.isEmpty(listString.get(i))){
                        count++;
                        listTextInputLayout.get(i).setError(null);
                    }
                }

                if (count == listString.size()){
                    final boolean[] success = {false};
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                User user = (User) dataSnapshot.getValue(User.class);
                                if(user.getId().equals(id) && user.getPass().equals(pass) && user.getLevel() == 1) {
                                    Intent it_nav = new Intent(DangNhap.this, FirstPage.class);
                                    User changeStatusUser = new User(user.getId(), user.getPass(), 2, "",user.getLevel());
                                    reference.child(user.getId()).setValue(changeStatusUser);
                                    success[0] = true;
                                    sqLite.queryData("DELETE FROM user1");
                                    sqLite.queryData("INSERT INTO user1 VALUES ('" + user.getId() + "','" + user.getPass() + "'," + user.getStatus() + ",'" + user.getIdVi() + "'," + user.getLevel() + ")");
                                    startActivity(it_nav);
                                    finish();
                                    break;
                                }
                                if(user.getId().equals(id) && user.getPass().equals(pass) && user.getLevel() == 2) {
                                    Intent it_nav = new Intent(DangNhap.this, AdminPage.class);
                                    success[0] = true;
                                    startActivity(it_nav);
                                    finish();
                                    break;
                                }
                                }
                            if(!success[0]){
                                Toast.makeText(DangNhap.this, "Sai thông tin ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        //setAction textview tao tai khoan moi
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_register = new Intent(DangNhap.this, DangKy.class);
                startActivity(it_register);
                finish();
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
                finish();
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
        String taikhoan = "";
        Cursor getAllFromUser1 = sqLite.getData("SELECT * FROM user1");
        while (getAllFromUser1.moveToNext()){
            taikhoan = getAllFromUser1.getString(0);
            Intent it_nav = new Intent(DangNhap.this, FirstPage.class);
            startActivity(it_nav);
            finish();
        }
    }

}