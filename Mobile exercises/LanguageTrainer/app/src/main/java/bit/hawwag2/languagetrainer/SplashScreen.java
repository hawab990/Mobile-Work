package bit.hawwag2.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Thread myThread= new Thread(){

            // This method runs when myThread is called
            @Override
            public void run() {
                try {
                    //5 second intermission and disables user Interaction
                    sleep(5000);
                    Intent intentSplashScreen= new Intent(getApplicationContext(),QuestionActivity.class);
                    startActivity(intentSplashScreen);
                    //After 5 seconds it will rest
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
