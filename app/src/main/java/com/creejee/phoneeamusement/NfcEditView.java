package com.creejee.phoneeamusement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by my on 2016-11-28.
 */

public class NfcEditView  extends AppCompatActivity {
    public CardDbManager cardDbManager = null;
    private String dbName = "eAphone";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final EditText edName = (EditText) findViewById(R.id.editName);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        cardDbManager = new CardDbManager(getApplicationContext(),dbName,null,1);
        Button submitBtn =(Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getExtras() != null) {
                    Bundle bundle = getIntent().getExtras();
                    cardDbManager.open("w").query("" +
                            "INSERT INTO `cardList` " +
                            "(`idx`," +
                            "`cardname`," +
                            "`tagInfo`," +
                            "`tagId`," +
                            "`tagContent`" +
                            ") VALUES (NULL,"
                            + edName.getText() + ","
                            + bundle.getString("tagInfo") + ","
                            + bundle.getString("tagId") + ","
                            + bundle.getString("tagContent") + "" +
                            ");")
                            .close();
                }
                else{
                    Toast.makeText(getApplicationContext(), "bundle is null", Toast.LENGTH_SHORT).show();
                }
                //edName.getText();
                //getIntent().getExtras().get();
            }
        });

    }

}
