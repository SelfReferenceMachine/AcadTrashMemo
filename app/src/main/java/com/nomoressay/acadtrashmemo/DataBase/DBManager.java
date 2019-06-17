package com.nomoressay.acadtrashmemo.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import com.nomoressay.acadtrashmemo.Data.NoteData;


public class DBManager {
    Context context;
    SQLOpenHelper sqlOpenHelper;
    SQLiteDatabase dbManager;

    public DBManager(Context context){
        this.context = context;
        sqlOpenHelper = new SQLOpenHelper(context);
    }

    public ArrayList<NoteData> getarray(){
        ArrayList<NoteData> arr = new ArrayList<NoteData>();
        ArrayList<NoteData> arr1 = new ArrayList<NoteData>();
        dbManager = sqlOpenHelper.getWritableDatabase();
        Cursor cursor = dbManager.rawQuery("select ids,title,times from note",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("ids"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String times = cursor.getString(cursor.getColumnIndex("times"));
            NoteData data = new NoteData(id,title,times);
            arr.add(data);
            cursor.moveToNext();
        }
        dbManager.close();
        for (int i = arr.size(); i >0; i--) {
            arr1.add(arr.get(i-1));
        }
        return arr1;
    }

    public NoteData getTiCon(int id){
        dbManager=sqlOpenHelper.getWritableDatabase();
        Cursor cursor=dbManager.rawQuery("select title,content from note where ids='"+id+"'", null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        NoteData data=new NoteData(title,content);
        dbManager.close();
        return data;
    }

    public void toUpdate(NoteData data){
        dbManager=sqlOpenHelper.getWritableDatabase();
        dbManager.execSQL("update note set title='"+data.getTitle()+"',times='"+data.getTimes()+"',content='"+data.getContent()+"'where ids='"+ data.getIds()+"'");
        dbManager.close();
    }

    public void toInsert(NoteData data){
        dbManager=sqlOpenHelper.getWritableDatabase();
        dbManager.execSQL("insert into note(title,content,times)values('"+data.getTitle()+"','"+data.getContent()+"','"+data.getTimes()+"')");
        sqlOpenHelper.close();
    }

    public void toDelete(int ids){
        dbManager=sqlOpenHelper.getWritableDatabase();
        dbManager.execSQL("delete from note where ids="+ids+"");
        dbManager.close();
    }
}
