package bit.hawwag2.pizzaalertapp;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.R.string.*;



/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleConfirm extends DialogFragment {


    public SimpleConfirm() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(
            Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm Order?");


        builder.setIcon(R.drawable.pizza);


        // Creating an instance of the Alert.Builder Dialog buttons
        AlertDialog.Builder builderDialogNo= builder.setNegativeButton(android.R.string.no, new noButtonHandler());

        AlertDialog.Builder builderDialogYes= builder.setPositiveButton(android.R.string.yes, new yesButtonHandler());




        return builderDialogNo.create();

/*
//Anonymous in Line Class No no.

       builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity myActivity= (MainActivity) getActivity();
                myActivity.giveMeMyData(false);

            }
        });


        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog, int which) {

                MainActivity myActivity= (MainActivity) getActivity();
                myActivity.giveMeMyData(true);

            }
        });




        return builder.create();
    }
*/
    } //Oncreat


    public class yesButtonHandler implements Dialog.OnClickListener
    {


        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity myActivity= (MainActivity) getActivity();
            myActivity.giveMeMyData(true);

        }
    }

    public class noButtonHandler implements Dialog.OnClickListener
    {


        @Override
        public void onClick(DialogInterface dialog, int which) {
            MainActivity myActivity= (MainActivity) getActivity();
            myActivity.giveMeMyData(false);
        }
    }




}
