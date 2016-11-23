package com.creejee.phoneeamusement;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;

import static android.R.attr.id;

/**
 * Created by my on 2016-11-20.
 */

public class NfcCreateView extends AppCompatActivity {

    NfcManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card);
        manager = (NfcManager) getApplicationContext().getSystemService(Context.NFC_SERVICE);
        if (manager.getDefaultAdapter() != null){
            //Toast.makeText(getApplicationContext(),manager.getDefaultAdapter().toString(),Toast.LENGTH_SHORT).show();
            if (manager.getDefaultAdapter().isNdefPushEnabled() && getIntent() != null){
                Toast.makeText(getApplicationContext(),"시동완료",Toast.LENGTH_SHORT).show();
                //processTag(getIntent());
            }
            else{
                Toast.makeText(getApplicationContext(),"android beam을 필요로 합니다.",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"nfc를 지원하지 않습니다.",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null){
            Toast.makeText(this, "tag : "+tag.getId().toString(), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "tag : !{undefined}!", Toast.LENGTH_SHORT).show();
        }
    }
}

