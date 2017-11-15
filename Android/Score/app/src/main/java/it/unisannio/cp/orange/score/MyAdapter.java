package it.unisannio.cp.orange.score;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;



/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 15/11/17
 *
 */


public class MyAdapter extends BaseAdapter {
    public MyAdapter(TreeMap<String, Float> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.keySet().toArray()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, null);
        String selectedItem = (String) getItem(position);
        TextView nome = convertView.findViewById(R.id.nome);
        TextView score = convertView.findViewById(R.id.score);
        ImageView img = convertView.findViewById(R.id.img);
        float scoreValue = list.get(selectedItem).floatValue();
        nome.setText(selectedItem);
        score.setText(list.get(selectedItem).toString());
        if(scoreValue==0)
            img.setImageResource(R.mipmap.quest_img);
        else if (scoreValue<=2.5)
            img.setImageResource(R.mipmap.down_img);
        else
            img.setImageResource(R.mipmap.up_img);
        return convertView;
    }

    private TreeMap<String, Float> list;
    private Context context;
}
