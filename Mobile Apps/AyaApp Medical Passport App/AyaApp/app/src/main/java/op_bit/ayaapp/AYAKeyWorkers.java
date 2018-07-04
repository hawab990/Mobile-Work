package op_bit.ayaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AYAKeyWorkers extends AppCompatActivity {

    TextView tvAuckland;
    TextView tvMidland;
    TextView tvMidCentral;
    TextView tvCapital;
    TextView tvCanterbury;
    TextView tvSouthern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayakey_workers);

        setUpTextViews();
        setOnClicks();
        setUpDefaultButtons();
    }

    public void setUpTextViews(){
        tvAuckland = (TextView)findViewById(R.id.tv_Auckland);
        tvMidCentral = (TextView)findViewById(R.id.tv_MidCentral);
        tvMidland = (TextView)findViewById(R.id.tv_Midland);
        tvCapital = (TextView)findViewById(R.id.tv_Capital);
        tvCanterbury = (TextView)findViewById(R.id.tv_Canterbury);
        tvSouthern = (TextView)findViewById(R.id.tv_Southern);
    }

    public void setOnClicks(){

        tvAuckland.setMovementMethod(LinkMovementMethod.getInstance());
        tvMidCentral.setMovementMethod(LinkMovementMethod.getInstance());
        tvMidland.setMovementMethod(LinkMovementMethod.getInstance());
        tvCapital.setMovementMethod(LinkMovementMethod.getInstance());
        tvCanterbury.setMovementMethod(LinkMovementMethod.getInstance());
        tvSouthern.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setUpDefaultButtons(){
        Button btnHome = (Button)findViewById(R.id.btn_home);
        Button btnBack = (Button)findViewById(R.id.btn_Back);
        Button btnMenu = (Button)findViewById(R.id.btn_menu);

        btnHome.setOnClickListener(new ButtonListener());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnMenu.setOnClickListener(new ButtonListener());
    }

    public class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent i = new Intent();
            switch(view.getId()){
                case R.id.btn_home:
                    i = new Intent(AYAKeyWorkers.this, MainActivity.class);
                    break;
                case R.id.btn_menu:
                    i = new Intent(AYAKeyWorkers.this, MainActivity.class);
                    break;
            }
            startActivity(i);
        }
    }
}
