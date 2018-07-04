package bit.hawwag2.welcometodunedinapp;


import android.app.DialogFragment;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogZoom extends DialogFragment {


    public DialogZoom() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View zoomView=inflater.inflate(R.layout.fragment_dialog_zoom,container, false);

         int imageResourceID=getArguments().getInt("imageID");
        ImageView imgZoom=(ImageView) zoomView.findViewById(R.id.ivImgZoom);
        imgZoom.setImageResource(imageResourceID);

        // Inflate the layout for this fragment
        return zoomView;
    }


}
