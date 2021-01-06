package com.example.catatankeuangan.database;

import android.provider.BaseColumns;

public class TableDatabase {

    public static String TABLE_KEUANGAN = "keuangan";

    public static final class ColumnKeuangan implements BaseColumns{
        public static String NOMINAL = "nominal";
        public static String KETERANGAN = "keterangan";
        public static String TANGGAL = "tanggal";
    }
}



