package com.example.catatankeuangan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catatankeuangan.database.KeuanganHelper;
import com.example.catatankeuangan.model.Keuangan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertUpdateActivity extends AppCompatActivity {

    private EditText edtNominal, edtKeterangan;
    private Button btnSubmit;

    private KeuanganHelper keuanganHelper;

    public static int KIRIM_INSERT = 100;
    public static int TERIMA_INSERT = 101;

    public static int KIRIM_UPDATE_DELETE = 200;
    public static int TERIMA_UPDATE = 201;

    public static int TERIMA_DELETE = 301;

    public boolean isUpdate = false;

    public static String KEY_KIRIM_OBJEK_KEUANGAN = "keuangan";
    public static String KEY_KIRIM_INSERT = "insert";
    public static String KEY_KIRIM_UPDATE = "update";
    public static String KEY_KIRIM_DELETE = "delete";

    private Keuangan terimaKeuangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_update);

        edtNominal = findViewById(R.id.edt_nominal);
        edtKeterangan = findViewById(R.id.edt_keterangan);
        btnSubmit = findViewById(R.id.btn_submit);

        keuanganHelper = new KeuanganHelper(this);
        keuanganHelper.open();

        terimaKeuangan = getIntent().getParcelableExtra(KEY_KIRIM_OBJEK_KEUANGAN);

        if (terimaKeuangan !=null){
            isUpdate = true;
        }

        String strTitle, strButton;

        if (isUpdate){
            strTitle = "Update Data";
            strButton = "UPDATE";

            edtKeterangan.setText(terimaKeuangan.getKeterangan());
            edtNominal.setText(terimaKeuangan.getNominal());
        }else {
            strTitle = "Insert Data";
            strButton = "INSERT";
        }
        getSupportActionBar().setTitle(strTitle);
        btnSubmit.setText(strButton);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nominal = edtNominal.getText().toString();
                String keterangan = edtKeterangan.getText().toString();

                if (TextUtils.isEmpty(nominal) || TextUtils.isEmpty(keterangan)){
                    Toast.makeText(InsertUpdateActivity.this, "Inputan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent();
                    Keuangan keuangan = new Keuangan();
                    keuangan.setTanggal(currentDate());
                    keuangan.setKeterangan(keterangan);
                    keuangan.setNominal(Integer.parseInt(nominal));

                    keuanganHelper.insertKeuangan(keuangan);

                    intent.putExtra(KEY_KIRIM_INSERT, "Data Berhasil di Inputkan");
                    setResult(TERIMA_INSERT, intent);
                    finish();
                }
            }
        });

    }

    private String currentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        Date date = new Date();
        return sdf.format(date);
    }

}

