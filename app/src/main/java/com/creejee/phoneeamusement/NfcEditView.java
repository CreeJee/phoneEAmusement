package com.creejee.phoneeamusement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.support.v4.content.IntentCompat.FLAG_ACTIVITY_CLEAR_TASK;
import static com.creejee.phoneeamusement.StringUtil.bytesToHex;

/**
 * Created by my on 2016-11-28.
 */

public class NfcEditView  extends AppCompatActivity {
    public CardDbManager cardDbManager = null;
    private String dbName = "eAphone";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        cardDbManager = new CardDbManager(getApplicationContext(),dbName,null,1);
        Button submitBtn =(Button) findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edName = (EditText) findViewById(R.id.editName);
                if(getIntent().getExtras() != null && edName.getText() != null ) {
                    Bundle bundle = getIntent().getExtras();
                    boolean cardCheck = cardDbManager.open("r").query("SELECT * FROM `cardList` WHERE `cardName` = \"" + edName.getText() + "\"").getData().isEmpty();
                    if(cardCheck == true){
                        if (bundle.getString("action").equals("insert")) {
                            String sql = "INSERT INTO `cardList` " +
                                    "(`idx`," +
                                    "`cardName`," +
                                    "`tagInfo`," +
                                    "`tagId`," +
                                    "`tagContent`" +
                                    ") VALUES (NULL," +
                                    "\"" + edName.getText() + "\"," +
                                    "\"" + bytesToHex(bundle.getByteArray("tagInfo")) + "\"," +
                                    "\"" + bytesToHex(bundle.getByteArray("tagId")) + "\"," +
                                    "\"" + bytesToHex(bundle.getByteArray("tagContent")) + "\"" +
                                    ");";
                            cardDbManager.open("w").query(sql).close();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.addFlags(FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            finish();
                        } else if (bundle.getString("action").equals("update")) {
                            Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "이 이름은 이미 사용중입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "카드 이름을 추가하셔야 합니다.", Toast.LENGTH_SHORT).show();
                }
                //edName.getText();
                //getIntent().getExtras().get();
            }
        });

    }

}
