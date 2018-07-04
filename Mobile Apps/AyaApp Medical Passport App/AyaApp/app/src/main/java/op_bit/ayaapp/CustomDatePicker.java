package op_bit.ayaapp;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomDatePicker extends DialogFragment
implements DatePickerDialog.OnDateSetListener {
    public ViewHealthInformationData HealthINformationData;

    XmlParser xmlParser;
    int  year;
    int  month;
    int  day;
    static final int PICK_DATE_REQUEST=1;//REQUESTcODE

    public CustomDatePicker() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Calendar c = Calendar.getInstance();
          year=c.get(Calendar.YEAR);
          month=c.get(Calendar.MONTH);
          day=c.get(Calendar.DAY_OF_MONTH);



        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,this, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



return dialog;


    }

    @Override
    // Do something with the date chosen by the user
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(c.getTime());
        EditHealthInformation getInformaion=new EditHealthInformation();
        Bundle currentDate=new Bundle();
        currentDate.putString("formatedDate",formattedDate);





    }


}
