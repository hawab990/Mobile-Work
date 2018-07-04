package hawwag2.alertbuilder;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
SimpleConfirm confirmPizza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowConfirm=(Button) findViewById(R.id.btnOrderPizza);
        btnShowConfirm.setOnClickListener(new CreateFragmentButtonHandler());

    }

    public class CreateFragmentButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v) {

            confirmPizza =new SimpleConfirm();
            FragmentManager fm = getFragmentManager();
            confirmPizza.show(fm, "confirm");
        }
    }


    public void giveMeMyData(boolean orderPizza)
    {

        confirmPizza.dismiss();
        ImageView ivPizza=(ImageView) findViewById(R.id.imageView);

        if(orderPizza)
        {

            ivPizza.setImageResource(R.drawable.pizza);
        }
        else
         ivPizza.setImageResource(R.drawable.sad_face);

    }
}
