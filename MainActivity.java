package com.example.benjamin.beehiveapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


// Bug: Hive View works when no radio buttons are selected.

public class MainActivity extends AppCompatActivity {

    public static int NUMBER_OF_HIVES = 5;
    RadioGroup radioHiveGroup;
    RadioButton radioHiveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // add a bunch of radio buttons, eventually will be about selecting the Hive you want to view.
    public void addHiveButtons(View view){
        for(int row = 0; row<1; row++){
            RadioGroup LL = new RadioGroup(this);
            LL.setOrientation(LinearLayout.VERTICAL);
            for(int i = 1; i<=NUMBER_OF_HIVES; i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setId((row * 2) + i);
                rdbtn.setText(String.format("Hive %d", rdbtn.getId()));
                LL.addView(rdbtn);
            }
            ((ViewGroup) findViewById(R.id.radiogroup)).addView(LL);
        }
    }
    // View the selected hive
    public void viewHive(View view){
        radioHiveGroup = (RadioGroup) findViewById(R.id.radiogroup);
        int selectedId = radioHiveGroup.getCheckedRadioButtonId();
        //if radio button is selected, view that hive's information
        radioHiveButton = (RadioButton) findViewById(selectedId);
        Toast.makeText(MainActivity.this, radioHiveButton.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("radioHiveId", radioHiveButton.getId());
        intent.putExtra("radioHiveText",radioHiveButton.getText());
        startActivity(intent);
    }
    public void sendTests(){

    }
}
