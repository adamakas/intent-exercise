package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    TextView tvFullname, tvAbout, tvEmail, tvHomepage;
    private CircleImageView image;
    Button btnVisit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // get view
        tvAbout = findViewById(R.id.label_about);
        tvFullname = findViewById(R.id.label_fullname);
        tvEmail = findViewById(R.id.label_email);
        tvHomepage = findViewById(R.id.label_homepage);
        btnVisit = findViewById(R.id.button_homepage);
        image = findViewById(R.id.image_profile);

        // get extra
        String about = getIntent().getExtras().getString("about");
        String fullname = getIntent().getExtras().getString("fullname");
        String email = getIntent().getExtras().getString("email");
        final String homepage = getIntent().getExtras().getString("homepage");
        String uri = getIntent().getExtras().getString("image");

        // set text
        image.setImageURI(Uri.parse(uri));
        tvAbout.setText(about);
        tvFullname.setText(fullname);
        tvEmail.setText(email);
        tvHomepage.setText(homepage);

        btnVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoURL(homepage);
            }
        });
    }
    private void gotoURL(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}