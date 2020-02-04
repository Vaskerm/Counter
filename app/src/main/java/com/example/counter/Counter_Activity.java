package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Counter_Activity extends AppCompatActivity {

    private static final String P_Value="P_Value";
    private TextView mValueTv;
    private SharedPreferences sPrefers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_counter);
            sPrefers= PreferenceManager.getDefaultSharedPreferences(this);
            mValueTv = findViewById(R.id.value);
            updateValue(getValue());
            findViewById(R.id.increase).setOnClickListener(v ->{ updateValue(getValue()+1);
            });

            findViewById(R.id.reduce).setOnClickListener(v->{ updateValue(getValue()-1);
            });
            findViewById(R.id.reset).setOnClickListener(v -> {int oldValue=getValue();
                updateValue(0);
                Snackbar.make(v, "Counter was rest", Snackbar.LENGTH_SHORT)
                    .setAction("Undo", ignored ->updateValue(oldValue))
                    .show();
            });
            }
    private void updateValue(int newValue){

        sPrefers.edit().putInt(P_Value,newValue).apply();
        mValueTv.setText(String.valueOf(newValue));

    }
    private int getValue(){
        return sPrefers.getInt(P_Value,0);
    }
}