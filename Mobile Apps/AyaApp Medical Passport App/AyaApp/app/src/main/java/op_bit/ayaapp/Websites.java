package op_bit.ayaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Websites extends AppCompatActivity {

    TextView tvCanteen;
    TextView tvTeensCancer;
    TextView tvCancerNZ;
    TextView tvHealth;
    TextView tvSouthernDHB;
    TextView tvAfterCure;
    TextView tvSmokeFree;
    TextView tvSeventyK;
    TextView tvCancerSurvivor;
    TextView tvStupidCanver;
    TextView tvFertileHope;
    TextView tvSouthenPHO;
    TextView tvWhatNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_with_arrow_and_edit_edgedcurve);

        setUpTextViews();
        setOnClicks();
        setupListeners();
        setupButtonClicks();


    }
    public void setUpTextViews()
    {
        tvCanteen=(TextView)findViewById(R.id.tv_canteen);
        tvTeensCancer=(TextView)findViewById(R.id.tv_teensLivingWithCancer);
        tvCancerNZ=(TextView)findViewById(R.id.tv_cancerNZ);
        tvHealth=(TextView)findViewById(R.id.tv_health);
        tvSouthernDHB=(TextView)findViewById(R.id.tv_southernDHB);
        tvAfterCure=(TextView)findViewById(R.id.tv_afterCure);
        tvSmokeFree=(TextView)findViewById(R.id.tv_smokeFree);
        tvSeventyK=(TextView)findViewById(R.id.tv_seventyK);
        tvCancerSurvivor=(TextView)findViewById(R.id.tv_cancerSurvivor);
        tvStupidCanver=(TextView)findViewById(R.id.tv_stupidCancer);
        tvFertileHope=(TextView)findViewById(R.id.tv_fertileHope);
        tvSouthenPHO=(TextView)findViewById(R.id.tv_southernPHO);
        tvWhatNext=(TextView)findViewById(R.id.tv_whatsNext);
    }


public void setupButtonClicks()
{
    Button btnGoback=(Button)findViewById(R.id.btn_Back);
    Button btnGoMenu=(Button)findViewById(R.id.btn_menu);
    Button btnGoHome=(Button)findViewById(R.id.btn_home);
    btnGoHome.setOnClickListener(new ButtonClickListener());
    btnGoMenu.setOnClickListener(new ButtonClickListener());
    btnGoback.setOnClickListener(new onClickBackListiner());
}



public void setupListeners()
{
}
    public void setOnClicks(){
        tvCanteen .setMovementMethod(LinkMovementMethod.getInstance());
        tvTeensCancer.setMovementMethod(LinkMovementMethod.getInstance());
        tvCancerNZ .setMovementMethod(LinkMovementMethod.getInstance());
        tvHealth.setMovementMethod(LinkMovementMethod.getInstance());
        tvSouthernDHB.setMovementMethod(LinkMovementMethod.getInstance());
        tvAfterCure.setMovementMethod(LinkMovementMethod.getInstance());
        tvSmokeFree.setMovementMethod(LinkMovementMethod.getInstance());
        tvSeventyK.setMovementMethod(LinkMovementMethod.getInstance());
        tvCancerSurvivor.setMovementMethod(LinkMovementMethod.getInstance());
        tvStupidCanver .setMovementMethod(LinkMovementMethod.getInstance());
        tvFertileHope.setMovementMethod(LinkMovementMethod.getInstance());
        tvSouthenPHO.setMovementMethod(LinkMovementMethod.getInstance());
        tvWhatNext.setMovementMethod(LinkMovementMethod.getInstance());


    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i=new Intent();
            switch(view.getId())
            {
                case R.id.btn_home:
                     i=new Intent(Websites.this, Home.class);
                    break;
                case R.id.btn_menu:
                    i=new Intent(Websites.this, MainActivity.class);
                    break;
            }
              startActivity(i);
        }
    }

    private class onClickBackListiner implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();

        }
    }
}






