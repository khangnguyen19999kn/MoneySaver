package com.example.moneysaver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moneysaver.ActivityDate;
import com.example.moneysaver.ChonNhom;
import com.example.moneysaver.Nhom;
import com.example.moneysaver.R;
import com.example.moneysaver.datasource.ChiTieuDataSource;
import com.example.moneysaver.lapkehoach.HoaDonLayout;
import com.example.moneysaver.lapkehoach.NganSachActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemGiaoDichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemGiaoDichFragment extends Fragment {
    Button btnThem;
    EditText tien,ghiChu;
    TextView chonNhom,ngay;

    private ChiTieuDataSource chiTieuDataSource;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThemGiaoDichFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThemGiaoDichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThemGiaoDichFragment newInstance(String param1, String param2) {
        ThemGiaoDichFragment fragment = new ThemGiaoDichFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        connect();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from view

                //insert data
                chiTieuDataSource = new ChiTieuDataSource(getContext());
                chiTieuDataSource.createChiTieu(1,Integer.parseInt(tien.getText().toString()),
                        ngay.getText().toString(),ghiChu.getText().toString(),"user1vi1");
                //
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.anim_click));
                Intent intent  = new Intent(getActivity(), HoaDonLayout.class);
                startActivity(intent);

            }
        });

        chonNhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.anim_click));
                Intent intent  = new Intent(getActivity(), ChonNhom.class);
                startActivity(intent);

            }
        });

        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.anim_click));
                Intent intent  = new Intent(getActivity(), ActivityDate.class);
                startActivity(intent);

            }
        });

    }
    private String getDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        String ngay = dinhDangNgay.format(calendar.getTime());
        return ngay;
    }
    //
    private void connect() {
        btnThem = getView().findViewById(R.id.btnThem);
        tien = getView().findViewById(R.id.nhapTien);
        chonNhom = getView().findViewById(R.id.nhom);
        ghiChu = getView().findViewById(R.id.note);
        ngay = getView().findViewById(R.id.ngay);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_them_giao_dich, container, false);
    }
}