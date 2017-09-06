package id.gomus.gosparring.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by idn on 9/5/2017.
 */

public class TeamModel implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("unique_id")
    @Expose
    private String uniqueId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("asal")
    @Expose
    private String asal;
    @SerializedName("kapten")
    @Expose
    private String kapten;
    @SerializedName("kontak")
    @Expose
    private String kontak;
    @SerializedName("menang")
    @Expose
    private String menang;
    @SerializedName("seri")
    @Expose
    private String seri;
    @SerializedName("kalah")
    @Expose
    private String kalah;
    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getKapten() {
        return kapten;
    }

    public void setKapten(String kapten) {
        this.kapten = kapten;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getMenang() {
        return menang;
    }

    public void setMenang(String menang) {
        this.menang = menang;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public String getKalah() {
        return kalah;
    }

    public void setKalah(String kalah) {
        this.kalah = kalah;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.uniqueId);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.asal);
        dest.writeString(this.kapten);
        dest.writeString(this.kontak);
        dest.writeString(this.menang);
        dest.writeString(this.seri);
        dest.writeString(this.kalah);
        dest.writeString(this.point);
        dest.writeString(this.level);
        dest.writeString(this.lat);
        dest.writeString(this.lng);
    }

    public TeamModel() {
    }

    protected TeamModel(Parcel in) {
        this.id = in.readString();
        this.uniqueId = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.asal = in.readString();
        this.kapten = in.readString();
        this.kontak = in.readString();
        this.menang = in.readString();
        this.seri = in.readString();
        this.kalah = in.readString();
        this.point = in.readString();
        this.level = in.readString();
        this.lat = in.readString();
        this.lng = in.readString();
    }

    public static final Parcelable.Creator<TeamModel> CREATOR = new Parcelable.Creator<TeamModel>() {
        @Override
        public TeamModel createFromParcel(Parcel source) {
            return new TeamModel(source);
        }

        @Override
        public TeamModel[] newArray(int size) {
            return new TeamModel[size];
        }
    };
}
