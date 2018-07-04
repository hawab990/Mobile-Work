package bit.hawwag2.photomosaicapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    String mPhotoFileName;
    File mPhotoFile;
    Uri mPhotoFileUri;
    ArrayList<ImageView> images;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<>();

        images.add((ImageView) findViewById(R.id.Image1));
        images.add((ImageView) findViewById(R.id.Image2));
        images.add((ImageView) findViewById(R.id.Image3));
        images.add((ImageView) findViewById(R.id.Image4));

        Button button = (Button)findViewById(R.id.btnClickCamera);
        button.setOnClickListener(new buttonClickHandler());
    }

    public class buttonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //creates time stamped file to hold the image data
            mPhotoFile = createTimeStampedFile();

            //Generate Uri from the File instance
            mPhotoFileUri = Uri.fromFile(mPhotoFile);

            //Create an intent for the image capture content provider
            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            //Attach your Uri to the intent
            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileUri);

            //Launch the intent, waiting for result
            //The user will see the camera app. When they finish, onActivityResult is raised
            startActivityForResult(imageCaptureIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Is this the return we are waiting for?
        if(requestCode == 1)
        {
            //Did we get data?
            if(resultCode == RESULT_OK)
            {
                //we need the file path for BitmapFactory, not the file
                String realFilepath = mPhotoFile.getPath(); //safe way to do this

                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilepath);

                //Do whatever you want with the bitmap here...
                for (ImageView i : images)
                {
                    i.setImageBitmap(userPhotoBitmap);
                }
            }

            else
            {
                Toast.makeText(MainActivity.this, "No photo saved. Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public File createTimeStampedFile() // whenever you are about to launch the camera, call this method
    {
        //fetch system image folder
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //Make the subdirectory
        File imageStorageDirectory = new File(imageRootPath, "CameraDemo1");
        if(!imageStorageDirectory.exists())
        {
            imageStorageDirectory.mkdirs();
        }

        //Get timestamp
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        //make file name
        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        //Make file object from directory and filename
        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + mPhotoFileName);

        //Return the created file object
        return photoFile;
    }
}