package com.example.catatankeuangan;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.catatankeuangan.database.KeuanganHelper;
import com.example.catatankeuangan.model.Keuangan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvKeuangan;
    KeuanganAdapter KeuanganAdapter;
    KeuanganHelper KeuanganHelper;
    ArrayList<Keuangan> listKeuangan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvKeuangan = findViewById(R.id.rv_keuangan);
        rvKeuangan.setLayoutManager(new LinearLayoutManager(this));
        rvKeuangan.setHasFixedSize(true);

        KeuanganHelper = new KeuanganHelper(this);
        KeuanganHelper.open();

        listKeuangan = new ArrayList<>();

        KeuanganAdapter = new KeuanganAdapter(this);
        rvKeuangan.setAdapter(KeuanganAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, InsertUpdateActivity.class);
                startActivityForResult(intent, InsertUpdateActivity.KIRIM_INSERT);

            }
        });
        new LoadDataKeuangan().execute();
    }


    public class LoadDataKeuangan extends AsyncTask<Void, Void, ArrayList<Keuangan>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (listKeuangan.size() > 0) {
                listKeuangan.clear();
            }

        }

        @Override
        protected ArrayList<Keuangan> doInBackground(Void... voids) {
            return KeuanganHelper.readKeuangan();
        }



        @Override
        protected void onPostExecute(ArrayList<Keuangan> keuangans) {
            super.onPostExecute(keuangans);

            listKeuangan.addAll(keuangans);
            KeuanganAdapter.setArrayList(keuangans);
            KeuanganAdapter.notifyDataSetChanged();

            if (listKeuangan.size() == 0) {
                Snackbar.make(findViewById(R.id.content_main), "Belum ada data", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == InsertUpdateActivity.KIRIM_INSERT){
            if (resultCode == InsertUpdateActivity.TERIMA_INSERT){
                String mag = data.getStringExtra(InsertUpdateActivity.KEY_KIRIM_INSERT);
                Toast.makeText(this, mag, Toast.LENGTH_SHORT).show();
                new LoadDataKeuangan().execute();
            }
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
