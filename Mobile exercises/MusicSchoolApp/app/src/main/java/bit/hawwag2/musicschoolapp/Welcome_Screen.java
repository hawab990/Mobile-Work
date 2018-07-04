package bit.hawwag2.musicschoolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Welcome_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        //Creates method object Mythread and initialises it.
        Thread myThread= new Thread(){

            // This method runs when myThread is called
            @Override
            public void run() {
                try {
                    //5 second intermission and disables user Interaction
                    sleep(5000);
                    Intent intentSplashScreen= new Intent(getApplicationContext(),MainActivity.class);
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
