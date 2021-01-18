package com.example.moneysaver.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.moneysaver.DangNhap;
//import com.example.moneysaver.Dangxuat;
import com.example.moneysaver.R;
import com.example.moneysaver.SQLite;
import com.example.moneysaver.ViCuaTui;
import com.example.moneysaver.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaiKhoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaiKhoanFragment extends Fragment {
    Button btn;
    Button btn1;
    public SQLite sqLite;
    private  String text2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaiKhoanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaiKhoanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaiKhoanFragment newInstance(String param1, String param2) {
        TaiKhoanFragment fragment = new TaiKhoanFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        btn = view.findViewById(R.id.btnlogout);
        btn1 = view.findViewById(R.id.btnvct);
        final TextView text1 = view.findViewById(R.id.namelogin);
        sqLite = new SQLite(getActivity(), "taikhoan.sqlite",null, 1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change2 = new Intent(getActivity(), ViCuaTui.class);
                startActivity(change2);

            }
        });
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
//        reference.addValueEventListener(new ValueEventListener() {
//                                            @Override
//                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                for (DataSnapshot datasnapshot : snapshot.getChildren) {
//
//                                                    User user = (User) dataSnapshot.getValue(User.class);
//                                                    if (user.getLevel() == 1 && user.getStatus() == 2) {
//                                                        text2 = user.getId();
//
//
//                                                    }
//
//                                                }
//                                                text1.setText(text2);
//
//
//                                            }
//
//                                            @Override
//                                            public void onCancelled(@NonNull DatabaseError error) {
//
//                                            }
//                                        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "before click", Toast.LENGTH_SHORT).show();
                String idUserNow = "";
                Cursor getUserPresent = sqLite.getData("SELECT * FROM user1");
                while (getUserPresent.moveToNext()) {
                    idUserNow = getUserPresent.getString(0);
                    Toast.makeText(getActivity(), "idUserNow" + idUserNow, Toast.LENGTH_SHORT).show();
                }

                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User");
                final String finalIdUserNow = idUserNow;
//                Toast.makeText(getActivity(), "finalIdUserNow " + finalIdUserNow, Toast.LENGTH_SHORT).show();
                reference.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            User user = (User) dataSnapshot.getValue(User.class);
//                            Toast.makeText(getActivity(), "firebase", Toast.LENGTH_SHORT).show();
                            if (user.getId().equals(finalIdUserNow) && user.getStatus() == 2 && user.getLevel() == 1) {
                                Toast.makeText(getActivity(), "match", Toast.LENGTH_SHORT).show();
                                Intent change = new Intent(getActivity(), DangNhap.class);
                                User changeStatusUser = new User(user.getId(), user.getPass(), 1, "", user.getLevel());
                                reference.child(user.getId()).setValue(changeStatusUser);
                                sqLite.queryData("DELETE FROM user1");
                                startActivity(change);
                            }

                        }
                    }

                    ;

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        return view;
    }
}