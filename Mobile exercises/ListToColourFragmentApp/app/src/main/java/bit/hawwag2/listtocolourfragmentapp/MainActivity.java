package bit.hawwag2.listtocolourfragmentapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView ivAnimalDisplay;
    List_dialogue_view chooseAnimalFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnChooseImage= (Button) findViewById(R.id.btnChooseImage);
        btnChooseImage.setOnClickListener(new createFragmentButtonHandler());


        // Need to create the  fragment manager, to place the object in the fragholder placeholder.
        //Instantiating the fragmenrt Manager
        //Get the fragment Manager Object
        Fragment dynamicFragment= new show_image_fragment();
        //Instantiating the fragmenrt Manager
        //Get the fragment Manager Object
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();


        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Fragholder, dynamicFragment);
        ft.commit();


}


    //A string to check for the right choice value.

    public void giveMeMyData(String ChooseImage)
    {
        chooseAnimalFragment.dismiss();

        ivAnimalDisplay=(ImageView) findViewById(R.id.ivShowCreature);
        ivAnimalDisplay.setImageResource(android.R.color.transparent);
        if(ChooseImage=="Meerkats")
        {
                ivAnimalDisplay.setImageResource(R.drawable.meerkats);
        }
        if(ChooseImage=="Alpacas")
        {
            ivAnimalDisplay.setImageResource(R.drawable.alpacas);
        }
        if(ChooseImage=="Panda")
        {
            ivAnimalDisplay.setImageResource(R.drawable.panda);
        }
        if(ChooseImage=="Manatee")
        {
            ivAnimalDisplay.setImageResource(R.drawable.manatee);
        }

    }
public class createFragmentButtonHandler implements View.OnClickListener
    {


        @Override
        public void onClick(View v) {
            //get the fragment manager

            //Create an instance of the fragment
            chooseAnimalFragment= new List_dialogue_view();
            FragmentManager fm =getSupportFragmentManager();
            chooseAnimalFragment.show(fm,"a");







        }
    }


}
