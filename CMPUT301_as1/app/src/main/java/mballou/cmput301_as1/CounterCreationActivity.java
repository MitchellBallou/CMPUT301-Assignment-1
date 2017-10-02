/*
 * Class Name: CounterCreationActivity
 *
 * Version: Version 1.0
 *
 * Date: October 1st, 2017
 *
 * Copyright (c) Team X, CMPUT301, University of Alberta - All Rights Reserved. You may use,
 * distribute, or modify this code under terms and conditions of the Code of Students Behavior at
 * University of Alberta
 */

package mballou.cmput301_as1;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import static mballou.cmput301_as1.R.styleable.View;

/**
 * CounterCreationActivity
 *
 * @author Mitchell Ballou
 * @version 1.0
 * @since 1.0
 */
public class CounterCreationActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText comment;
    private EditText iValue;
    private Counter newCounter;

    /**
     * Creates counter objects then parcels and sends them to main
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_creation);

        Button cCButton = (Button) findViewById(R.id.create_counter_button);
        nameText = (EditText) findViewById(R.id.name_text);
        comment = (EditText) findViewById(R.id.enter_comment);
        iValue = (EditText) findViewById(R.id.initial_value);



        /** Called when the user presses the Create Counter button */
        cCButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);

                String name = nameText.getText().toString();
                int value = Integer.parseInt(iValue.getText().toString());
                String com = comment.getText().toString();

                if(com != null) {
                    newCounter = new  Counter(name, value, com);
                }
                else{
                    newCounter = new  Counter(name, value);
                }

                newCounter.setInitialValue(value);
                newCounter.setCurrentValue(value);

                Intent i = getIntent();
                i.putExtra("parcelable_extra", newCounter);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}