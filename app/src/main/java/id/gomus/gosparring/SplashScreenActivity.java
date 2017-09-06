package id.gomus.gosparring;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah 3 detik
                Intent pindah = new Intent(SplashScreenActivity.this, MainNavigationActivity.class);
                startActivity(pindah);
                finish();
            }
        }, 2000);
    }


    // 6. Override onDestroy()
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 7. Remove any delayed Runnable(s) and prevent them from executing.
        handler.removeCallbacksAndMessages(null);
        // 8. Eagerly clear mHandler allocated memory
        handler = null;
    }
}
