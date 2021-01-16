package com.example.moneysaver.admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneysaver.DangKy;
import com.example.moneysaver.R;
import com.example.moneysaver.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.regex.Pattern;

public class AdminAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<User> userList;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public AdminAdapter(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tv_show_tk ,tv_show_mk ,tv_show_idvi;
        ImageView img_fix ,img_delete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_show_tk       = (TextView) convertView.findViewById(R.id.tv_show_taikhoan);
            viewHolder.tv_show_mk       = (TextView) convertView.findViewById(R.id.tv_show_mk);
            viewHolder.tv_show_idvi     = (TextView) convertView.findViewById(R.id.tv_show_idvi);
            viewHolder.img_fix          = (ImageView) convertView.findViewById(R.id.img_fix_account);
            viewHolder.img_delete       = (ImageView) convertView.findViewById(R.id.img_delete_account);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_show_tk.setText(userList.get(position).getId());
        viewHolder.tv_show_mk.setText(userList.get(position).getPass());
        viewHolder.tv_show_idvi.setText(userList.get(position).getIdVi());


        viewHolder.img_fix.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View viewDialog = LayoutInflater.from(context).inflate(R.layout.dialog_fix_account_of_adminpage,null);
                builder.setView(viewDialog);
                builder.setCancelable(false);
                //anh xa trong dialog
                TextView tv_show_tk_dialog                          = viewDialog.findViewById(R.id.tv_show_tk_dialog);
                final TextInputEditText tv_nhap_mk_dialog           = viewDialog.findViewById(R.id.tv_nhap_mk_dialog);
                final TextInputEditText tv_nhap_lai_mk_dialog       = viewDialog.findViewById(R.id.tv_nhap_lai_mk_dialog);
                final TextInputLayout txt_layout_pass1              = viewDialog.findViewById(R.id.text_pass_error_dialog);
                final TextInputLayout txt_layout_pass2              = viewDialog.findViewById(R.id.text_pass2_error_dialog);
                Button btnHuy_dialog                                = viewDialog.findViewById(R.id.btnHuy_dialog);
                Button btnOK_dialog                                 = viewDialog.findViewById(R.id.btnOK_dialog);

                tv_show_tk_dialog.setText(userList.get(position).getId());
                final AlertDialog ad = builder.show();

                //nut OK trong dialog
               btnOK_dialog.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(TextUtils.isEmpty(tv_nhap_mk_dialog.getText())){
                           txt_layout_pass1.setError("Bạn admin chưa nhập trường này !");
                       }
                       if (TextUtils.isEmpty(tv_nhap_lai_mk_dialog.getText())){
                           txt_layout_pass2.setError("Bạn admin chưa nhập trường này !");
                       }
                       if(!TextUtils.isEmpty(tv_nhap_mk_dialog.getText()) && !TextUtils.isEmpty(tv_nhap_lai_mk_dialog.getText())){
                           txt_layout_pass1.setError(null);
                           txt_layout_pass2.setError(null);
                           if (tv_nhap_mk_dialog.getText().toString().equals(tv_nhap_lai_mk_dialog.getText().toString())) {
                               Pattern p = Pattern.compile("^[a-zA-Z0-9]{0,9}$");
                               if (p.matcher(tv_nhap_mk_dialog.getText()).find()) {
                                   database = FirebaseDatabase.getInstance();
                                   myRef = database.getReference("User");
                                   User newUser = new User(userList.get(position).getId(), tv_nhap_mk_dialog.getText().toString(), userList.get(position).getStatus(), userList.get(position).getIdVi(),1);
                                   myRef.child(userList.get(position).getId()).setValue(newUser);
                                   Toast.makeText(context, "Đổi mật khẩu tài khoản thành công <3", Toast.LENGTH_SHORT).show();
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
               });


                //nut cancel trong dialog
                btnHuy_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ad.dismiss();
                    }
                });

            }
        });

        viewHolder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = FirebaseDatabase.getInstance().getReference("User").child(userList.get(position).getId());
                myRef.removeValue();
            }
        });

        return convertView;
    }
}
