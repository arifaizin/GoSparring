package id.gomus.gosparring.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by idn on 9/6/2017.
 */

public class LapanganModel implements Parcelable {
    @SerializedName("id_lap")
    @Expose
    private String idLap;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kontak")
    @Expose
    private String kontak;
    @SerializedName("banyaklapangan")
    @Expose
    private String banyaklapangan;

    public String getIdLap() {
        return idLap;
    }

    public void setIdLap(String idLap) {
        this.idLap = idLap;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getBanyaklapangan() {
        return banyaklapangan;
    }

    public void setBanyaklapangan(String banyaklapangan) {
        this.banyaklapangan = banyaklapangan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idLap);
        dest.writeString(this.nama);
        dest.writeString(this.lat);
        dest.writeString(this.lng);
        dest.writeString(this.alamat);
        dest.writeString(this.kontak);
        dest.writeString(this.banyaklapangan);
    }

    public LapanganModel() {
    }

    protected LapanganModel(Parcel in) {
        this.idLap = in.readString();
        this.nama = in.readString();
        this.lat = in.readString();
        this.lng = in.readString();
        this.alamat = in.readString();
        this.kontak = in.readString();
        this.banyaklapangan = in.readString();
    }

    public static final Parcelable.Creator<LapanganModel> CREATOR = new Parcelable.Creator<LapanganModel>() {
        @Override
        public LapanganModel createFromParcel(Parcel source) {
            return new LapanganModel(source);
        }

        @Override
        public LapanganModel[] newArray(int size) {
            return new LapanganModel[size];
        }
    };
}
