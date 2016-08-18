package com.mayankattri.primeornot;

import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
    private TextView question_View;
    private String question_String;
    private static final String CORRECT_ANSWER = "Correct Answer :)";
    private static final String INCORRECT_ANSWER = "Incorrect Answer :(";
    private static final String CURRENT_VALUE = "Current Value";
    private String MESSAGE = "Activity Message : ";
    // ArrayList of images for background.
    ArrayList<Integer> myImageList = new ArrayList<>();
    // index for images arraylist
    private int image_index = 0;

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

        question_String = "Is " + randomInt + " a prime number ?";

        question_View = (TextView) rootView.findViewById(R.id.TV_ques);
        question_View.setText(question_String);

        // adding the background images into the ArrayList
        myImageList.add(R.drawable.a);
        myImageList.add(R.drawable.b);
        myImageList.add(R.drawable.c);
        myImageList.add(R.drawable.d);
        myImageList.add(R.drawable.e);
        myImageList.add(R.drawable.f);

        Button buttonYes = (Button) rootView.findViewById(R.id.B_correct);
        Button buttonNo = (Button) rootView.findViewById(R.id.B_incorrect);
        Button buttonNext = (Button) rootView.findViewById(R.id.B_next);

        rootView.setBackgroundResource(myImageList.get(image_index++));

        // when "Yes" button is clicked, it tells if the answer is correct or incorrect.
        buttonYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isPrime(randomInt)) {
                    showAnswer(v, INCORRECT_ANSWER);
                }
                else {
                    showAnswer(v, CORRECT_ANSWER);
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
                    showAnswer(v, CORRECT_ANSWER);
                }
                else {
                    showAnswer(v, INCORRECT_ANSWER);
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
                question_String = "Is " + randomInt + " a prime number ?";
                question_View.setText(question_String);

                // if all images displayed in background, then again start from the first image.
                if(image_index == myImageList.size()) {
                    image_index = 0;
                }
                // setting background image.
                rootView.setBackgroundResource(myImageList.get(image_index));
                image_index++;
            }
        });

        return rootView;
    }

    // shows the response inside a snackbar.
    private void showAnswer(View v, String response) {
        Snackbar mSnackbar = Snackbar.make(v, response, Snackbar.LENGTH_LONG);
        View mView = mSnackbar.getView();
        TextView mTextView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
        // size of text in snackbar
        mTextView.setTextSize( 30 );

        if(response.contains("Incorrect")) {
            // set snackbar colour to RED if answer is incorrect.
            mView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.RED));
        } else {
            // set snackbar colour to GREEN if answer is correct.
            mView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.GREEN));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // "setTextAlignment" available from API Level 17, You can't use it before 17.
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        else {
            // "setGravity" available from API Level 1. You can use it anywhere.
            mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        mSnackbar.show();
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
