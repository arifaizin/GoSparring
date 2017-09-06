package id.gomus.gosparring.rest;

import java.util.ArrayList;

import id.gomus.gosparring.model.LapanganModel;
import id.gomus.gosparring.model.TeamModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by idn on 8/12/2017.
 */

public interface ApiService {
    @GET("tim.php?operasi=view")
    Call<ArrayList<TeamModel>> ambilDataTeam();

    @GET("lapangan.php?operasi=view")
    Call<ArrayList<LapanganModel>> ambilDataLapangan();

    @FormUrlEncoded
    @POST("sparing.php?operasi=insert")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("nohp") String nohp,
                                       @Field("lapangan") String lapangan,
                                       @Field("nomorlapangan") String nomorlapangan,
                                       @Field("tanggal") String tanggal,
                                       @Field("waktu") String waktu,
                                       @Field("durasi") String durasi);

    // TODO digunakan dalam API datasparing.php (http://suci.can.web.id/apigebuk/golekmusuh/)
    @FormUrlEncoded
    @POST("datasparing.php")
    Call<ResponseBody> datasparingRequest(@Field("idteam1") String team1,
                                          @Field("idteam2") String team2,
                                          @Field("skorteam1") String skor1,
                                          @Field("skorteam2") String skor2,
                                          @Field("namalapangan") String lapangan,
                                          @Field("jadwal") String jadwal,
                                          @Field("waktu") String waktu,
                                          @Field("durasi") String durasi,
                                          @Field("status") String status);

    @FormUrlEncoded
    @POST("lapangan.php")
    Call<ResponseBody> profilRequest(@Field("namatim") String namatim,
                                     @Field("level") String level,
                                     @Field("idteam") String idteam,
                                     @Field("asal") String asal,
                                     @Field("kapten") String kapten,
                                     @Field("nohp") String durasi,
                                     @Field("menang") String menang,
                                     @Field("seri") String seri,
                                     @Field("kalah") String kalah,
                                     @Field("latitude") String latitude,
                                     @Field("longitude") String longitude);

}
