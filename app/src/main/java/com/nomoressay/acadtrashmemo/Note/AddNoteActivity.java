package com.nomoressay.acadtrashmemo.Note;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.nomoressay.acadtrashmemo.Data.NoteData;
import com.nomoressay.acadtrashmemo.DataBase.DBManager;
import com.nomoressay.acadtrashmemo.R;

public class AddNoteActivity extends AppCompatActivity {
        EditText ed_title;
        EditText ed_content;
        FloatingActionButton floatingActionButton;
        DBManager dbManager;
        NoteData noteData;
        int ids;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_add);
            ed_title = (EditText)findViewById(R.id.title);
            ed_content = (EditText)findViewById(R.id.content);

            floatingActionButton = (FloatingActionButton)findViewById(R.id.finish);

            dbManager = new DBManager(this);
            Intent intent = this.getIntent();

            ids = intent.getIntExtra("ids",0);
            if (ids != 0){
                noteData = dbManager.getTiCon(ids);
                ed_title.setText(noteData.getTitle());
                ed_content.setText(noteData.getContent());
            }
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSave();
                }
            });

        }

        @Override
        public void onBackPressed() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            Date date = new Date(System.currentTimeMillis());
            String time = simpleDateFormat.format(date);
            String title = ed_title.getText().toString();
            String content = ed_content.getText().toString();
            if(ids!=0){
                noteData=new NoteData(title,ids, content, time);
                dbManager.toUpdate(noteData);
                Intent intent=new Intent(AddNoteActivity.this,NoteActivity.class);
                startActivity(intent);
                AddNoteActivity.this.finish();
            }
            else{
                noteData=new NoteData(title,content,time);
                dbManager.toInsert(noteData);
                Intent intent=new Intent(AddNoteActivity.this,NoteActivity.class);
                startActivity(intent);
                AddNoteActivity.this.finish();
            }

        }

        private void isSave(){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            Date date = new Date(System.currentTimeMillis());
            String time = simpleDateFormat.format(date);
            String title = ed_title.getText().toString();
            String content = ed_content.getText().toString();
            if(ids!=0){
                noteData=new NoteData(title,ids, content, time);
                dbManager.toUpdate(noteData);
                Intent intent=new Intent(AddNoteActivity.this,NoteActivity.class);
                startActivity(intent);
                AddNoteActivity.this.finish();
            }
            else{
                noteData=new NoteData(title,content,time);
                dbManager.toInsert(noteData);
                Intent intent=new Intent(AddNoteActivity.this,NoteActivity.class);
                startActivity(intent);
                AddNoteActivity.this.finish();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.new_lo,menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.new_share:
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,"标题："+ed_title.getText().toString()+""+"内容："+ed_content.getText().toString());
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            return false;
        }
}
