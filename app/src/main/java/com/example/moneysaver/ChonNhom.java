package com.example.moneysaver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChonNhom extends AppCompatActivity {
    private ImageView imgBack, imgFind;
    private TextView txt_divayvachovay,txt_khoanchi,txt_khoanthu,txt_behinddivayvachovay,txt_behindkhoanchi,txt_behindkhoanthu;
    private int green,white,text_normal;
    private ListView listView_tennhom;
    private SQLite dataNhom;
    private NhomAdapter nhomAdapter;
    private ArrayList<Nhom> arrayListNhom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_nhom);

        //variable
        imgBack                     = (ImageView) findViewById(R.id.icBackAtChonNhom);
        imgFind                     = (ImageView) findViewById(R.id.icFindAtChonNhom);
        txt_divayvachovay           = (TextView)  findViewById(R.id.divayvachovay);
        txt_khoanchi                = (TextView)  findViewById(R.id.khoanchi);
        txt_khoanthu                = (TextView)  findViewById(R.id.khoanthu);
        txt_behinddivayvachovay     = (TextView)  findViewById(R.id.behindDivayvachovay);
        txt_behindkhoanchi          = (TextView)  findViewById(R.id.behindKhoanchi);
        txt_behindkhoanthu          = (TextView)  findViewById(R.id.behindKhoanthu);
        listView_tennhom            = (ListView)  findViewById(R.id.list_itemNhom);
        arrayListNhom               = new ArrayList<>();

        //color
        white                       = getColor(R.color.white);
        green                       = getColor(R.color.green);
        text_normal                 = getColor(R.color.textcolor);
        //imgBack
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //imgFind
        imgFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //txt_divayvachovay
        txt_divayvachovay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_divayvachovay.setTextColor(green);
                txt_behinddivayvachovay.setBackgroundColor(green);
                txt_khoanchi.setTextColor(text_normal);
                txt_behindkhoanchi.setBackgroundColor(white);
                txt_khoanthu.setTextColor(text_normal);
                txt_behindkhoanthu.setBackgroundColor(white);
                arrayListNhom.clear();
                arrayListNhom.add(new Nhom(R.drawable.ic_thuno, "Thu nợ"));
                nhomAdapter = new NhomAdapter(ChonNhom.this,R.layout.nhom_chinh,arrayListNhom);
                listView_tennhom.setAdapter(nhomAdapter);
            }
        });

        //txt_khoanchi
        txt_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_khoanchi.setTextColor(green);
                txt_behindkhoanchi.setBackgroundColor(green);
                txt_divayvachovay.setTextColor(text_normal);
                txt_behinddivayvachovay.setBackgroundColor(white);
                txt_khoanthu.setTextColor(text_normal);
                txt_behindkhoanthu.setBackgroundColor(white);
                arrayListNhom.clear();
                arrayListNhom.add(new Nhom(R.drawable.ic_doan, "Đồ ăn"));
                nhomAdapter = new NhomAdapter(ChonNhom.this,R.layout.nhom_chinh,arrayListNhom);
                listView_tennhom.setAdapter(nhomAdapter);
            }
        });

        //txt_khoanthu
        txt_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_khoanthu.setTextColor(green);
                txt_behindkhoanthu.setBackgroundColor(green);
                txt_divayvachovay.setTextColor(text_normal);
                txt_behinddivayvachovay.setBackgroundColor(white);
                txt_khoanchi.setTextColor(text_normal);
                txt_behindkhoanchi.setBackgroundColor(white);
                arrayListNhom.clear();
                arrayListNhom.add(new Nhom(R.drawable.ic_luong, "Lương"));
                arrayListNhom.add(new Nhom(R.drawable.ic_bando, "Bản đồ"));
                nhomAdapter = new NhomAdapter(ChonNhom.this,R.layout.nhom_chinh,arrayListNhom);
                listView_tennhom.setAdapter(nhomAdapter);
            }
        });
    }
}