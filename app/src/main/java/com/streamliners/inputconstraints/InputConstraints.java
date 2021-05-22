package com.streamliners.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.streamliners.inputconstraints.databinding.ActivityInputBinding
        ;
import com.streamliners.inputconstraints.databinding.ActivityInputConstaintsBinding;
import android.content.Intent;
import android.text.Editable;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

public class InputConstraints extends AppCompatActivity {

    // request code of data transfer
    private static final int REQUEST_INPUT = 0;
    ActivityInputConstaintsBinding bind;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInputConstaintsBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        // set title of the activity
        setTitle("Input Constraints Activity");

        //Send constraint
        sendConstraints();
    }

    private void inputConstraints(){
        //Initialize bundle
        bundle = new Bundle();

        //To check user select upperCase constraint
        if(bind.uppercaseCheckBox.isChecked()){
            bundle.putString(Constants.UPPERCASE_ALPHABETS, "true");
        }

        //To check user select lowerCase constraint
        if(bind.lowercaseCheckBox.isChecked()){
            bundle.putString(Constants.LOWERCASE_ALPHABETS, "true");
        }

        //To check user select digit constraint
        if(bind.digitsCheckBox.isChecked()){
            bundle.putString(Constants.DIGITS, "true");
        }

        //To check user select operators constraint
        if(bind.operationsCheckBox.isChecked()){
            bundle.putString(Constants.MATHEMATICAL_OPERATORS, "true");
        }

        //To check user select symbol constraint
        if(bind.symbolsCheckBox.isChecked()){
            bundle.putString(Constants.OTHER_SYMBOLS, "true");
        }
    }

    //Send constraints
    private void sendConstraints() {

        bind.btnInput.setOnClickListener(v -> {

            //To put the constraints in bundle
            inputConstraints();

            //Check bundle
            if(bundle.isEmpty()){
                Toast.makeText(this, "Select constraints", Toast.LENGTH_SHORT).show();
                return;
            }

            //Send Constraints to InputActivity
            Intent intent = new Intent(this, Input.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_INPUT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check result
        if(requestCode == REQUEST_INPUT && resultCode == RESULT_OK){
            bind.textView.setText("Result is : " + data.getStringExtra(Constants.INPUT_DATA));
            bind.textView.setVisibility(View.VISIBLE);
        }
    }
}