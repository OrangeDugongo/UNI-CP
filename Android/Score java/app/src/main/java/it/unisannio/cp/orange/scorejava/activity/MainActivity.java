package it.unisannio.cp.orange.scorejava.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import it.unisannio.cp.orange.scorejava.R;
import it.unisannio.cp.orange.scorejava.Serie;
import it.unisannio.cp.orange.scorejava.fragment.SerieDetail;
import it.unisannio.cp.orange.scorejava.fragment.SerieMaster;

public class MainActivity extends AppCompatActivity implements SerieDetail.OnRatingChange, SerieMaster.OnClickListener{

    private SerieDetail detail = new SerieDetail();
    private SerieMaster master = new SerieMaster();
    private FrameLayout detailFrame = null;
    private boolean is2Pane = false;

    public static final String ERROR = "ERROR";
    public static final String EXTRA_ITEM = "it.unisannio.cp.orange.scorejava.ITEM";
    public static final int CODE_DETAIL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null)
            add(R.id.master, master);
        else
            replace(R.id.master, master);

        is2Pane = determineLayout();            //determina se mostrare due frame o meno

        if(is2Pane)
            detailFrame = findViewById(R.id.detail);
            getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    if(detailFrame != null)
                        detailFrame.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0));
                }
            });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDialog().show();         //mostra un dialog per aggiungere un elemento
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CODE_DETAIL:               //gestisce il ritorno dell'elemento con lo score modificato
                if(resultCode == RESULT_OK)
                    master.refresh((Serie) data.getSerializableExtra(EXTRA_ITEM));
                else
                    Log.e(ERROR, "no result");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);              //crea il menu generale
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {               //attribuisce un'azione ad ogni voce del menu generale
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                return true;
            case R.id.menu_bug_report:
                Intent bug = new Intent(this, BugReportActivity.class);
                startActivity(bug);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.list_con_menu, menu);          //crea il menu contestuale
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {               //attribuisce un'azione ad ogni voce del menu contestuale
        int pos = ((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position;
        final Serie serieItem = (Serie) master.getListAdapter().getItem(pos);
        switch (item.getItemId()){
            case R.id.list_con_delete:
                master.delete(serieItem);
                Snackbar snack = Snackbar.make(findViewById(R.id.main_layout), R.string.on_delete, Snackbar.LENGTH_SHORT);
                snack.setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        master.refresh(serieItem);
                    }
                }).show();
                return true;
            case R.id.list_con_azzera:
                master.toZero(serieItem);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private AlertDialog addDialog(){            //costruisce il dialog per l'aggiunta di un nuovo elemento
        View view = getLayoutInflater().inflate(R.layout.dialog_add, null);
        final EditText etAdd = view.findViewById(R.id.add_editText);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_dialog_title);
        builder.setView(view);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!master.add(etAdd.getText().toString()))
                    Snackbar.make(findViewById(R.id.main_layout), R.string.duplicate, Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.undo, null);
        return builder.create();
    }

    @Override
    public void onRatingChange(Serie item) {
        master.refresh(item);
    }

    @Override
    public void onClickListener(Serie item) {
        if(is2Pane){            //landscape
            if(!detail.isAdded()){          //controlla se il secondo frame è gia nel layout altrimenti lo aggiunge
                Log.d("ADD", "in");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.detail, detail).addToBackStack(null).commit();
                getFragmentManager().executePendingTransactions();
                detailFrame.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
            }
            detail.showDetail(item);
        }else{          //se si trova in portrait avvia una nuova activity
            Intent detailIntent = new Intent(this, DetailActivity.class);
            detailIntent.putExtra(EXTRA_ITEM, item);
            startActivityForResult(detailIntent, CODE_DETAIL);
        }
    }

    private boolean determineLayout(){ return findViewById(R.id.detail) != null; }

    private void add(int id, Fragment fragment){            //shortcut per aggiungere un frame
        getFragmentManager().beginTransaction().add(id, fragment).commit();
    }

    private void replace(int id, Fragment fragment){        //shortcut per sostituire un frame
        getFragmentManager().beginTransaction().replace(id, fragment).commit();
    }

}


