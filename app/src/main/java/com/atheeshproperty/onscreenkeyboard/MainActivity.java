package com.atheeshproperty.onscreenkeyboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText firstNumber, secondNumber;
    private ImageButton forwardButton, backwardButton, deleteButtonOne, deleteButtonTwo;
    private Button numberOne, numberTwo, numberThree, numberFour, numberFive,
            numberSix, numberSeven, numberEight, numberNine, numberZero;

    String theNumber = "";
    int previousOne = 0;
    int currentlySelectedEditText = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ui);

        //Initializing the Edit Text
        firstNumber = findViewById(R.id.numberTextFirst);
        firstNumber.setShowSoftInputOnFocus(false);
        firstNumber.setCursorVisible(false);

        secondNumber = findViewById(R.id.numberTextSecond);
        secondNumber.setShowSoftInputOnFocus(false);
        secondNumber.setCursorVisible(false);


        //Initializing the number buttons
        numberOne = findViewById(R.id.numberOne);
        numberTwo = findViewById(R.id.numberTwo);
        numberThree = findViewById(R.id.numberThree);
        numberFour = findViewById(R.id.numberFour);
        numberFive = findViewById(R.id.numberFive);
        numberSix = findViewById(R.id.numberSix);
        numberSeven = findViewById(R.id.numberSeven);
        numberEight = findViewById(R.id.numberEight);
        numberNine = findViewById(R.id.numberNine);
        numberZero = findViewById(R.id.numberZero);

        //Initializing the ImageButtons
        forwardButton = findViewById(R.id.forwardBtn);
        backwardButton = findViewById(R.id.btnBack);
        deleteButtonOne = findViewById(R.id.backspaceLeft);
        deleteButtonTwo = findViewById(R.id.backspaceRight);

        //Set number buttons onClick Listeners to this activity
        numberOne.setOnClickListener(this);
        numberTwo.setOnClickListener(this);
        numberThree.setOnClickListener(this);
        numberFour.setOnClickListener(this);
        numberFive.setOnClickListener(this);
        numberSix.setOnClickListener(this);
        numberSeven.setOnClickListener(this);
        numberEight.setOnClickListener(this);
        numberNine.setOnClickListener(this);
        numberZero.setOnClickListener(this);


        forwardBackProcess();

        deleteButtonsOnClickListners();

        firstNumber.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                setTheNumberToEditText(firstNumber.getId());
                //Toast.makeText(MainActivity.this,"First edit text is selected",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        secondNumber.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                setTheNumberToEditText(secondNumber.getId());
                //Toast.makeText(MainActivity.this,"Second edit text is selected",Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

    private void deleteButtonsOnClickListners() {

        deleteButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText temp = getAEditTextForId(currentlySelectedEditText);
                backspace(temp.getText().toString(), currentlySelectedEditText);
            }
        });

        deleteButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText temp = getAEditTextForId(currentlySelectedEditText);
                backspace(temp.getText().toString(), currentlySelectedEditText);
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.numberZero:
                concatenating(numberZero.getText().toString());
                break;
            case R.id.numberOne:
                concatenating(numberOne.getText().toString());
                break;
            case R.id.numberTwo:
                concatenating(numberTwo.getText().toString());
                break;
            case R.id.numberThree:
                concatenating(numberThree.getText().toString());
                break;
            case R.id.numberFour:
                concatenating(numberFour.getText().toString());
                break;
            case R.id.numberFive:
                concatenating(numberFive.getText().toString());
                break;
            case R.id.numberSix:
                concatenating(numberSix.getText().toString());
                break;
            case R.id.numberSeven:
                concatenating(numberSeven.getText().toString());
                break;
            case R.id.numberEight:
                concatenating(numberEight.getText().toString());
                break;
            case R.id.numberNine:
                concatenating(numberNine.getText().toString());
                break;
        }

    }

    public void concatenating(String number) {
        if (theNumber.equals("")) {
            theNumber = number;
        } else {
            theNumber = theNumber + number;
        }

        EditText tempEdit = getAEditTextForId(currentlySelectedEditText);
        tempEdit.setText(theNumber);

        //Toast.makeText(MainActivity.this, "Number : " + theNumber, Toast.LENGTH_LONG).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setTheNumberToEditText(int selectedID) {

        EditText tempEditText = getAEditTextForId(selectedID);

        currentlySelectedEditText = selectedID;

        if (previousOne == 0) {
            previousOne = selectedID;
            theNumber = tempEditText.getText().toString();
            tempEditText.setTextColor(getColor(R.color.colorAccent));

        } else {
            if (previousOne != selectedID) {
                theNumber = "";
                theNumber = tempEditText.getText().toString();

                EditText prevOne = getAEditTextForId(previousOne);
                prevOne.setTextColor(getColor(R.color.colorPrimary));

                tempEditText.setTextColor(getColor(R.color.colorAccent));

                previousOne = selectedID;
            }
        }

    }

    private EditText getAEditTextForId(int selectedID) {
        EditText myEditTextTemp = new EditText(this); // Pass it an Activity or Context
        myEditTextTemp.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)); // Pass two args; must be LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, or an integer pixel value.
        myEditTextTemp = findViewById(selectedID);

        return myEditTextTemp;

    }

    public void backspace(String number, int id) {
        if (number != null && number.length() > 0) {
            number = number.substring(0, number.length() - 1);
        }
        //Toast.makeText(MainActivity.this, "New Number : " + number, Toast.LENGTH_LONG).show();
        theNumber = number;

        EditText temp = getAEditTextForId(id);
        temp.setText(theNumber);
    }

    private void forwardBackProcess(){

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditText temp = getAEditTextForId(currentlySelectedEditText);

            }
        });
    }


}
