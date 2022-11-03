package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.udojava.evalex.Expression;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Calculator_Activity extends AppCompatActivity {

    private EditText input_box;
    private TextView result_txt;
    private ImageView btn_back;
    private String display="";
    private String resultati_String = "";
    private List<Character> validOperators = Arrays.asList('+','-','\u00F7','x','%');
    private boolean isDotAdded=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setTitle("Calculator");

        input_box = findViewById(R.id.inputBox);
        result_txt = findViewById(R.id.resultTextView);
        btn_back = findViewById(R.id.back_btn);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteNumbers();

            }
        });


    }

    private void deleteNumbers() {

        if(getInput().length()>0){

            if(input_box.getText().toString().endsWith(".") && isDotAdded)
                isDotAdded=false;
            input_box.getText().delete(getInput().length()-1,getInput().length());

        }


        if(getInput().length()==0){

            result_txt.setText("");
            display = "";
            resultati_String = "";

        }


    }

    public void OnClickNumber(View view) {

        Button button = (Button) view;

        String btn_next = button.getText().toString();

       if(!resultati_String.equals("")){

           result_txt.setText("");
           input_box.getText().clear();
           resultati_String = "";

       }

       if(btn_next.contains(".") && isDotAdded){



       }else{
           display = display + button.getText();

           if(btn_next.contains(".")){

               isDotAdded = true;

               if(endswithOperator() || getInput().isEmpty())
                   display = "0"+display;

           }


       }



        appendtoLast(display);

        display = "";

    }

    @SuppressLint("SuspiciousIndentation")
    public void OnClearClick(View view) {

        input_box.getText().clear();
        result_txt.setText("");
        display = "";
        resultati_String = "";

        if(isDotAdded)
        isDotAdded = false;


    }

    public void OnNegativeClick(View view) {

        display = display + getInput();

        if(display.startsWith("-")){

            display.replaceFirst("-","");

        }else{

           display = "-"+display;
        }

        input_box.setText(display);
        display="";

    }

    public void OnOperatorClick(View view) {

        Button button1 = (Button) view;
        char operatorifundit = button1.getText().charAt(button1.getText().length()-1);

        if(!resultati_String.equals("")){

            if(validOperators.contains(operatorifundit)){

                result_txt.setText("");
                input_box.getText().clear();
                display = "";
                display = resultati_String + button1.getText();
                appendtoLast(display);
                display ="";
                resultati_String ="";

            }else{

                display += button1.getText();
                appendtoLast(display);
                display ="";


            }

        }else{

            if(getInput().isEmpty()){

                display = "0" + button1.getText();
            }else{
                display = display + button1.getText();
            }



            if(endswithOperator()){

                replace(display);
            }else{

                appendtoLast(display);
            }

            display = "";
        }

        if(isDotAdded)
            isDotAdded=false;


    }

    private void replace(String str) {

        input_box.getText().replace(getInput().length()-1,getInput().length(),str);
    }

    private String getInput() {
        return input_box.getText().toString();
    }

    private boolean endswithOperator() {
        return input_box.getText().toString().endsWith("+") || input_box.getText().toString().endsWith("-") || input_box.getText().toString().endsWith("x") || input_box.getText().toString().endsWith("\u00F7") || input_box.getText().toString().endsWith("%");
    }

    @SuppressLint("SuspiciousIndentation")
    public void OnEqualClick(View view) {

        String input = getInput();

        if(input.length()>0 && !endswithOperator()){

            if(input.contains("x"))
                    input=input.replaceAll("x","*");
                if (input.contains("\u00F7"))
                    input = input.replaceAll("\u00F7", "/");

            Expression expression = new Expression(input);

            BigDecimal bigDecimal = expression.eval();

            double result = bigDecimal.doubleValue();

            if(isNumberRoundet(result)){

                    int roundNumber = (int) Math.round(result);
                   resultati_String = String.valueOf(roundNumber);
               }else{

                   resultati_String = String.valueOf(result);
                }

            result_txt.setText(resultati_String);

//            try {
//
//                if(input.contains("x"))
//                    input=input.replaceAll("x","*");
//                if (input.contains("\u00F7"))
//                    input = input.replaceAll("\u00F7", "/");
//
//
//                String operator = input.replaceAll("[\\d.]","");
//
//                operator = String.valueOf(operator.charAt(operator.length()-1));
//
//                String[] numbers = input.split("[\\-/+*%]");
//                double resultati;
//
//                if(input.startsWith("-")){
//
//                    numbers[1] = "-"+numbers[1];
//
//                   resultati = operator(numbers[1],numbers[2],operator);
//                }else{
//                    resultati = operator(numbers[0],numbers[1],operator);
//                }
//
//                if(isNumberRoundet(resultati)){
//
//                    int roundNumber = (int) Math.round(resultati);
//                    resultati_String = String.valueOf(roundNumber);
//                }else{
//
//                    resultati_String = String.valueOf(resultati);
//                }
//
//                result_txt.setText(resultati_String);
//
//            }catch (Exception i){
//              result_txt.setText("");
//            }


        }else result_txt.setText("");

    }

    private boolean isNumberRoundet(double number) {

        return number % 1==0;
    }

    private double operator(String nr_pare,String nr_dyte,String operator){

        switch (operator){

            case "+":
                return Double.parseDouble(nr_pare) + Double.parseDouble(nr_dyte);
            case "-":
                return Double.parseDouble(nr_pare) - Double.parseDouble(nr_dyte);
            case "*":
                return Double.parseDouble(nr_pare) * Double.parseDouble(nr_dyte);
            case "/":
                return Double.parseDouble(nr_pare) / Double.parseDouble(nr_dyte);
            case "%":
                return (Double.parseDouble(nr_pare) / 100) * Double.parseDouble(nr_dyte);
            default:
                return -1;



        }

    }

    public void appendtoLast(String string){

        input_box.append(string);

    }

}