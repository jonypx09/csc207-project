package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserAuthenticateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authenticate);

        Intent intent = getIntent();
        String userInfo[] = (String[]) intent.getSerializableExtra("userInformation");

        TextView userField = (TextView) findViewById(R.id.username_text);
        userField.setText(userInfo[0]);

        TextView passwordField = (TextView) findViewById(R.id.password_text);
        passwordField.setText(userInfo[1]);
    }
}
