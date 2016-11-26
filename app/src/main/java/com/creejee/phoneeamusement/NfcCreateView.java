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

import static com.creejee.phoneeamusement.stringUtil.*;


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
        tagIntentOptions(getIntent());
    }
    @Override
    protected void onNewIntent(Intent intent) {
        tagIntentOptions(intent);
    }
    private void tagIntentOptions(Intent intent){
        if (manager.getDefaultAdapter() != null) {
            context = getApplicationContext();
            if (manager.getDefaultAdapter().isNdefPushEnabled() && intent != null) {
                mAdapter = NfcAdapter.getDefaultAdapter(this);

                //시발 알고보니 UID값만 있던걸로

                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                Toast.makeText(getApplicationContext(),"uid : " + bin2hex(tag.getId()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"un support \n tagName" + arrayToString(tag.getTechList(),"\n"), Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "android beam을 필요로 합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            }
        } else {
            Toast.makeText(getApplicationContext(), "nfc를 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
        }

    }



}

