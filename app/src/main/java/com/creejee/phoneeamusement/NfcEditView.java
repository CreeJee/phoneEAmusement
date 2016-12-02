package com.creejee.phoneeamusement;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by my on 2016-11-28.
 */

public class NfcEditView  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
    }

}
