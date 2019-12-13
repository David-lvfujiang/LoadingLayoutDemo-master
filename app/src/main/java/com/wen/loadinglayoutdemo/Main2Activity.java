package com.wen.loadinglayoutdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
String[] arry = {"张三","里斯"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new MyAdapter(getLayoutInflater()));

    }
    public class MyAdapter extends BaseAdapter{

        private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

        /*
        定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
        */
        public MyAdapter(LayoutInflater inflater){
            mInflater = inflater;
        }

        @Override
        public int getCount() {
            return arry.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup) {
            //获得ListView中的view
            if (convertview == null){
                convertview = mInflater.inflate(R.layout.slide_layout,null);
                TextView textView = convertview.findViewById(R.id.text);
                MyViewHolder viewHolder = new MyViewHolder(textView);
                convertview.setTag(viewHolder);
            }
            MyViewHolder myViewHolder = (MyViewHolder)convertview.getTag();
            myViewHolder.getTextView().setText(arry[position]);
            return convertview ;
        }
    }
    class MyViewHolder {
        TextView textView;

        public MyViewHolder(TextView textView) {
            this.textView = textView;
        }

        public TextView getTextView() {
            return textView;
        }
    }
}
