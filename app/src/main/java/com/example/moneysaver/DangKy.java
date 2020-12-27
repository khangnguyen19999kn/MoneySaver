package com.example.moneysaver;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class DangKy extends AppCompatActivity {
    private EditText txt_acc, txt_pass1, txt_pass2;
    private Button buttonCreate,buttonBack;
    private SQLite sqLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        //anh xa
        txt_acc         = (EditText) findViewById(R.id.txtID);
        txt_pass1       = (EditText) findViewById(R.id.txtPass1);
        txt_pass2       = (EditText) findViewById(R.id.txtPass2);
        buttonCreate   = (Button) findViewById(R.id.btnCreate);
        sqLite = new SQLite(this, "taikhoan.sqlite",null, 1);

        //setAction
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(txt_acc.getText()) && !TextUtils.isEmpty(txt_pass1.getText()) && !TextUtils.isEmpty(txt_pass2.getText())) {
                    String user = String.valueOf(txt_acc.getText());
                    String pass1 = String.valueOf(txt_pass1.getText());
                    String pass2 = String.valueOf(txt_pass1.getText());
                    if(pass1.equals(pass2)){
                        sqLite.queryData("INSERT INTO user VALUES('"+ user +"','" + pass1 + "')");
                    }else{
                        Toast.makeText(DangKy.this,"Mật khẩu và xác nhận mật khẩu không trùng nhau",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DangKy.this,"Bạn chưa nhập đủ dữ liệu",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
