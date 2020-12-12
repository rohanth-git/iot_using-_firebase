package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Switch s1,s2,s3,s4;
    SeekBar slider;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1 = findViewById(R.id.switch1);
        s2 = findViewById(R.id.switch2);
        s3 = findViewById(R.id.switch3);
        s4 = findViewById(R.id.switch4);
        slider = findViewById(R.id.seekBar);
        slider.setProgress(2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Switch");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("s0").getValue(String.class);
                if(value.equals("ON")){
                    s1.setChecked(true);
                }
                else{
                    s1.setChecked(false);
                }
                Log.d(TAG, "Value is: " + value);
                String value2 = dataSnapshot.child("s1").getValue(String.class);
                if(value2.equals("ON")){
                    s2.setChecked(true);
                }
                else{
                    s2.setChecked(false);
                }
                Log.d(TAG, "Value2 is: " + value2);
                String value3 = dataSnapshot.child("s2").getValue(String.class);
                if(value3.equals("ON")){
                    s3.setChecked(true);
                }
                else{
                    s3.setChecked(false);
                }
                Log.d(TAG, "Value is: " + value3);
                String value4 = dataSnapshot.child("s3").getValue(String.class);
                if(value4.equals("ON")){
                    s4.setChecked(true);
                }
                else{
                    s4.setChecked(false);
                }
                Log.d(TAG, "Value4 is: " + value4);
                int sil =dataSnapshot.child("Slider").getValue(int.class);
                slider.setProgress(sil);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Switch");
                String msg = isChecked ? "ON" : "OFF";
                /*opnvar.switch1 = msg;
                opnvar.setdata();*/
                myRef.child("s0").setValue(msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                s1.setText("stitch1 "+msg);
            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Switch");
                String msg = isChecked ? "ON" : "OFF";
                /*opnvar.switch1 = msg;
                opnvar.setdata();*/
                myRef.child("s1").setValue(msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                s2.setText("stitch2 "+msg);
            }
        });
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Switch");
                String msg = isChecked ? "ON" : "OFF";
                /*opnvar.switch1 = msg;
                opnvar.setdata();*/
                myRef.child("s2").setValue(msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                s3.setText("stitch3 "+msg);
            }
        });
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Switch");
                String msg = isChecked ? "ON" : "OFF";
                /*opnvar.switch1 = msg;
                opnvar.setdata();*/
                myRef.child("s3").setValue(msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                s4.setText("stitch4 "+msg);
            }
        });
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Switch");
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Write code to perform some action when progress is changed.

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Write code to perform some action when touch is started.

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Write code to perform some action when touch is stopped.
                int posi = seekBar.getProgress();
                Toast.makeText(MainActivity.this, "Current value is " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
                myRef.child("Slider").setValue(posi);


            }
        });

    }
}