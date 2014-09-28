package com.amigos.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
public class AmigosDialog extends DialogFragment {


    private static PayPalConfiguration payPalConfiguration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId("ATGcYxBTGz_zVRx4hQOEXWyu9r3_piWFoa4bebli-oufXdzsSq8KGtc7Vddu");

    public static AmigosDialog newInstance(){
        AmigosDialog amigosDialog = new AmigosDialog();
        return amigosDialog;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        startPayPalService();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);

        View paymentLayout = getActivity().getLayoutInflater().inflate(R.layout.paypal_payment, null);

        builder.setView(paymentLayout);

        Button payButton = (Button)paymentLayout.findViewById(R.id.buy_button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PayPalPayment payment = new PayPalPayment(new BigDecimal("40.00"), "USD", "Kit Services",
                        PayPalPayment.PAYMENT_INTENT_SALE);

                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);


                startActivityForResult(intent, 0);
            }
        });

        return builder.create();
        
        
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

            if (confirm != null) {
                try {

                    Log.i("paymentExample", confirm.toString());

                    JSONObject jsonObject = (JSONObject) confirm.toJSONObject().get("response");
                    Log.i("paymentExample", jsonObject.get("state").toString());

                    if (jsonObject.get("state").toString().contains("approved")) {
                        CorfirmDialog corfirmDialog = CorfirmDialog.newInstance("¡¡¡Gracias!!! \nHemos Registro tu Compra");
                        corfirmDialog.show(getFragmentManager(), "");
                    }
                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }

        dismiss();
    }

    private void startPayPalService(){
        Intent intentServicePayPal = new Intent(getActivity(), PayPalService.class);
        intentServicePayPal.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
        getActivity().startService(intentServicePayPal);
    }



}
