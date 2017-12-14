package it.unisannio.cp.orange.scorejava.fragment;


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/11/17
 *
 */


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Comparator;
import java.util.TreeMap;

import it.unisannio.cp.orange.scorejava.R;
import it.unisannio.cp.orange.scorejava.Serie;
import it.unisannio.cp.orange.scorejava.SerieAdapter;
import it.unisannio.cp.orange.scorejava.activity.MainActivity;

public class SerieMaster extends ListFragment{
    private TreeMap<String, Serie> map = new TreeMap<>();
    private OnClickListener onClickListener = null;
    private int order = 1;
    private SharedPreferences settings = null;

    public interface OnClickListener{                   //interfaccia per la comunicazione
        public void onClickListener(Serie item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnClickListener)          //verifico che l'activity chiamante rispetti l'interfaccia
            onClickListener = (OnClickListener) context;
        else
            Log.e(MainActivity.ERROR, "unmatching type");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        read();                 //ripristino lo stato della lista
        settings = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        if(!settings.getBoolean("pro", false))
            map.put(getString(R.string.buy_pro), new Serie(getString(R.string.buy_pro), 10));

        setListAdapter(new SerieAdapter(sortByValue(map), getContext()));       //setto l'adapter
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        registerForContextMenu(getListView());                                  //aggiungo alla lista il menu contestuale
    }

    @Override
    public void onStart() {
        super.onStart();
        order = settings.getInt("order", 1);
        setListAdapter(new SerieAdapter(sortByValue(map), getContext()));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Serie item = (Serie) getListView().getItemAtPosition(position);
        if(item.getNome().equals(getString(R.string.buy_pro))){             //controlla se l'utente ha premuto un elemento normale o il banner pubblicitario
            //crea un intent per una transazione bitcoin
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.bitcoin_uri)));
            if(intent.resolveActivity(getActivity().getPackageManager()) != null)           //controlla se l'utente ha app che possono gestire la transazione
                startActivity(intent);
            else
                paymentDialog().show();         //se l'utente non ha app per la transazione viene mostrato un qr code con l'indirizo per il pagamento
        }else
            onClickListener.onClickListener((Serie) getListView().getItemAtPosition(position));         //chiamo l'activity che si occuperò di gestire l'evento
    }

    public void refresh(Serie item){            //modifica lo score di un elemento
        map.put(item.getNome(), item);
        setListAdapter(new SerieAdapter(sortByValue(map), getContext()));
        save(item);
    }

    public boolean add(String name){               //aggiunge un elemento alla lista
        if(map.containsKey(name)) return false;
        Serie item = new Serie(name, 0);
        map.put(item.getNome(), item);
        setListAdapter(new SerieAdapter(sortByValue(map), getContext()));
        save(item);
        return true;
    }

    public boolean delete(Serie item){          //elimina un elemento dalla lista
        map.remove(item.getNome());
        setListAdapter(new SerieAdapter(sortByValue(map), getContext()));
        remove(item.getNome());
        return true;
    }

    public boolean toZero(Serie item){          //setta a zero un elemento della lista
        if(item.getScore()==0) return true;
        item.setScore(0);
        map.put(item.getNome(), item);
        setListAdapter(new SerieAdapter(sortByValue(map), getContext()));
        save(item);
        return true;
    }

    private TreeMap<String, Serie> sortByValue(final TreeMap<String, Serie> unsort){        //ordina la lista in base al valore
        TreeMap<String, Serie> sort = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Float a1 = unsort.get(o1).getScore();
                Float a2 = unsort.get(o2).getScore();
                int result = a2.compareTo(a1)*order;            //order determina se la lista viene ordinata in modo crescente o descrente
                if(result==0)
                    result = o1.compareTo(o2);
                return result;
            }
        });
        sort.putAll(unsort);
        return sort;
    }

    private void read(){            //legge gli elementi presenti nella lista
        Log.d("READ", "in");
        SharedPreferences ps = getContext().getSharedPreferences("list", Activity.MODE_PRIVATE);
        for(String s: ps.getAll().keySet())
            map.put(s, new Serie(s, ps.getFloat(s, 0)));
    }

    private void save(Serie item){          //salva un nuovo elemento nelle sp
        Log.d("SAVE", "in");
        SharedPreferences ps = getContext().getSharedPreferences("list", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = ps.edit();
        editor.putFloat(item.getNome(), item.getScore());
        editor.apply();
    }

    private void remove(String nome){       //elimina un elemento dalle sp
        SharedPreferences sp = getContext().getSharedPreferences("list", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(nome);
        editor.apply();
    }

    private AlertDialog paymentDialog(){    //crea un dialog con il qr code per il pagamento
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_payment, null);
        ImageView qr = view.findViewById(R.id.qr);
        String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.qr_code;
        qr.setImageURI(Uri.parse(path));

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        builder.setTitle(R.string.buy_pro);
        builder.setPositiveButton("ok", null);
        return builder.create();
    }
}
