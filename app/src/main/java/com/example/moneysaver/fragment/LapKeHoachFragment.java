package com.example.moneysaver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.moneysaver.R;
import com.example.moneysaver.lapkehoach.HoaDonLayout;
import com.example.moneysaver.lapkehoach.NganSachActivity;
import com.example.moneysaver.lapkehoach.SuKienActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LapKeHoachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LapKeHoachFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    LinearLayout nganSach,suKien,hoaDon;
    private String mParam2;

    public LapKeHoachFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LapKeHoachFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LapKeHoachFragment newInstance(String param1, String param2) {
        LapKeHoachFragment fragment = new LapKeHoachFragment();
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

        nganSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.anim_click));
                Intent intent  = new Intent(getActivity(), NganSachActivity.class);
                startActivity(intent);

            }
        });

        suKien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.anim_click));

                Intent intent  = new Intent(getActivity(), SuKienActivity.class);
                startActivity(intent);
            }
        });
        hoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.anim_click));
                Intent intent  = new Intent(getActivity(), HoaDonLayout.class);
                startActivity(intent);
            }
        });
       }
//
    private void connect() {
        nganSach = getView().findViewById(R.id.nganSach);
        suKien = getView().findViewById(R.id.suKien);
        hoaDon = getView().findViewById(R.id.hoaDon);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lap_ke_hoach, container, false);
    }

}