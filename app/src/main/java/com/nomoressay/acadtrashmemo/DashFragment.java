package com.nomoressay.acadtrashmemo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nomoressay.acadtrashmemo.Data.NoteData;
import com.nomoressay.acadtrashmemo.DataBase.DBManager;
import com.nomoressay.acadtrashmemo.DataBase.MyAdapter;
import com.nomoressay.acadtrashmemo.DataBase.SQLOpenHelper;
import com.nomoressay.acadtrashmemo.Note.AddNoteActivity;
import com.nomoressay.acadtrashmemo.Note.NoteActivity;

import java.util.ArrayList;


public class DashFragment extends Fragment {

    Button btn_editor;

    /*
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        getActivity().setTitle(R.string.title_activity_note);

        getListView() = NoteActivity.get(getActivity()).getNote();
    }*/

    ListView listView;
    LayoutInflater layoutInflater;
    ArrayList<NoteData> arrayList;

    public DBManager dbManager = new DBManager(this.getActivity());

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInflater = getLayoutInflater();
        arrayList = dbManager.getarray();
        final MyAdapter adapter = new MyAdapter(layoutInflater, arrayList);
        listView.setAdapter(adapter);
    }

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
            });}

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

