package com.example.moneysaver.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moneysaver.FirstPage;
import com.example.moneysaver.LogAdapter;
import com.example.moneysaver.R;
import com.example.moneysaver.helper.SqlChiTieuHelper;
import com.example.moneysaver.helper.SqlLoaiHoatDongHelper;
import com.example.moneysaver.model.ChiTieu;
import com.example.moneysaver.model.LoaiHoatDong;
import com.example.moneysaver.model.LogModel;
import com.example.moneysaver.model.TienMat;
import com.example.moneysaver.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SoGiaoDichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SoGiaoDichFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listViewLog;
    private LogModel logModel;
    private ChiTieu chiTieu;
    private List<User> userList;
    private TextView tienHienCo;

    public SoGiaoDichFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SoGiaoDichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SoGiaoDichFragment newInstance(String param1, String param2) {
        SoGiaoDichFragment fragment = new SoGiaoDichFragment();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_so_giao_dich, container, false);
        // Inflate the layout for this fragment
        listViewLog = (ListView) view.findViewById(R.id.listViewLog);
        tienHienCo =(TextView) view.findViewById(R.id.tienHienCo);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TienMat");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TienMat tienHT = snapshot.child("1").getValue(TienMat.class);
                DecimalFormat df = new DecimalFormat("#");
                df.setMaximumFractionDigits(10);
                tienHienCo.setText(df.format(tienHT.getTienHT())+" VND");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Logs
        DatabaseReference refLog = database.getReference("logs");
        final ArrayList<LogModel> listLog = new ArrayList<>();

        final LogAdapter logAdapter = new LogAdapter(this.getActivity(),listLog);
        listViewLog.setAdapter(logAdapter);

        refLog.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            listLog.add(snapshot.getValue(LogModel.class));
            Collections.reverse(listLog);
            logAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }



}