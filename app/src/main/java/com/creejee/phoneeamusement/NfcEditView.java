package com.creejee.phoneeamusement;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by my on 2016-11-28.
 */

public class NfcEditView  extends AppCompatActivity {
    public SqliteManager sqliteManager = null;
    public String dbName = "eAphone";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        sqliteManager = new SqliteManager(getApplicationContext(),dbName,null,1);
        Button submitBtn =(Button) findViewById(R.id.submitBtn);
        final EditText edName = (EditText) findViewById(R.id.editName);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edName.getText();
                //getIntent().getExtras().get();
            }
        });

    }

}
