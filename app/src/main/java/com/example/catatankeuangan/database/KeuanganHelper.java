package com.example.catatankeuangan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.catatankeuangan.model.Keuangan;

import java.security.KeyException;
import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.catatankeuangan.database.TableDatabase.ColumnKeuangan.KETERANGAN;
import static com.example.catatankeuangan.database.TableDatabase.ColumnKeuangan.NOMINAL;
import static com.example.catatankeuangan.database.TableDatabase.ColumnKeuangan.TANGGAL;
import static com.example.catatankeuangan.database.TableDatabase.TABLE_KEUANGAN;



public class KeuanganHelper {

    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase sqLiteDatabase;

    public KeuanganHelper(Context context){
        databaseHelper = new DatabaseHelper(context, "dbkeuangan", null);
    }

    public void open() throws SQLException{
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public ArrayList<Keuangan> readKeuangan(){
        ArrayList<Keuangan> arrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE_KEUANGAN,
                null,null, null, null, null, _ID+" DESC", null);

        cursor.moveToFirst();

        Keuangan keuangan;
        if (cursor.getCount() > 0){
            do{
                keuangan = new Keuangan();
                keuangan.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                keuangan.setNominal(cursor.getInt(cursor.getColumnIndexOrThrow(NOMINAL)));
                keuangan.setKeterangan(cursor.getString(cursor.getColumnIndexOrThrow(KETERANGAN)));
                keuangan.setTanggal(cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL)));

                arrayList.add(keuangan);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertKeuangan(Keuangan keuangan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOMINAL, keuangan.getNominal());
        contentValues.put(KETERANGAN, keuangan.getKeterangan());
        contentValues.put(TANGGAL, keuangan.getTanggal());
        sqLiteDatabase.insert(TABLE_KEUANGAN, null, contentValues);
    }


}

