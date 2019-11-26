package com.example.reach;
import android.content.Context;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class LoginActivityTest {
    @Mock
    Context mcontext;

    @Mock
    EventDatabaseHelper dbhelper = new EventDatabaseHelper(mcontext);
    @Test
    public void correct_user_password(){

        //Validate the correct password and correct email
        LoginActivity activity = new LoginActivity(mcontext);
        String content = dbhelper.getDatabaseName();
        Log.d("Ignore", "correct_user_password: "+ content);
        assertTrue(activity.is_valid("email@gmail.com","1234"));
    }
    @Test
    public void invalid_user_valid_password(){

        //Validate the correct password and correct email
        LoginActivity activity = new LoginActivity(mcontext);
        String content = dbhelper.getDatabaseName();
        Log.d("Ignore", "correct_user_password: "+ content);
        assertFalse(activity.is_valid("email@yahoo.com","1234"));
    }
    @Test
    public void valid_user_invalid_password(){

        //Validate the correct password and correct email
        LoginActivity activity = new LoginActivity(mcontext);
        String content = dbhelper.getDatabaseName();
        Log.d("Ignore", "correct_user_password: "+ content);
        assertFalse(activity.is_valid("email@gmail.com","5678"));
    }
    @Test
    public void invalid_user_invalid_password(){

        //Validate the correct password and correct email
        LoginActivity activity = new LoginActivity(mcontext);
        String content = dbhelper.getDatabaseName();
        Log.d("Ignore", "correct_user_password: "+ content);
        assertFalse(activity.is_valid("email@yahoo.com","5678"));
    }
}
