package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method creates an order summary
     *
     * @param price of the order
     * @param hasWhippedCream bool indicating state of wc checkbox
     * @param hasChocolate bool indicating state of chocolate checkbox
     * @return a message with name, needed toppings, quantity ordered, total amount, and a thank you
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate) {
        String priceMessage = "Name: First Last";
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal= $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whippedCreamCheckBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.chocolateCheckBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}