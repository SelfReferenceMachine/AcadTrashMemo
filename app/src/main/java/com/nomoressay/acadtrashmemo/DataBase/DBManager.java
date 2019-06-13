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

    public ArrayList<NoteData> getarray(){            //获取listview中要显示的数据
        ArrayList<NoteData> arr = new ArrayList<NoteData>();
        ArrayList<NoteData> arr1 = new ArrayList<NoteData>();
        dbManager = sqlOpenHelper.getWritableDatabase();
        Cursor cursor = dbManager.rawQuery("select ids,title,times from mybook",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex("ids"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String times = cursor.getString(cursor.getColumnIndex("times"));
            NoteData data = new NoteData(id, title, times);
            arr.add(data);
            cursor.moveToNext();
        }
        dbManager.close();
        for (int i = arr.size(); i >0; i--) {
            arr1.add(arr.get(i-1));
        }
        return arr1;
    }

    public NoteData getTiandCon(int id){           //获取要修改数据（就是当选择listview子项想要修改数据时，获取数据显示在新建页面）
        dbManager = sqlOpenHelper.getWritableDatabase();
        Cursor cursor = dbManager.rawQuery("select title,content from mybook where ids='"+id+"'" , null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        NoteData data=new NoteData(title,content);
        dbManager.close();
        return data;
    }

    public void toUpdate(NoteData data){           //修改表中数据
        dbManager = sqlOpenHelper.getWritableDatabase();
        dbManager.execSQL(
                "update mybook set title='"+ data.getTitle()+
                        "',times='"+data.getTimes()+
                        "',content='"+data.getContent() +
                        "' where ids='"+ data.getIds()+"'");
        dbManager.close();
    }

    public void toInsert(NoteData data){           //在表中插入新建的便签的数据
        dbManager = sqlOpenHelper.getWritableDatabase();
        dbManager.execSQL("insert into mybook(title,content,times)values('"
                + data.getTitle()+"','"
                +data.getContent()+"','"
                +data.getTimes()
                +"')");
        sqlOpenHelper.close();
    }

    public void toDelete(int ids){            //在表中删除数据
        dbManager  = sqlOpenHelper.getWritableDatabase();
        dbManager.execSQL("delete from mybook where ids="+ids+"");
        dbManager.close();
    }

}
