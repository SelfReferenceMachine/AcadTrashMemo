package com.nomoressay.acadtrashmemo;

import android.content.Intent;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nomoressay.acadtrashmemo.Data.NoteData;
import com.nomoressay.acadtrashmemo.Note.NoteActivity;

import java.util.ArrayList;

public class DashFragment extends Fragment {

    Button btn_editor;


    /*
    ListView listView;

    LayoutInflater layoutInflater;
    ArrayList<NoteData> arrayList;
    liDBManager dbManager;


    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        layoutInflater = getLayoutInflater();
        dbManager = new liDBManager(this);
        arrayList = dbManager.getarray1();
        MyAdapter adapter = new MyAdapter(layoutInflater,arrayList);
        listView.setAdapter(adapter);
    }

    ListView listView;
    LayoutInflater layoutInflater;
    ArrayList<NoteData> arrayList;
    public DBManager dbManager = new DBManager(this.getActivity());
    */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_dash, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            btn_editor=getActivity().findViewById(R.id.btn_editor);
            btn_editor.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),NoteActivity.class));
                }
            });

    }

}

    //@Nullable
    //@Override
    //public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,Bundle savedInstanceState) {
      //  View view = inflater.inflate(R.layout.frame_dash,null);
        //return view;

    //}
    //@Override
    //public void onActivityCreated(Bundle savedInstanceState){
      //  super.onActivityCreated(savedInstanceState);
        //btn_play=getActivity().findViewById(R.id.navigation_dashboard);
        //btn_play.setOnClickListener(new View.OnClickListener(){
        //                  @Override
        //                public void onClick(View v) {
        //                  startActivity(new Intent(getActivity(), NoteActivity.class));
        //            }
        //      });}

