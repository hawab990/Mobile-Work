package bit.hawwag2.standupanimationapp;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    ImageView ivClickyPotato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivClickyPotato=(ImageView)findViewById(R.id.ivPotatoMan);
        ivClickyPotato.setOnClickListener(new imageManipulationOnClickHandler());


    }


    private class imageManipulationOnClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Boolean clickedPicture=YoyoThis();
            if (clickedPicture)
            {
                YoYo.with(Techniques.StandUp)
                        .duration(700)
                        .playOn(ivClickyPotato);
                clickedPicture.equals(false);
            }else{

                YoYo.with(Techniques.StandUp)
                        .duration(700)
                        .playOn(ivClickyPotato);
                clickedPicture.equals(true);

            }

        }
    }



   public boolean YoyoThis()
   {

       return true;
   }

}
