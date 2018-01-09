package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream bool indicating state of whipped cream checkbox
     * @param hasChocolate bool indicating state of chocolate checkbox
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = quantity * 5;
        if (hasWhippedCream) {
            price += quantity * 1;
        }
        if (hasChocolate){
            price += quantity * 2;
        }
        return price;
    }

    /**
     * This method creates an order summary
     *
     * @param price of the order
     * @param hasWhippedCream bool indicating state of whipped cream checkbox
     * @param hasChocolate bool indicating state of chocolate checkbox
     * @return a message with name, needed toppings, quantity ordered, total amount, and a thank you
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String nameText) {
        String orderSummary = "Order for: " + nameText;
        orderSummary += "\nAdd whipped cream? " + hasWhippedCream;
        orderSummary += "\nAdd chocolate? " + hasChocolate;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal= $" + price;
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Take user's name and store as a string
        EditText userName = findViewById(R.id.userName);
        String nameText = userName.getText().toString();
        // Find if user wants whipped cream
        CheckBox whippedCreamCheckBox = findViewById(R.id.whippedCreamCheckBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        // Find if user wants chocolate
        CheckBox chocolateCheckBox = findViewById(R.id.chocolateCheckBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String orderSummary = createOrderSummary(price, hasWhippedCream, hasChocolate, nameText);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + nameText);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity >= 100) {
            Context context = getApplicationContext();
            CharSequence text = "We don't have that much coffee!";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
            return;
        } else {
            quantity = quantity + 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 1) {
            Context context = getApplicationContext();
            CharSequence text = "You can't order less than one coffee!";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
            return;
        } else {
            quantity = quantity - 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
}