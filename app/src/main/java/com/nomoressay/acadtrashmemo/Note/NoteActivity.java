package com.nomoressay.acadtrashmemo.Note;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import com.nomoressay.acadtrashmemo.Data.NoteData;
import com.nomoressay.acadtrashmemo.DataBase.DBManager;
import com.nomoressay.acadtrashmemo.DataBase.MyAdapter;
import com.nomoressay.acadtrashmemo.R;

public class NoteActivity extends AppCompatActivity {

    ListView listView;
    FloatingActionButton floatingActionButton;
    LayoutInflater layoutInflater;
    ArrayList<NoteData> arrayList;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_note);
            listView = (ListView)findViewById(R.id.layout_list_view);
            floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
            layoutInflater = getLayoutInflater();

            dbManager = new DBManager(this);
            arrayList = dbManager.getarray();
            MyAdapter adapter = new MyAdapter(layoutInflater,arrayList);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {   //点击一下跳转到编辑页面（编辑页面与新建页面共用一个布局）
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(),AddNoteActivity.class);
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
                    Intent intent=new Intent(getApplicationContext(),AddNoteActivity.class);
                    startActivity(intent);
                    NoteActivity.this.finish();
                }
            });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_lo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_newnote:
                Intent intent = new Intent(getApplicationContext(),AddNoteActivity.class);
                startActivity(intent);
                NoteActivity.this.finish();
                break;
            case R.id.menu_exit:
                NoteActivity.this.finish();
                break;
            default:
                break;
        }
        return  true;

    }
}
