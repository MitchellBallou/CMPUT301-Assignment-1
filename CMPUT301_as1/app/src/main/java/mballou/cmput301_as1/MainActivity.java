/*
 * Class Name: MainActivity
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

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * MainActivity
 *
 * @author Mitchell Ballou
 * @version 1.0
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {


    private static final String FILENAME = "file.sav";
    private static final int R_MAP_LOCATION = 1;
    private static final int R_MAP_LOCATION2 = 2;
    private ListView oldCountersList;
    private Counter clickedCounter;


    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldCountersList = (ListView) findViewById(R.id.oldCountersList);
        oldCountersList.setAdapter(adapter);

        Button newCounterButton = (Button) findViewById(R.id.create_a_new_counter_button);

        /** Called when the user presses the Create A New Counter button */
        newCounterButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                setResult(RESULT_OK);
                Intent i = new Intent(MainActivity.this, CounterCreationActivity.class);
                startActivityForResult(i, R_MAP_LOCATION);
            }
        });

        oldCountersList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setResult(RESULT_OK);
                clickedCounter = counters.get(position);
                Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                i.putExtra("parcelable_extra", clickedCounter);
                startActivityForResult(i,R_MAP_LOCATION2);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                Counter newCounter = data.getParcelableExtra("parcelable_extra");
                counters.add(newCounter);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }
    }



    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Counter>(this,R.layout.list_item, counters);
        oldCountersList.setAdapter(adapter);

    }
    public void saveInFile() {
        try{
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();
            fos.close();

        } catch(FileNotFoundException e){
            throw new RuntimeException();
        } catch(IOException e){
            throw new RuntimeException();
        }
    }

    public void loadFromFile() {
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counters = gson.fromJson(in, listType);

        }catch (FileNotFoundException e) {
            counters = new ArrayList<Counter>();
        }catch (IOException e){
            throw new RuntimeException();
        }

    }


}
