package com.creejee.phoneeamusement;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static com.creejee.phoneeamusement.StringUtil.*;


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
    public static byte[] processNfcIntent(Intent intent,byte blockStart,byte blockEnd)   {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if(tag != null){
            byte[] id = tag.getId(),data = null;
            // set up read command buffer
            //byte blockNo = 0; // block address
            byte[] readCmd = new byte[4 + id.length];
            readCmd[0] = 0x20; // set "address" flag (only send command to this tag)
            readCmd[1] = 0x23; // ISO 15693 Single Block Read command byte (20) , ISO 15693 Multi Block Read command byte (23)
            System.arraycopy(id, 0, readCmd, 2, id.length); // copy ID
            readCmd[2 + id.length] = blockStart; // 1 byte payload: block address , start byte code
            readCmd[3 + id.length] = blockEnd; // null , end byte code
            NfcV tech = NfcV.get(tag);
            if (tech != null) {
                // send read command
                try {
                    tech.connect();
                    data = tech.transceive(readCmd);
                    return data;
                } catch (IOException e) {
                    Log.e("tagError(transceive)",e.toString());
                } finally {
                    try {
                        tech.close();
                    } catch (IOException e) {
                        Log.e("tagError(Close)",e.toString());
                    }
                }
            }
        }
        return null;
    }
    private void tagIntentOptions(Intent intent){
            if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
                //시발 알고보니 UID값만 있던걸로
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                byte[] tagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
                byte[] tagContent;

                if(tag != null) {
                        tagContent = processNfcIntent(intent,(byte)27,(byte)0);

                        Intent i = new Intent(getApplicationContext(),NfcEditView.class);
                        i.putExtra("tagInfo",tag.getId()); //tag total send byte
                        i.putExtra("tagId",tagId); //tag unique id
                        i.putExtra("tagContent",tagContent); // tag Content id
                        i.putExtra("action_type","insert");
                        startActivity(i);
                        this.finish();
                    /*if(tagContent != null) {
                        TextView t = (TextView) findViewById(R.id.sacnMessageText);
                        t.setText(new String(tagContent));
                    }*/
                }
            }

    }

}
