package com.example.moneysaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneysaver.model.Nhom;

import java.util.ArrayList;
import java.util.List;

public class NhomAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Nhom> listNhom;

    public NhomAdapter(Context context, int layout, ArrayList<Nhom> listNhom) {
        this.context = context;
        this.layout = layout;
        this.listNhom = listNhom;
    }

    @Override
    public int getCount() {
        return listNhom.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        TextView tv_tenNhom     = (TextView) convertView.findViewById(R.id.tv_tennhom);
        ImageView img_tenNhom   = (ImageView) convertView.findViewById(R.id.imgNhom);

        tv_tenNhom.setText(listNhom.get(position).getTenNhom());
        img_tenNhom.setImageResource(listNhom.get(position).getImgNhom());
        return convertView;
    }
}
