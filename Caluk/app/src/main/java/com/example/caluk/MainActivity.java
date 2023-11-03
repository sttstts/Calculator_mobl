package com.example.caluk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC;
    MaterialButton buttonMinus, buttonPlus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignid(buttonC,R.id.button_C);
        assignid(buttonMinus,R.id.button_minus);
        assignid(buttonPlus,R.id.button_plus);
        assignid(buttonEquals,R.id.button_equals);
        assignid(button0,R.id.button_0);
        assignid(button1,R.id.button_1);
        assignid(button2,R.id.button_2);
        assignid(button3,R.id.button_3);
        assignid(button4,R.id.button_4);
        assignid(button5,R.id.button_5);
        assignid(button6,R.id.button_6);
        assignid(button7,R.id.button_7);
        assignid(button8,R.id.button_8);
        assignid(button9,R.id.button_9);
        assignid(buttonAC,R.id.button_AC);
        assignid(buttonDot,R.id.button_Dot);

    }

    void assignid(MaterialButton btn,int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);


        String finaleResult = getResult(dataToCalculate);

        if(!finaleResult.equals("Err")){
            resultTv.setText(finaleResult);
        }

    }

    String getResult(String data) {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}
