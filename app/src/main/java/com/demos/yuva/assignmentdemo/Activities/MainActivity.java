package com.demos.yuva.assignmentdemo.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.demos.yuva.assignmentdemo.Fragment.ListDataFragment;
import com.demos.yuva.assignmentdemo.R;

/**
 * main activity
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = new ListDataFragment();
            fragmentTransaction.add(R.id.container, fragment);
            fragmentTransaction.commit();
        }

    }

}
