package com.example.moneysaver;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneysaver.fragment.SoGiaoDichFragment;
import com.example.moneysaver.model.LogModel;

import java.util.ArrayList;

public class LogAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater li;
    private ArrayList<LogModel> data;

    public LogAdapter(Activity context, ArrayList<LogModel> data) {
        this.activity = context;
        this.data = data;


    }



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
        LayoutInflater inflater = activity.getLayoutInflater();

        // Đổ dữ liệu vào biến View, view này chính là những gì nằm trong item_name.xml
        view = inflater.inflate(R.layout.item_logs, null);

        // Đặt chữ cho từng view trong danh sách.
        TextView nameLog = (TextView) view.findViewById(R.id.nameLogs);
        TextView priceLog = (TextView) view.findViewById(R.id.priceLogs);
        nameLog.setText(data.get(i).getTenHD());
        priceLog.setText(data.get(i).getTien()+"");

        // Trả về view kết quả.
        return view;

    }

}
