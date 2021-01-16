package com.example.moneysaver.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneysaver.DangKy;
import com.example.moneysaver.DangNhap;
import com.example.moneysaver.FirstPage;
import com.example.moneysaver.R;
import com.example.moneysaver.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AdminPage extends AppCompatActivity {
    private ListView lv_user;
    private List<User> userList;
    private AdminAdapter adminAdapter;
    private TextView tv_count_all_account, tv_user_view, tv_admin_view, tv_user_line, tv_admin_line;
    private Button add_new_admin;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        //anh xa
        lv_user                 = (ListView) findViewById(R.id.lv_all_user);
        tv_count_all_account    = (TextView) findViewById(R.id.tv_count_all_account);
        tv_user_view            = (TextView) findViewById(R.id.user_view);
        tv_admin_view           = (TextView) findViewById(R.id.admin_view);
        tv_user_line            = (TextView) findViewById(R.id.user_line);
        tv_admin_line           = (TextView) findViewById(R.id.admin_line);
        add_new_admin           = (Button)   findViewById(R.id.add_new_admin);
        userList        = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = (User) dataSnapshot.getValue(User.class);
                    if(user.getLevel() == 1) {
                       userList.add(user);
                    }
                }
                tv_count_all_account.setText("Tổng số tài khoản đã đăng ký : " + userList.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adminAdapter = new AdminAdapter(this, R.layout.item_lv_adminpage,userList);
        lv_user.setAdapter(adminAdapter);

        tv_user_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_admin_view.setTextColor(getColor(R.color.textcolor));
                tv_admin_line.setBackgroundColor(getColor(R.color.white));
                tv_user_view.setTextColor(getColor(R.color.tv_adminpage));
                tv_user_line.setBackgroundColor(getColor(R.color.tv_adminpage));

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userList.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            User user = (User) dataSnapshot.getValue(User.class);
                            if(user.getLevel() == 1) {
                                userList.add(user);
                            }
                        }
                        tv_count_all_account.setText("Tổng số tài khoản đã đăng ký : " + userList.size());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                adminAdapter = new AdminAdapter(AdminPage.this, R.layout.item_lv_adminpage,userList);
                lv_user.setAdapter(adminAdapter);
            }
        });

        tv_admin_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_admin_view.setTextColor(getColor(R.color.tv_adminpage));
                tv_admin_line.setBackgroundColor(getColor(R.color.tv_adminpage));
                tv_user_view.setTextColor(getColor(R.color.textcolor));
                tv_user_line.setBackgroundColor(getColor(R.color.white));

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        userList.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            User user = (User) dataSnapshot.getValue(User.class);
                            if(user.getLevel() == 2) {
                                userList.add(user);
                            }
                        }
                        tv_count_all_account.setText("Tổng số tài khoản đã đăng ký : " + userList.size());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                adminAdapter = new AdminAdapter(AdminPage.this, R.layout.item_lv_adminpage,userList);
                lv_user.setAdapter(adminAdapter);
            }
        });


        add_new_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AdminPage.this);
                final View viewDialog = LayoutInflater.from(AdminPage.this).inflate(R.layout.dialog_add_new_admin_adminpage,null);
                builder.setView(viewDialog);
                builder.setCancelable(false);
                //anh xa trong dialog
                final TextInputLayout txt_layout_id           = (TextInputLayout)  viewDialog.findViewById(R.id.text_id_error_dialog_add_new_admin);
                final TextInputLayout txt_layout_pass1        = (TextInputLayout)  viewDialog.findViewById(R.id.text_pass_error_dialog_add_new_admin);
                final TextInputLayout txt_layout_pass2        = (TextInputLayout)  viewDialog.findViewById(R.id.text_pass2_error_dialog_add_new_admin);
                final TextInputEditText txt_acc               = (TextInputEditText) viewDialog.findViewById(R.id.txtID_dialog_add_new_admin);
                final TextInputEditText txt_pass1             = (TextInputEditText) viewDialog.findViewById(R.id.text_pass_dialog_add_new_admin);
                final TextInputEditText txt_pass2             = (TextInputEditText) viewDialog.findViewById(R.id.text_pass2_dialog_add_new_admin);
                Button buttonHuy                              = (Button) viewDialog.findViewById(R.id.btnHuy_diaglog_add_new_admin);
                Button buttonOK                               = (Button) viewDialog.findViewById(R.id.btnCreate_diaglog_add_new_admin);
                final ArrayList<String>           listString          =   new ArrayList<>();
                final ArrayList<TextInputLayout>  listTextInputLayout =   new ArrayList<>();
                listTextInputLayout.add(txt_layout_id);
                listTextInputLayout.add(txt_layout_pass1);
                listTextInputLayout.add(txt_layout_pass2);
                final AlertDialog ad = builder.show();

                buttonHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ad.dismiss();
                    }
                });

                buttonOK.setOnClickListener(new View.OnClickListener() {
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
                                                User newUser = new User(username, pass1, 1, "",2);
                                                myRef.child(username).setValue(newUser);
                                                Toast.makeText(AdminPage.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                                                ad.dismiss();
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
        });

    }
}