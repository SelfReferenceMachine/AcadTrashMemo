package com.nomoressay.acadtrashmemo.DataBase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import com.nomoressay.acadtrashmemo.Data.NoteData;
import com.nomoressay.acadtrashmemo.R;

public class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<NoteData> array;

        public MyAdapter(LayoutInflater inf, ArrayList<NoteData> arry){
            this.inflater=inf;
            this.array=arry;
        }

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return array.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        //判断是修改还是新建，同时对切出窗口后的内容保存
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if(convertView==null){
                vh=new ViewHolder();
                convertView = inflater.inflate(R.layout.list_view,null);
                vh.tv1=(TextView) convertView.findViewById(R.id.list_title);
                vh.tv2=(TextView) convertView.findViewById(R.id.list_time);
                convertView.setTag(vh);
            }
            vh=(ViewHolder) convertView.getTag();
            vh.tv1.setText(array.get(position).getTitle());
            vh.tv2.setText(array.get(position).getTimes());
            return convertView;
        }
        class ViewHolder{
            TextView tv1,tv2;
        }
}
