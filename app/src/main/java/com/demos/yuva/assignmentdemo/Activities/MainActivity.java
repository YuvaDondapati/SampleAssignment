package com.demos.yuva.assignmentdemo.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.demos.yuva.assignmentdemo.Fragment.ListDataFragment;
import com.demos.yuva.assignmentdemo.R;
import com.demos.yuva.assignmentdemo.impl.CountryDetailsAsyncTask;

public class MainActivity extends AppCompatActivity {

    CountryDetailsAsyncTask asynctask;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null)
        {
            /*
            To make fragment transactions in your activity (such as add, remove, or replace a fragment),
             you must use APIs from FragmentTransaction.You can get an instance of FragmentTransaction from your Activity like this:
            */
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //You can then add a fragment using the add() method, specifying the fragment to add and the view in which to insert it.
            Fragment fragment = new ListDataFragment();
            fragmentTransaction.add(R.id.container, fragment);
            fragmentTransaction.commit();

            //the above 5 steps can be done in a single step like below.
            //getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }

       // asynctask = new CountryDetailsAsyncTask();
        //asynctask.execute();
    }




}
