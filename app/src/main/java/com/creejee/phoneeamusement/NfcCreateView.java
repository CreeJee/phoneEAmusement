package com.creejee.phoneeamusement;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.tech.TagTechnology;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import android.nfc.NdefRecord;


import java.io.UnsupportedEncodingException;
import java.util.Arrays;



/**
 * Created by my on 2016-11-20.
 */

public class NfcCreateView extends AppCompatActivity {

    private NfcManager manager;
    private NfcAdapter mAdapter;
    private static String tagType = "*/*";
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);
        manager = (NfcManager) getApplicationContext().getSystemService(Context.NFC_SERVICE);
        context = getApplicationContext();
        tagIntentOptions(getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        tagIntentOptions(intent);
    }

    public void readFromTag(Intent intent){
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Ndef ndef = Ndef.get(tag);


        try{
            ndef.connect();

            //txtType.setText(ndef.getType().toString());
            //txtSize.setText(String.valueOf(ndef.getMaxSize()));
            //txtWrite.setText(ndef.isWritable() ? "True" : "False");
            Parcelable[] messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            if (messages != null) {
                NdefMessage[] ndefMessages = new NdefMessage[messages.length];
                for (int i = 0; i < messages.length; i++) {
                    ndefMessages[i] = (NdefMessage) messages[i];
                }
                NdefRecord record = ndefMessages[0].getRecords()[0];

                byte[] payload = record.getPayload();
                String text = new String(payload);
                Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();


                ndef.close();

            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Cannot Read From Tag.", Toast.LENGTH_LONG).show();
        }
    }
    private void tagIntentOptions(Intent intent){
        if (manager.getDefaultAdapter() != null) {
            if (manager.getDefaultAdapter().isNdefPushEnabled() && intent != null) {
                mAdapter = NfcAdapter.getDefaultAdapter(this);

                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                Ndef ndef = Ndef.get(tag);
                if (ndef == null) {
                    Toast.makeText(getApplicationContext(),"un support", Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(getApplicationContext(), "support", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "android beam을 필요로 합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            }
        } else {
            Toast.makeText(getApplicationContext(), "nfc를 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
        }

    }



}

