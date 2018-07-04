package bit.hawwag2.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //retrieve data
        Intent launchedIntent =getIntent();

        int totalScore=launchedIntent.getIntExtra("userFinalScore",0);

        //Getting references and setting textView
        TextView textViewFinalScore=(TextView)findViewById(R.id.textViewScore);
        textViewFinalScore.setText("Final Score is:"+"\n"+totalScore+"/11");

        //Getting references and setting buttons
        Button btnPlayAgain=(Button)findViewById(R.id.ButtonAgain);
        btnPlayAgain.setOnClickListener(new onClickHandlerBack());






    }
    public class onClickHandlerBack implements View.OnClickListener
    {


        @Override
        public void onClick(View v) {
            Intent backToSplash= new Intent(Score.this,SplashScreen.class);
            startActivity(backToSplash);
        }
    }

}
