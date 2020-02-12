package com.murach.invoice;

//imports classes

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class InvoiceTotalActivity extends Activity implements OnEditorActionListener, OnClickListener {


	//declare variables

	private EditText subtotalEditText;
	private TextView discountTextView;
	private TextView discountPercentTextView;
	private TextView totalTextView;

	private Button decreaseButton;
	private Button increaseButton;

	private float discountPercent = 0.10f;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invoice_total);

		//get reference to widgets

		subtotalEditText = findViewById(R.id.subtotalEditText);
		discountTextView = findViewById(R.id.discountAmountTextView);
		discountPercentTextView = findViewById(R.id.discountPercentTextView);
		totalTextView = findViewById(R.id.totalTextView);

		decreaseButton = (Button) findViewById(R.id.decreaseButton);
		increaseButton = (Button) findViewById(R.id.increaseButton);

        //sets listener

		subtotalEditText.setOnEditorActionListener(this);
		decreaseButton.setOnClickListener(this);
		increaseButton.setOnClickListener(this);
	}

		//implements listener

	@Override
	public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			calculateAndDisplay();
		}
		return false;
	}

	public void calculateAndDisplay() {

		//get bill amount and tip and total

		String subtotalAmountString = subtotalEditText.getText().toString();
		float subtotalAmount;
		if (subtotalAmountString.equals("")) {
			subtotalAmount = 0;
		} else {
			subtotalAmount = Float.parseFloat(subtotalAmountString);
		}

		float discountAmount = subtotalAmount * discountPercent;
		float totalAmount = subtotalAmount - discountAmount;

		//shows formatted results

		NumberFormat currency = NumberFormat.getCurrencyInstance();
		discountTextView.setText(currency.format(discountAmount));
		totalTextView.setText(currency.format(totalAmount));

		NumberFormat percent = NumberFormat.getPercentInstance();
		discountPercentTextView.setText(percent.format(discountPercent));



	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
				case R.id.decreaseButton:
				    discountPercent = discountPercent - .01f;
					Log.e("Discount", Float.toString(discountPercent));
				    calculateAndDisplay();
				    break;
				case R.id.increaseButton:
					discountPercent = discountPercent + .01f;
					calculateAndDisplay();
					Log.e("Discount", Float.toString(discountPercent));
					break;


		}
	}

}
