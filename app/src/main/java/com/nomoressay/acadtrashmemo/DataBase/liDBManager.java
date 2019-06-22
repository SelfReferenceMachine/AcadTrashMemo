package com.nomoressay.acadtrashmemo.DataBase;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

import com.nomoressay.acadtrashmemo.DashFragment;
import com.nomoressay.acadtrashmemo.Data.NoteData;

public class liDBManager{
/*
想要实现fragment的数据列表读取，参考原来的方法打包数据，但是没能显示于是作罢
 */
    Context context;
    SQLOpenHelper sqlOpenHelper;
    SQLiteDatabase dbManager1;

    public liDBManager(Context context){
        this.context = context;
        sqlOpenHelper = new SQLOpenHelper(context);
    }


    public ArrayList<NoteData> getarray1(){
        ArrayList<NoteData> arr = new ArrayList<NoteData>();
        ArrayList<NoteData> arr1 = new ArrayList<NoteData>();
        dbManager1 = sqlOpenHelper.getWritableDatabase();
        Cursor cursor = dbManager1.rawQuery("select ids,title,times from note",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("ids"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String times = cursor.getString(cursor.getColumnIndex("times"));
            NoteData data = new NoteData(id,title,times);
            arr.add(data);
            cursor.moveToNext();
        }
        dbManager1.close();
        for (int i = arr.size(); i >0; i--) {
            arr1.add(arr.get(i-1));
        }
        return arr1;
    }

    public NoteData getTiCon1(int id){
        dbManager1=sqlOpenHelper.getWritableDatabase();
        Cursor cursor=dbManager1.rawQuery("select title,content from note where ids='"+id+"'", null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        NoteData data=new NoteData(title,content);
        dbManager1.close();
        return data;
    }

}
