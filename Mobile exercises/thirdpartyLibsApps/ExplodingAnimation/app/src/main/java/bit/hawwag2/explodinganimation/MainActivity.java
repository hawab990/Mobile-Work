package bit.hawwag2.explodinganimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivDeathStar=(ImageView)findViewById(R.id.imageView);
        ivDeathStar.setOnClickListener(new onclickHandler());
    }

    private class onclickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            new ExplodeAnimation(v).animate();
        }
    }
}
