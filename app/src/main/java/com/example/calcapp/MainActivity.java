package com.example.calcapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentInput = "";
    private double previousValue = 0.0;
    private String currentOperator = "";
    private boolean justCalculated = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // parent vertical layout
        LinearLayout parent = new LinearLayout(this);
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setBackgroundColor(Color.BLACK);
        int padding = dpToPx(16);
        parent.setPadding(padding, padding, padding, padding);

        // this is for the text view for calculations and the result
        tvDisplay = new TextView(this);
        tvDisplay.setText("0");
        tvDisplay.setTextColor(Color.WHITE);
        tvDisplay.setTextSize(48);
        tvDisplay.setGravity(Gravity.END | Gravity.BOTTOM);
        tvDisplay.setPadding(padding, padding, padding, padding);
        LinearLayout.LayoutParams displayParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1  // for taking available space
        );
        parent.addView(tvDisplay, displayParams);

        // ------------------ first row: AC, +/-, %, ÷ ------------------
        LinearLayout row1 = createButtonRow();
        Button btnAC        = createButton("AC", "#A5A5A5", "#000000");
        Button btnPlusMinus = createButton("+/-", "#A5A5A5", "#000000");
        Button btnPercent   = createButton("%", "#A5A5A5", "#000000");
        Button btnDivide    = createButton("÷", "#FF9500", "#FFFFFF");

        row1.addView(btnAC,        createLayoutParams(1));
        row1.addView(btnPlusMinus, createLayoutParams(1));
        row1.addView(btnPercent,   createLayoutParams(1));
        row1.addView(btnDivide,    createLayoutParams(1));
        parent.addView(row1);

        // ------------------ second row: 7, 8, 9, × ------------------
        LinearLayout row2 = createButtonRow();
        Button btn7 = createButton("7", "#333333", "#FFFFFF");
        Button btn8 = createButton("8", "#333333", "#FFFFFF");
        Button btn9 = createButton("9", "#333333", "#FFFFFF");
        Button btnMultiply = createButton("×", "#FF9500", "#FFFFFF");

        row2.addView(btn7,       createLayoutParams(1));
        row2.addView(btn8,       createLayoutParams(1));
        row2.addView(btn9,       createLayoutParams(1));
        row2.addView(btnMultiply,createLayoutParams(1));
        parent.addView(row2);

        // ------------------ thrid row: 4, 5, 6, − ------------------
        LinearLayout row3 = createButtonRow();
        Button btn4 = createButton("4", "#333333", "#FFFFFF");
        Button btn5 = createButton("5", "#333333", "#FFFFFF");
        Button btn6 = createButton("6", "#333333", "#FFFFFF");
        Button btnMinus = createButton("−", "#FF9500", "#FFFFFF");

        row3.addView(btn4,    createLayoutParams(1));
        row3.addView(btn5,    createLayoutParams(1));
        row3.addView(btn6,    createLayoutParams(1));
        row3.addView(btnMinus,createLayoutParams(1));
        parent.addView(row3);

        // ------------------ forth row: 1, 2, 3, + ------------------
        LinearLayout row4 = createButtonRow();
        Button btn1 = createButton("1", "#333333", "#FFFFFF");
        Button btn2 = createButton("2", "#333333", "#FFFFFF");
        Button btn3 = createButton("3", "#333333", "#FFFFFF");
        Button btnPlus = createButton("+", "#FF9500", "#FFFFFF");

        row4.addView(btn1,   createLayoutParams(1));
        row4.addView(btn2,   createLayoutParams(1));
        row4.addView(btn3,   createLayoutParams(1));
        row4.addView(btnPlus,createLayoutParams(1));
        parent.addView(row4);

        // ------------------ fifth row: 0, ., = ------------------
        LinearLayout row5 = createButtonRow();
        Button btn0     = createButton("0", "#333333", "#FFFFFF");
        Button btnDot   = createButton(".", "#333333", "#FFFFFF");
        Button btnEquals= createButton("=", "#FF9500", "#FFFFFF");

        // for the 0 button
        row5.addView(btn0,     createLayoutParams(2));
        row5.addView(btnDot,   createLayoutParams(1));
        row5.addView(btnEquals,createLayoutParams(1));
        parent.addView(row5);

        // setting the parent layout as the screen content
        setContentView(parent);

        // ------------------ this part is for the logic of the calculator ------------------

        // 1. Numerical buttons
        View.OnClickListener numberListener = view -> {
            Button b = (Button) view;
            if (justCalculated) {
                currentInput = "";
                justCalculated = false;
            }
            currentInput += b.getText().toString();
            tvDisplay.setText(currentInput);
        };
        btn0.setOnClickListener(numberListener);
        btn1.setOnClickListener(numberListener);
        btn2.setOnClickListener(numberListener);
        btn3.setOnClickListener(numberListener);
        btn4.setOnClickListener(numberListener);
        btn5.setOnClickListener(numberListener);
        btn6.setOnClickListener(numberListener);
        btn7.setOnClickListener(numberListener);
        btn8.setOnClickListener(numberListener);
        btn9.setOnClickListener(numberListener);

        // 2. (.)
        btnDot.setOnClickListener(view -> {
            if (justCalculated) {
                currentInput = "0";
                justCalculated = false;
            }
            if (!currentInput.contains(".")) {
                if (currentInput.isEmpty()) {
                    currentInput = "0.";
                } else {
                    currentInput += ".";
                }
                tvDisplay.setText(currentInput);
            }
        });

        // 3. Operators
        View.OnClickListener operatorListener = view -> {
            Button b = (Button) view;
            String operator = b.getText().toString();
            if (!currentInput.isEmpty()) {
                previousValue = Double.parseDouble(currentInput);
                currentInput = "";
            }
            currentOperator = operator;
            tvDisplay.setText(String.valueOf(previousValue));
            justCalculated = false;
        };
        btnPlus.setOnClickListener(operatorListener);
        btnMinus.setOnClickListener(operatorListener);
        btnMultiply.setOnClickListener(operatorListener);
        btnDivide.setOnClickListener(operatorListener);

        // 4. (=)
        btnEquals.setOnClickListener(view -> {
            if (currentInput.isEmpty() || currentOperator.isEmpty()) {
                return;
            }
            double secondValue = Double.parseDouble(currentInput);
            double result = 0.0;
            switch (currentOperator) {
                case "+":
                    result = previousValue + secondValue;
                    break;
                case "−":
                    result = previousValue - secondValue;
                    break;
                case "×":
                    result = previousValue * secondValue;
                    break;
                case "÷":
                    if (secondValue != 0) {
                        result = previousValue / secondValue;
                    } else {
                        tvDisplay.setText("Error");
                        return;
                    }
                    break;
            }
            tvDisplay.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            previousValue = result;
            currentOperator = "";
            justCalculated = true;
        });

        // 5. Clear
        btnAC.setOnClickListener(view -> {
            currentInput = "";
            previousValue = 0.0;
            currentOperator = "";
            justCalculated = false;
            tvDisplay.setText("0");
        });

        // 6. (+ )and (- )
        btnPlusMinus.setOnClickListener(view -> {
            if (currentInput.isEmpty()) return;
            double val = Double.parseDouble(currentInput);
            val = -val;
            currentInput = String.valueOf(val);
            tvDisplay.setText(currentInput);
        });

        // 7. (%)
        btnPercent.setOnClickListener(view -> {
            if (currentInput.isEmpty()) return;
            double val = Double.parseDouble(currentInput);
            val = val / 100;
            currentInput = String.valueOf(val);
            tvDisplay.setText(currentInput);
        });
    }

    /**
     * this is a method to create a horizontal row for the buttons.
     */
    private LinearLayout createButtonRow() {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setWeightSum(4); // Each row is divided into 4 parts
        return row;
    }

    /**
     * a method for custom text/color stuff
     */
    private Button createButton(String text, String bgColorHex, String textColorHex) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextColor(Color.parseColor(textColorHex));
        button.setTextSize(24);
        button.setAllCaps(false); // iOS style typically doesn't force uppercase

        // Create a shape with rounded corners to mimic iOS style
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(dpToPx(20)); // adjust corner radius as desired
        shape.setColor(Color.parseColor(bgColorHex));

        // Assign shape as button background
        button.setBackground(shape);

        // If Material theming is still overriding the color, try disabling tint:
        // button.setBackgroundTintList(null);

        return button;
    }

    /**
     * a method to define the LayoutParams for each button and stuff
     */
    private LinearLayout.LayoutParams createLayoutParams(int weight) {
        int margin = dpToPx(4);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0, // width: 0, so weight can distribute
                LinearLayout.LayoutParams.WRAP_CONTENT,
                weight
        );
        params.setMargins(margin, margin, margin, margin);
        return params;
    }

    /**
     * a method to convert dp to px (pixels)
     */
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
