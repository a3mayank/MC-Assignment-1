package com.mayankattri.primeornot;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private int value;
    private TextView ques;
    private String quesStr;

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
        quesStr = "Is " + value + " prime or not ?";

        ques = (TextView) getView().findViewById(R.id.TV_ques);
        ques.setText(quesStr);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) rootView.findViewById(R.id.B_correct);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = getActivity().getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                if(isPrime(value)) {
                    Toast toast = Toast.makeText(context, "Wrong Answer :(", duration);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(context, "Correct Answer :)", duration);
                    toast.show();
                }
            }
        });

        Button button1 = (Button) rootView.findViewById(R.id.B_incorrect);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = getActivity().getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                if(isPrime(value)) {
                    Toast toast = Toast.makeText(context, "Correct Answer :)", duration);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(context, "Wrong Answer :(", duration);
                    toast.show();
                }
            }
        });

        Button button3 = (Button) rootView.findViewById(R.id.B_next);
        button3.setOnClickListener(new View.OnClickListener()
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
        int min = 2;
        int max = 1000;

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive

        return rand.nextInt((max - min) + 1) + min;
    }

}
