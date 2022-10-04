package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    Button btnOk;
    EditText etFullname, etEmail, etPass, etConfirmPass, etHomepage, etAbout;
    private ImageView ivProfil;
    private CircleImageView avatar, change_Avatar;
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private Bitmap bitmap;
    private Uri imgUri = null;
    private boolean change_img = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnOk = findViewById(R.id.button_ok);
        etFullname = findViewById(R.id.text_fullname);
        etEmail = findViewById(R.id.text_email);
        etPass = findViewById(R.id.text_password);
        etConfirmPass = findViewById(R.id.text_confirm_password);
        etHomepage = findViewById(R.id.text_homepage);
        etAbout = findViewById(R.id.text_about);
        ivProfil = findViewById(R.id.imageView);
        avatar = findViewById(R.id.image_profile);

        ivProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                String image = imgUri.toString();
                intent.putExtra("image", image);
                intent.putExtra("fullname", etFullname.getText().toString());
                intent.putExtra("email", etEmail.getText().toString());
                intent.putExtra("pass", etPass.getText().toString());
                intent.putExtra("conf_pass", etConfirmPass.getText().toString());
                intent.putExtra("homepage", etHomepage.getText().toString());
                intent.putExtra("about", etAbout.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Cancel input image", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (requestCode == GALLERY_REQUEST_CODE) {
                if (data != null) {
                    try {
                        change_img = true;
                        imgUri = data.getData();
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);
                        avatar.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        }
    }
}