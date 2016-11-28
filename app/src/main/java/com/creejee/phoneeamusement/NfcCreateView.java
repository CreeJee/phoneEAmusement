package com.creejee.phoneeamusement;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import static com.creejee.phoneeamusement.stringUtil.*;


/**
 * Created by my on 2016-11-20.
 */

public class NfcCreateView extends AppCompatActivity {

    private NfcManager manager;
    private NfcAdapter mAdapter;
    private PendingIntent pendingIntent;

    @Override
    public void onResume() {
        super.onResume();
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        mAdapter.enableForegroundDispatch(this ,pendingIntent, null, null);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);
        manager = (NfcManager) getApplicationContext().getSystemService(Context.NFC_SERVICE);
        if (manager.getDefaultAdapter() != null) {
            if (!manager.getDefaultAdapter().isNdefPushEnabled()) {
                Toast.makeText(getApplicationContext(), "android beam을 필요로 합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            }
        } else {
            Toast.makeText(getApplicationContext(), "nfc를 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onNewIntent(Intent intent) {
        if (manager.getDefaultAdapter() != null) {
            if (manager.getDefaultAdapter().isNdefPushEnabled()) {
                tagIntentOptions(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "android beam을 필요로 합니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            }
        } else {
            Toast.makeText(getApplicationContext(), "nfc를 지원하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }
    private void tagIntentOptions(Intent intent){
            if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
                //시발 알고보니 UID값만 있던걸로
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);


                if(tag != null) {
                    /*String result = "";
                    for(int i=0; i<tag.getId().length; i++) {
                        result += String.format("%x",tag.getId()[i])+":";
                    }
                    TextView t =(TextView) findViewById(R.id.sacnMessageText);
                    t.setText(result);*/
                    //Toast.makeText(getApplicationContext(), "uid ( " + result+")", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),NfcEditView.class);
                    i.putExtra("tagInfo",tag.getId());
                    startActivity(i);
                    this.finish();
                }
            }

    }

}
