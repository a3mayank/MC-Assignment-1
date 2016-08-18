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

import java.util.ArrayList;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private int randomInt;
    private int image_count = 0;
    private TextView ques;
    private String quesStr;
    public static final String CURRENT_VALUE = "Current Value";
    private String MESSAGE = "Activity Message : ";

    ArrayList<Integer> myImageList = new ArrayList<>();

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null || !savedInstanceState.containsKey(CURRENT_VALUE)) {
            randomInt = randomNumber();
        }
        else {
            randomInt = savedInstanceState.getInt(CURRENT_VALUE);
        }
//        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Called when the layout should save its state.
        // This saves configuration and anything else that may be required to restore.
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_VALUE, randomInt);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(MESSAGE, " OnStart invoked");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(MESSAGE, " OnResume invoked");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(MESSAGE, " OnPause invoked");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(MESSAGE, " OnStop invoked");
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(MESSAGE, " OnDestroy invoked");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        quesStr = "Is " + randomInt + " prime or not ?";

        ques = (TextView) rootView.findViewById(R.id.TV_ques);
        ques.setText(quesStr);

        myImageList.add(R.drawable.a);
        myImageList.add(R.drawable.b);
        myImageList.add(R.drawable.c);
        myImageList.add(R.drawable.d);
        myImageList.add(R.drawable.e);
        myImageList.add(R.drawable.f);

        Button buttonYes = (Button) rootView.findViewById(R.id.B_correct);
        Button buttonNo = (Button) rootView.findViewById(R.id.B_incorrect);
        Button buttonNext = (Button) rootView.findViewById(R.id.B_next);

        // when "Yes" button is clicked, it tells if the answer is correct or incorrect.
        buttonYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isPrime(randomInt)) {
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

        // when "No" button is clicked, it tells if the answer is correct or incorrect.
        buttonNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isPrime(randomInt)) {
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

        // when "Next" button is clicked, value of randomInt is changed
        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                randomInt = randomNumber();
                quesStr = "Is " + randomInt + " prime or not ?";
                ques.setText(quesStr);
                if(image_count == 6) {
                    image_count = 0;
                }
                rootView.setBackgroundResource(myImageList.get(image_count));
                image_count++;
            }
        });

        return rootView;
    }

    // checks if a number is prime or not
    private boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                Log.v("isPrime: " + randomInt, " no");
                return false;
            }
        }
        Log.v("isPrime: " + randomInt, " yes");
        return true;
    }

    // returns a random integer between 1 and 1000 inclusive.
    private int randomNumber() {
        Random rand = new Random();
        int min = 1;
        int max = 1000;

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }

}
