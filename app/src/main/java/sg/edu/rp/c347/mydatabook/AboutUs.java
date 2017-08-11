package sg.edu.rp.c347.mydatabook;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AboutUs extends AppCompatActivity {
    ActionBar ab;
    TextView tvBottom,tvTop;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        tvBottom = (TextView)findViewById(R.id.textViewBottom);
        tvTop = (TextView)findViewById(R.id.textViewTop);
        iv = (ImageView)findViewById(R.id.imageView);

        tvTop.setText("Created By : Ah Chong, Peter, Mary");
        String imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/80/Republic_Polytechnic_Logo.jpg";
        Picasso.with(this).load(imageUrl).into(iv);
        tvBottom.setText("C347 Android Programming II Republic Polytechnic");
    }
}
