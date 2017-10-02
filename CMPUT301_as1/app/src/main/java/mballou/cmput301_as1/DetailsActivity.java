/*
 * Class Name: DetailsActivity
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

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Details Activity
 *
 * @author Mitchell Ballou
 * @version 1.0
 * @since 1.0
 */
public class DetailsActivity extends AppCompatActivity {
    private TextView iValue;
    private TextView cValue;
    private TextView name;
    private Button incButton;
    private Button decButton;
    private Button resetButton;
    private Button deleteButton;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final Counter currentCounter = (Counter) getIntent().getParcelableExtra("parecelable_extra");
        Button incButton = (Button) findViewById(R.id.details_inc);
        Button decButton = (Button) findViewById(R.id.details_dec);
        Button resetButton = (Button) findViewById(R.id.details_reset);
        Button deleteButton = (Button) findViewById(R.id.details_delete);
        Button returnButton = (Button) findViewById(R.id.details_return);

        iValue.setText(currentCounter.getInitialValue());
        cValue.setText(currentCounter.getCurrentValue());
        name.setText(currentCounter.getName());

        iValue = (TextView) findViewById(R.id.details_i_value);
        cValue = (TextView) findViewById(R.id.details_c_value);
        name = (TextView) findViewById(R.id.details_name);

        incButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setResult(RESULT_OK);
                currentCounter.incrementCurrentValue();

            }
        });
        decButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setResult(RESULT_OK);
                currentCounter.decrementCurrentValue();

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setResult(RESULT_OK);
                currentCounter.setCurrentValue(currentCounter.getInitialValue());

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setResult(RESULT_OK);


            }
        });

        returnButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = getIntent();
                setResult(RESULT_OK, i);
                finish();
            }
        });


    }


}
