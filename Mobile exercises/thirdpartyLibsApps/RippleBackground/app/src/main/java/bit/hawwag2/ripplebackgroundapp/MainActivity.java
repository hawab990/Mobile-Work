package bit.hawwag2.ripplebackgroundapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {
    RippleBackground rippleBackground;
    boolean clicked=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        imageView.setOnClickListener(new onclickHandler());

    }

    public class onclickHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {
            if(clicked==true)
            {
                //when clicked Ripple
                rippleBackground.startRippleAnimation();
                //Set it to false
                clicked= false;
            }
           else
            {
                
                rippleBackground.stopRippleAnimation();
                clicked=true;


            }


        }
    }
}
