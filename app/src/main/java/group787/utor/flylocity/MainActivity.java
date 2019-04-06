package group787.utor.flylocity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import group787.utor.flylocity.driver.AppEngine;

public class MainActivity extends AppCompatActivity {

    //The main backend driver of the application
    private AppEngine system = new AppEngine();

    private InputStreamReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            reader = new InputStreamReader(getAssets().open("savedFlights.txt"));
//            system.readFlights("savedFlights.txt", reader);
//        }catch(IOException e){
//            e.printStackTrace();
//            e.getMessage();
//        }catch(Exception e){
//            e.printStackTrace();
//            e.getMessage();
//        }
    }

    /**
     * Determines whether the username and password are found in the system.
     * @param username the username to be looked for
     * @param password the password to be looked for
     * @return true if the username and password are in the system, false otherwise
     */
    public boolean isPassword(String username, String password){
        BufferedReader br;
        boolean accepted = false;
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open("passwords.txt")));
            String mLine;
            while ((mLine = br.readLine()) != null) {
                String[] userAndPass = mLine.split(",");
                String userAdmin = "";
                if ((userAndPass[0].length() > 5) && (userAndPass[0].substring(0, 5).equals("ADMIN"))){
                    userAdmin = userAndPass[0].substring(5, userAndPass[0].length());
                    if (username.equals(userAdmin) && password.equals(userAndPass[1])) {
                        accepted = true;
                    }
                }else{
                    if (username.equals(userAndPass[0]) && password.equals(userAndPass[1])) {
                        accepted = true;
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
            e.getMessage();
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
        }finally{
            return accepted;
        }
    }

    /**
     * // Note: an onClick method must return void and have one View parameter.
     * @param view
     */
    public void loginUser(View view) {

        Intent intent = new Intent(this, ClientActivity.class);
        Intent adminIntent = new Intent(this, AdminActivity.class);

        // Gets the user name from the 1st EditText field.
        EditText usernameField = (EditText) findViewById(R.id.username_field);
        String userName = usernameField.getText().toString();

        // Gets the password from the 2nd EditText field.
        EditText passwordField = (EditText) findViewById(R.id.password_field);
        String password = passwordField.getText().toString();

        if (isPassword(userName, password)){
            userName = getUserFromFile(usernameField.getText().toString());
            if (userName.length() > 5){
                if (userName.substring(0, 5).equals("ADMIN")){
                    EditText messageText = (EditText) findViewById(R.id.MessageText);
                    messageText.setText("Welcome Administrator!");
                    String[] userInfo = {userName, password};
                    adminIntent.putExtra("userInformation", userInfo);

                    // Starts ShowActivity.
                    startActivity(adminIntent);
                }else {
                    EditText messageText = (EditText) findViewById(R.id.MessageText);
                    messageText.setText("Welcome");
                    String[] userInfo = {userName, password};
                    intent.putExtra("userInformation", userInfo);
                    intent.putExtra("system", system);
                    // Starts ShowActivity
                    startActivity(intent);
                }
            }else{
                EditText messageText = (EditText) findViewById(R.id.MessageText);
                messageText.setText("Welcome");
                String[] userInfo = {userName, password};
                intent.putExtra("userInformation", userInfo);

                // Starts ShowActivity.
                startActivity(intent);
            }
        }else{
            EditText messageText = (EditText) findViewById(R.id.MessageText);
            messageText.setText("Incorrect Username and Password");
        }
    }

    /**
     * Returns the exact string of the user name from the password.txt file. This is used to
     * verify users with non-administrative rights to the application.
     * @param username the name of the user in the form of a String
     * @return the exact username that is in the passwords.txt file
     */
    public String getUserFromFile(String username){
        BufferedReader br;
        String rawName = "";
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open("passwords.txt")));
            String mLine;
            while ((mLine = br.readLine()) != null) {
                String[] userAndPass = mLine.split(",");
                String userAdmin = "";
                if ((userAndPass[0].length() > 5) && (userAndPass[0].substring(0, 5).equals("ADMIN"))){
                    userAdmin = userAndPass[0].substring(5, userAndPass[0].length());
                }
                if (username.equals(userAdmin)) {
                    rawName = userAndPass[0];
                }
            }
        }catch(IOException e){
            e.printStackTrace();
            e.getMessage();
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
        }finally{
            return rawName;
        }
    }
}
