package it.unisannio.cp.orange.scorejava.fragment;


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 27/11/17
 *
 */


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import it.unisannio.cp.orange.scorejava.R;
import it.unisannio.cp.orange.scorejava.Serie;
import it.unisannio.cp.orange.scorejava.activity.MainActivity;

public class SerieDetail extends Fragment{
    private OnRatingChange onRatingChange = null;
    private Serie item = null;
    private View root = null;

    public interface OnRatingChange{
        void onRatingChange(Serie item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.detail_layout, container, false);         //inserisce il layout
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRatingChange)              //controla se l'activity chiamante rispetta l'interfaccia
            onRatingChange = (OnRatingChange) context;
        else
            Log.e(MainActivity.ERROR, "unmatching type");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RatingBar ratingDetail = getActivity().findViewById(R.id.ratingDetail);
        ratingDetail.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating != item.getScore()){
                    item.setScore(rating);
                    onRatingChange.onRatingChange(item);            //notifica al chiamante che il valore di un elemento è cambiato
                }
            }
        });
    }

    public void showDetail(Serie item){             //setta i parametri del layout con i valori dell'elemento selezionato
        this.item=item;
        TextView textDetail = root.findViewById(R.id.nomeDetail);
        textDetail.setText(item.getNome());
        RatingBar ratingDetail = root.findViewById(R.id.ratingDetail);
        ratingDetail.setRating(item.getScore());
    }
}
