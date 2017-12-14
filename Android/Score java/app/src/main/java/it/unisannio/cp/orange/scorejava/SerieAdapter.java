package it.unisannio.cp.orange.scorejava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.TreeMap;



/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 15/11/17
 *
 */


public class SerieAdapter extends BaseAdapter {
    public SerieAdapter(TreeMap<String, Serie> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(list.keySet().toArray()[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, null);
        Serie selectedItem = (Serie) getItem(position);
        TextView nome = convertView.findViewById(R.id.nome);
        TextView score = convertView.findViewById(R.id.rating);
        TextView network = convertView.findViewById(R.id.network);
        ImageView img = convertView.findViewById(R.id.img);
        float scoreValue = selectedItem.getScore();
        nome.setText(selectedItem.getNome());
        network.setText(selectedItem.getNetwork());
        score.setText(Float.valueOf(scoreValue).toString());
        if(scoreValue==0)       //l'immagine da visualizzare viene determinata in base al punteggio
            img.setImageResource(R.mipmap.quest_img);
        else if (scoreValue<=2.5)
            img.setImageResource(R.mipmap.down_img);
        else
            img.setImageResource(R.mipmap.up_img);
        return convertView;
    }

    private TreeMap<String, Serie> list;
    private Context context;
}
