package com.creejee.phoneeamusement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.cardemulation.HostApduService;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.Format;

/**
 * Created by user on 2016-12-21.
 */

public class CardSelectView extends AppCompatActivity{
    private String dbName = "eAphone";
    private CardDbManager db;
    private NfcManager manager;
    private DbStruct res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);
        manager = (NfcManager) getApplicationContext().getSystemService(Context.NFC_SERVICE);
        Bundle bundle = getIntent().getExtras();

        TextView textView = (TextView) findViewById(R.id.sacnMessageText);
        //String s = bundle.getString("cardName");
        //textView.setText("cardName : "+s );
        db = new CardDbManager(getApplicationContext(),dbName,null,1);
        String sql = "SELECT * FROM `cardList` WHERE cardName = '"+bundle.getString("cardName")+"'";
        res = db.open("r").query(sql).getData();
        //byte[] tagInfo = new java.math.BigInteger(res.getDbStruct().get("tagInfo").toString(), 16).toByteArray();
        byte[] data = new java.math.BigInteger(res.get("tagId"), 16).toByteArray();
        if (manager.getDefaultAdapter() != null) {
            if (manager.getDefaultAdapter().isNdefPushEnabled()) {
                processNfcIntent(res.get("tagId"));
            }
            else{
                Toast.makeText(getApplicationContext(), "android beam을 필요로 합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            }
        } else {
            Toast.makeText(getApplicationContext(), "nfc를 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
        textView.setText("cardName : "+res.get("cardName"));
        db.close();
        //new java.math.BigInteger(hexText, 16).toByteArray();
    }
    public void processNfcIntent(String UID) {
        final Intent ndefIntent = new Intent(NfcAdapter.ACTION_NDEF_DISCOVERED);
        ndefIntent.setType("text/plain");
        ndefIntent.putExtra(NfcAdapter.EXTRA_ID, UID);
        //ndefIntent.putExtra(NfcAdapter.EXTRA_TAG, mockTag);
        ndefIntent.putExtra("android.nfc.extra.AFI",0x00);
        ndefIntent.putExtra("android.nfc.extra.DSFID",0x00);
        ndefIntent.putExtra(NfcAdapter.EXTRA_NDEF_MESSAGES, new NdefMessage[]{});
        //// TODO: 2017-01-06 ADD TAG EXTRA EDIT
        sendBroadcast(ndefIntent);
    }
}
