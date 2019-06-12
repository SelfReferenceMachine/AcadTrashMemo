package com.nomoressay.acadtrashmemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    ListView listView;
    FloatingActionButton floatingActionButton;
    LayoutInflater layoutInflater;
    ArrayList<NoteData> arrayList;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            listView = (ListView)findViewById(R.id.layout_listview);
            floatingActionButton = (FloatingActionButton)findViewById(R.id.add_note);
            layoutInflater = getLayoutInflater();

            dbManager = new DBManager(this);
            arrayList = dbManager.getarray();
            MyAdapter adapter = new MyAdapter(layoutInflater,arrayList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击一下跳转到编辑页面（编辑页面与新建页面共用一个布局）
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(),AddActivity.class);
                    intent.putExtra("ids",arrayList.get(position).getIds());
                    startActivity(intent);
                    NoteActivity.this.finish();
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {   //长按删除
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    new AlertDialog.Builder(NoteActivity.this).setMessage("确定要删除此便签？")
                            .setNegativeButton("取消",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dbManager.toDelete(arrayList.get(position).getIds());
                                    MyAdapter myAdapter = new MyAdapter(layoutInflater,arrayList);
                                    listView.setAdapter(myAdapter);
                                }
                            })
                            .create()
                            .show();
                    return true;
                }
            });

            floatingActionButton.setOnClickListener(new View.OnClickListener() {   //点击悬浮按钮时，跳转到新建页面
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),AddActivity.class);
                    startActivity(intent);
                    NoteActivity.this.finish();
                }
            });
    }

}
