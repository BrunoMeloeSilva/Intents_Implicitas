package bms.intents_implicitas;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        TextView textView = (TextView) findViewById(R.id.txt);

        Intent intent = getIntent();
        if (intent.getAction().equals(Intent.ACTION_VIEW)){
            Uri uri = intent.getData();
            textView.setText("Acao customizada 2. Uri: "+uri.toString()+" Host: "+uri.getHost()+" Path: "+uri.getPath());
        }else if (intent.getAction().equals("dominando.android.ACAO_CUSTOMIZADA")){
            textView.setText("Acao customizada 1");
        }
    }
}
