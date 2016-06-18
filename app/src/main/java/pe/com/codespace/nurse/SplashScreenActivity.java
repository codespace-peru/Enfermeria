package pe.com.codespace.nurse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {

    private static final long SPLASH_SCREEN_DELAY = 1800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Typeface font = Typeface.createFromAsset(getAssets(),"BrushScriptStd.otf");
        TextView textView = (TextView) findViewById(R.id.tvSplash);
        textView.setTypeface(font);


        Thread start_splash = new Thread() {
            @Override
            public void run() {
                try{
                    sleep(SPLASH_SCREEN_DELAY);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        start_splash.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
