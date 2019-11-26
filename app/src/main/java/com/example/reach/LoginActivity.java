package com.example.reach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    final String TAG="LoginActivitiy";
    ProgressBar progressBar;
    Button loginButton;

    public LoginActivity(Context context){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, " In onCreate()");
        setContentView(R.layout.activity_login);
        loginButton =  findViewById(R.id.button);
        final EditText email = findViewById(R.id.editText);
        final EditText password = findViewById(R.id.editText2);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login login = new Login();
                login.execute(email.getText().toString(),password.getText().toString());
            }
        });
    }
    void disableButton(){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                loginButton.setVisibility(View.INVISIBLE);

            }
        });

    }
    void enableButton(){        runOnUiThread(new Runnable() {

        @Override
        public void run() {

            loginButton.setVisibility(View.VISIBLE);

        }
    });
    }
    void invalidInput(){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast toast = Toast.makeText(LoginActivity.this,"Invalid Username or Password, check dropbox",Toast.LENGTH_LONG);
                toast.show();

            }
        });


    }

    protected class Login extends AsyncTask<String, Integer, String>{
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
        }


        @Override
        protected String doInBackground(String... strings) {

            disableButton();
            String emailinput = strings[0];
            String password = strings [1];
            publishProgress(25);
            String query = "SELECT "+EventDatabaseContract.EventEntry.user_id+" FROM " +EventDatabaseContract.EventEntry.tblUser_name+" WHERE EMAIL = '"+emailinput+"' AND PASSWORD = '"+password+"';";
            EventDatabaseHelper dbhelper = new EventDatabaseHelper(LoginActivity.this);
            SQLiteDatabase database = dbhelper.getReadableDatabase();
            publishProgress(50);
            Cursor cursor = database.rawQuery(query,null);
            if (cursor.moveToFirst()){
                publishProgress(100);
                enableButton();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("email",emailinput);
                startActivity(intent);

            }
            else{
                invalidInput();
                publishProgress(0);
                enableButton();
            }


            return null;
        }
    }
    boolean is_valid(String email, String password){
        if (email.equals("email@gmail.com") && password.equals("1234")){
            return true;
        }
        return false;

    }
}
