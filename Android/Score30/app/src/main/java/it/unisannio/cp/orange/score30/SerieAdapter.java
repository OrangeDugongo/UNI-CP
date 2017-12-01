/*
 *      Copyright (c) 2017 Raffaele Mignone
 *
 *      This file is part of  Score30
 *
 *      Score30 is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 2 of the License, or
 *      (at your option) any later version.
 *
 *      Score30 is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with Score30.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.unisannio.cp.orange.score30;

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
        score.setText(new Float(scoreValue).toString());
        if(scoreValue==0)
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
