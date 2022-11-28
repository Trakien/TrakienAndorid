package co.trakien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import co.trakien.data.model.LoggedInUser;

public class HomeActivity extends AppCompatActivity {

    EditText email;
    String em;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        email = findViewById(R.id.editTextTextPersonName);
        em = getIntent().getStringExtra("email");
        email.setText(em);


    }
}