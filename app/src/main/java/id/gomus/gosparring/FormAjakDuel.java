package id.gomus.gosparring;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import id.gomus.gosparring.rest.ApiService;
import id.gomus.gosparring.rest.Client;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAjakDuel extends AppCompatActivity {

    private static final String TAG = "FormAjakDuel";

    private int mSelectedYear;
    private int mSelectedMonth;
    private int mSelectedDay;
    private int mSelectedHour ,mSelectedMinute ,mSelectedSecond;

    private String tglBookingLapangan;
    private boolean mDebugMode = false;
    private String mCurrentMode = "";
    private LinearLayout divTglBookingLapangan;
    private LinearLayout divTimeBookingLapangan;
    private TextView tvTglBookingLapangan;
    private TextView tvBookingWaktuLapangan;
    private EditText edt_Booking_Nama, edt_Booking_NoHp;
    private TimePicker tp_BookingLapangan;
    private Spinner spnBookingLapangan, spnBookingDurasi;
    private Button btnBookingSimpang;
    private ProgressDialog loading;
    Context mContext;
    ApiService mApiService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ajak_duel);

        mContext = FormAjakDuel.this;
        mApiService = Client.getInstanceRetrofit();
        spnBookingLapangan = (Spinner) findViewById(R.id.spn_Booking_Lapangan);
        spnBookingDurasi = (Spinner) findViewById(R.id.spn_Booking_Durasi);
        edt_Booking_Nama = (EditText) findViewById(R.id.edt_Booking_NamaLapangan);
        edt_Booking_NoHp = (EditText) findViewById(R.id.edt_Booking_NomorLapangan);
        tvTglBookingLapangan = (TextView) findViewById(R.id.tvTglBookingLapangan);
        tvBookingWaktuLapangan = (TextView) findViewById(R.id.tvBookingWaktuLapangan);
        divTglBookingLapangan = (LinearLayout) findViewById(R.id.divTglBookingLapangan);
        divTimeBookingLapangan = (LinearLayout) findViewById(R.id.divTimeBookingLapangan);
        btnBookingSimpang = (Button) findViewById(R.id.btnBookingSimpan);

        String[] items = new String[]{"Lapangan 1", "Lapangan 2", "Lapangan 3"};
        String[] items1 = new String[]{"1 Jam", "2 Jam", "3 Jam"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, items1);
        spnBookingLapangan.setAdapter(adapter);
        spnBookingDurasi.setAdapter(adapter1);


        divTglBookingLapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        divTimeBookingLapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });

        edt_Booking_Nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });


//        tvBookingWaktuLapangan.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showTimeDialog();
//            }
//        });

//        spinner.setOnItemSelectedListener((AdapterOnItemSelectedListener) mContext);

        btnBookingSimpang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        mContext);

                alertDialog2.setTitle("Peringatan");
                alertDialog2.setMessage("Apakah anda ingin melanjutkan ?");
//                alertDialog2.setIcon(R.drawable.ic_info_black_24dp);
                alertDialog2.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(mContext, "Unch", Toast.LENGTH_SHORT).show();
                        reqBooking();
                    }
                });
                alertDialog2.show();
            }
        });

    }

    private void showDateDialog() {
        final Calendar c = Calendar.getInstance();
        mSelectedYear = c.get(Calendar.YEAR);
        mSelectedMonth = c.get(Calendar.MONTH);
        mSelectedDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if(mCurrentMode.isEmpty()) {
                            tvTglBookingLapangan.setText(Config.formatCustomDate(year, (monthOfYear + 1), dayOfMonth));
                            tglBookingLapangan = Config.formatDMY(year, (monthOfYear + 1), dayOfMonth);
                        }
                    }
                }, mSelectedYear, mSelectedMonth, mSelectedDay);
        datePickerDialog.show();
    }
    private void showTimeDialog(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvBookingWaktuLapangan.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void reqBooking() {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        Log.d(TAG, "reqBooking: "+"Skripsi Team"+
                edt_Booking_NoHp.getText().toString()+
                edt_Booking_Nama.getText().toString()+
                spnBookingLapangan.getSelectedItem().toString()+
                tvTglBookingLapangan.getText().toString()+
                tvBookingWaktuLapangan.getText().toString()+
                spnBookingDurasi.getSelectedItem().toString());


        mApiService.registerRequest("Skripsi Team",
                edt_Booking_NoHp.getText().toString(),
                edt_Booking_Nama.getText().toString(),
                spnBookingLapangan.getSelectedItem().toString(),
                tvTglBookingLapangan.getText().toString(),
                tvBookingWaktuLapangan.getText().toString(),
                spnBookingDurasi.getSelectedItem().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
//                                    Toast.makeText(mContext, "Hasil : " + jsonRESULTS, Toast.LENGTH_LONG).show();
                                    Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(mContext, MainActivity.class));
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
//                                Toast.makeText(mContext, "Error json : "+ e, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(mContext, "Error IO : "+ e, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
