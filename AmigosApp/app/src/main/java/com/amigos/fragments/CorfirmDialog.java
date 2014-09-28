package com.amigos.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amigos.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * Created by leonelmendez on 28/09/14.
 */
public class CorfirmDialog extends DialogFragment {


    private static final String PARM = "parm";

    public static CorfirmDialog newInstance(String s){
        CorfirmDialog amigosDialog = new CorfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putString(PARM,s);
        amigosDialog.setArguments(bundle);
        return amigosDialog;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View paymentLayout = getActivity().getLayoutInflater().inflate(R.layout.paypal_payment_confirm, null);

        builder.setView(paymentLayout);


        TextView textView = (TextView) paymentLayout.findViewById(R.id.textViewCorfirm);
        textView.setText(getArguments().getString(PARM));

        Button payButton = (Button)paymentLayout.findViewById(R.id.cofirm_button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                getActivity().finish();
            }
        });

        return builder.create();
        
        
    }


}
