package bit.hawwag2.languagetranslator;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Pause and continute
        //Assign an instance of the Handler child Class.
        Handler splashHandler=new Handler();
        Runnable launcher=new LaunchMainFromSplash();
        Intent intentSplashScreen = new Intent(getApplicationContext(), MainActivity.class);
        splashHandler.postDelayed(launcher,2000);


    }

    private class LaunchMainFromSplash implements Runnable {
        @Override
        public void run() {
            Intent intent=new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}