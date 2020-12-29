package com.example.moneysaver.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.moneysaver.LogAdapter;
import com.example.moneysaver.R;
import com.example.moneysaver.model.LogModel;

import java.util.ArrayList;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_so_giao_dich, container, false);
        // Inflate the layout for this fragment
//
//        //Set ListView
        String [] menuLog = {"Do something!","Do something else","Do homework now","Do you marry me?","Do you know the way","Do something!","Do something else","Do homework now","Do you marry me?","Do something!","Do something else","Do homework now","Do you marry me?","Do something!","Do something else","Do homework now","Do you marry me?","Do something!","Do something else","Do homework now","Do you marry me?","Do something!","Do something else","Do homework now","Do you marry me?"};
        ListView listViewLog = view.findViewById(R.id.listViewLog);
        ArrayList<LogModel> list = new ArrayList<LogModel>();
        list.add(new LogModel("Cafe",20000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));
        list.add(new LogModel("Trà sữa",60000));



        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,menuLog);
        LogAdapter logAdapter = new LogAdapter(this.getActivity(),list);
        listViewLog.setAdapter(logAdapter);

        return view;
    }


}