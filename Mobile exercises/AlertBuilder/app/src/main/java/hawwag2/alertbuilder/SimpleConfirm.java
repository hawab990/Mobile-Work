package hawwag2.alertbuilder;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleConfirm extends DialogFragment {


    public SimpleConfirm() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Pizza");

        builder.setIcon(R.drawable.pizza);

        yesButtonDialogue yesButtonHandler = new yesDialogHandler();
        NoButtonDialogue NobButtonHandler = new NoDialogHandler();


        alertDialogBuilder.setNegativeButton("No", new DialogInterface().OnClickListener());

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface().OnClickListener());

    }




    }
}
