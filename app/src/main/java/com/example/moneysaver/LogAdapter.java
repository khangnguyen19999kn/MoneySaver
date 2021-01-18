package com.example.moneysaver;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneysaver.fragment.SoGiaoDichFragment;
import com.example.moneysaver.model.ChiTieu;
import com.example.moneysaver.model.LogModel;

import java.util.ArrayList;

public class LogAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater li;
    private ArrayList<LogModel> data;
    private  ImageView iconLog;

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
        TextView ngayGiaoDich =(TextView) view.findViewById(R.id.ngayGiaoDich);
        TextView ghichu = view.findViewById(R.id.ghichuLog);
        iconLog = (ImageView) view.findViewById(R.id.iconLog);
        String alo = data.get(i).getTenHD();


        if(alo!=null){
            switch(alo) {
                case "Cafe":
                    iconLog.setImageResource(R.drawable.coffee);
                    break;
                case "Thiết bị điện tử":
                    iconLog.setImageResource(R.drawable.tbdt);
                    break;
                case "Mua sắm":
                    iconLog.setImageResource(R.drawable.shoping);
                    break;
                case "Sách Vở":
                    iconLog.setImageResource(R.drawable.sachvo);
                    break;
                case "Nhà Hàng":
                    iconLog.setImageResource(R.drawable.nhahang);
                    break;
                case "Đồ ăn":
                    iconLog.setImageResource(R.drawable.food);
                    break;
                case "Thu nợ":
                    iconLog.setImageResource(R.drawable.thuno);
                    break;
                case "Lương":
                    iconLog.setImageResource(R.drawable.salary);
                    break;
                case "Bán đồ":
                    iconLog.setImageResource(R.drawable.ic_bando);
                    break;
                default:
                    iconLog.setImageResource(R.drawable.coin);
            }
        }
        nameLog.setText(data.get(i).getTenHD());
        if(data.get(i).getIsThu()==1){
            priceLog.setText("+ "+data.get(i).getTien()+" đ");
            priceLog.setTextColor(Color.GREEN);
        }else {
            priceLog.setText("-" +data.get(i).getTien()+" đ");
            priceLog.setTextColor(Color.RED);
        }

        ngayGiaoDich.setText(data.get(i).getDate());


        // Trả về view kết quả.
        return view;

    }

}
