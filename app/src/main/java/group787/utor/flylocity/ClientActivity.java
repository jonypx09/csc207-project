package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import group787.utor.flylocity.driver.AppEngine;

public class ClientActivity extends AppCompatActivity {

    private AppEngine system;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Intent intent = getIntent();
        system = (AppEngine) intent.getSerializableExtra("system");
    }
}
