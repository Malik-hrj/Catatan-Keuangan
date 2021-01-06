package com.example.catatankeuangan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static String CREATE_TABLE = String.format("CREATE TABLE %s"+
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s INTEGER NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL)",
            TableDatabase.TABLE_KEUANGAN,
            TableDatabase.ColumnKeuangan._ID,
            TableDatabase.ColumnKeuangan.NOMINAL,
            TableDatabase.ColumnKeuangan.KETERANGAN,
            TableDatabase.ColumnKeuangan.TANGGAL);

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TableDatabase.TABLE_KEUANGAN);
        onCreate(db);
    }
}



