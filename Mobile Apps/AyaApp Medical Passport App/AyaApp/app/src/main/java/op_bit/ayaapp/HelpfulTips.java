package op_bit.ayaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpfulTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpful_tips);

        TextView t2 = (TextView) findViewById(R.id.textViewTips);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
