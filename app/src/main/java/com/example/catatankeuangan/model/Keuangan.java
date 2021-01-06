package com.example.catatankeuangan.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Keuangan implements Parcelable {

    int id, nominal;
    String keterangan, tanggal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public static Creator<Keuangan> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.nominal);
        dest.writeString(this.keterangan);
        dest.writeString(this.tanggal);
    }

    public Keuangan() {
    }

    protected Keuangan(Parcel in) {
        this.id = in.readInt();
        this.nominal = in.readInt();
        this.keterangan = in.readString();
        this.tanggal = in.readString();
    }

    public static final Parcelable.Creator<Keuangan> CREATOR = new Parcelable.Creator<Keuangan>() {
        @Override
        public Keuangan createFromParcel(Parcel source) {
            return new Keuangan(source);
        }

        @Override
        public Keuangan[] newArray(int size) {
            return new Keuangan[size];
        }
    };
}

