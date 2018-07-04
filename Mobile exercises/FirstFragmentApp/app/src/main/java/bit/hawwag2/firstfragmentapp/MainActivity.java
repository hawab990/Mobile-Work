package bit.hawwag2.firstfragmentapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabletmode);

        Button btndisplayImageFragment=(Button)findViewById(R.id.btnImgFragment);
        btndisplayImageFragment.setOnClickListener(new onClickImageViewHandler());

        Button btnDisplayListViewFragment=(Button)findViewById(R.id.btnListFragment);
        btnDisplayListViewFragment.setOnClickListener(new onClickListViewHandler());


    }

    public class onClickImageViewHandler implements View.OnClickListener
    {


        @Override
        public void onClick(View v) {

            Fragment dynamicFragment= new DisplayImage();
            //Instantiating the fragmenrt Manager
            //Get the fragment Manager Object
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();


            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.PlaceHolder1, dynamicFragment);
            ft.commit();

        }
    }

    public class onClickListViewHandler implements View.OnClickListener
    {


        @Override
        public void onClick(View v) {


            Fragment dynamicFragment= new DisplayListView();
            //Instantiating the fragmenrt Manager
            //Get the fragment Manager Object
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();


            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.PlaceHolder2, dynamicFragment);
            ft.commit();

        }
    }



}
