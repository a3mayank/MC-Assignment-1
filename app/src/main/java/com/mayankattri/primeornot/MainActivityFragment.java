package com.mayankattri.primeornot;

import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private int value;
    private TextView ques;
    private String quesStr;
    private String msg = "Message : ";

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null || !savedInstanceState.containsKey("valueKey")) {
            value = randomNumber();
        }
        else {
            value = savedInstanceState.getInt("valueKey");
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("valueKey", value);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(msg, " OnStart invoked");
        quesStr = "Is " + value + " prime or not ?";

        ques = (TextView) getView().findViewById(R.id.TV_ques);
        ques.setText(quesStr);
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(msg, " OnResume invoked");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(msg, " OnPause invoked");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(msg, " OnStop invoked");
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(msg, " OnDestroy invoked");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button buttonYes = (Button) rootView.findViewById(R.id.B_correct);
        buttonYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(isPrime(value)) {
                    Snackbar mSnackbar = Snackbar.make(v, "You are Wrong :(", Snackbar.LENGTH_LONG);
                    View mView = mSnackbar.getView();
                    TextView mTextView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mSnackbar.show();
                }
                else {
                    Snackbar mSnackbar = Snackbar.make(v, "You are Right :)", Snackbar.LENGTH_LONG);
                    View mView = mSnackbar.getView();
                    TextView mTextView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mSnackbar.show();
                }
            }
        });

        Button buttonNo = (Button) rootView.findViewById(R.id.B_incorrect);
        buttonNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(isPrime(value)) {
                    Snackbar mSnackbar = Snackbar.make(v, "You are Right :)", Snackbar.LENGTH_LONG);
                    View mView = mSnackbar.getView();
                    TextView mTextView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mSnackbar.show();
                }
                else {
                    Snackbar mSnackbar = Snackbar.make(v, "You are Wrong :(", Snackbar.LENGTH_LONG);
                    View mView = mSnackbar.getView();
                    TextView mTextView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                        mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    else
                        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                    mSnackbar.show();
                }
            }
        });

        Button buttonNext = (Button) rootView.findViewById(R.id.B_next);
        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                value = randomNumber();
                quesStr = "Is " + value + " prime or not ?";
                ques.setText(quesStr);
            }
        });

        return rootView;
    }

    private boolean isPrime(int x) {
        int count = 0;

        for (int i = 1; i <= x; i++) {
            if (x % i == 0) {
                count++;
            }
        }

        if (count == 2) {
            Log.v("isPrime: " + value, "yes");
            return true;
        }
        else {
            Log.v("isPrime: " + value, "no");
            return false;
        }
    }

    private int randomNumber() {
        Random rand = new Random();
        int min = 1;
        int max = 1000;

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive

        return rand.nextInt((max - min) + 1) + min;
    }

}
