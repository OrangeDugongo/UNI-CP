package it.unisannio.cp.orange.score.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import it.unisannio.cp.orange.score.MyAdapter;
import it.unisannio.cp.orange.score.Serie;

public class LeaderboardListActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = (HashMap<String, Serie>) getIntent().getSerializableExtra(MainActivity.SERIE_TV_ARRAY);
        sortList = sortByValue(list);
        MyAdapter adapter = new MyAdapter(sortList, this);
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LeaderboardListActivity.this, "Click on " + (String) parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LeaderboardListActivity.this, "Long click on " + (String) parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private TreeMap<String, Serie> sortByValue(final HashMap<String, Serie> unsort){
        TreeMap<String, Serie> sort = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Float a1 = new Float(unsort.get(o1).getScore());
                Float a2 = new Float(unsort.get(o2).getScore());
                int result = a2.compareTo(a1);
                if(result==0)
                    result = o1.compareTo(o2);
                return result;
            }
        });
        sort.putAll(unsort);
        return sort;
    }

    private HashMap<String, Serie> list;
    private TreeMap<String, Serie> sortList;
}
