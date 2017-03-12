package bms.intents_implicitas;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] arrayOption = getResources().getStringArray(R.array.act);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayOption);
        setListAdapter(adapter);
    }

    //Verifica se a intent disparada pode ser resolvida, caso contrário emite um aviso, impedindo
    //que um erro pare a app.
    private void dispararIntent(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.intentErro, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Uri uri = null;
        Intent intent = null;
        switch (position){
            //Abrindo uma Url
            case 0:
                uri = Uri.parse("http://www.nglauber.com.br");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispararIntent(intent);
                break;
            //Realizar uma chamada
            case 1:
                uri = Uri.parse("tel:5592982415168");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                //ACTION_CALL - se quisesse ligar direto.
                dispararIntent(intent);
                break;
            //Pesquisa uma posição do mapa
            //!Seu emulador deve ter o Google Maps
            case 2:
                uri = Uri.parse("geo:0,0?q=Rua+Amelia,Recife");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispararIntent(intent);
                break;
            //Executa uma musica do SDCard
            case 3:
                uri = Uri.parse("file:///mnt/sdcard/musica.mp3");
                intent = new Intent().setAction(Intent.ACTION_VIEW).setDataAndType(uri,"audio/mp3");
                dispararIntent(intent);
                break;
            //Abrindo o editor de SMS
            case 4:
                uri = Uri.parse("sms:12345");
                intent = new Intent(Intent.ACTION_VIEW, uri).putExtra("sms_body", "Corpo do SMS");
                dispararIntent(intent);
                break;
            //Compartilhar
            case 5:
                intent = new Intent().setAction(Intent.ACTION_SEND).putExtra(Intent.EXTRA_TEXT, "Compartilhando via intent.").setType("text/plain");
                dispararIntent(intent);
                break;
            //Alarme
            case 6:
                //Os dias da semana que o alarme deve se repetir
                //só para KitKat API Level 19
                //Precisa de: <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
                ArrayList<Integer> dias = new ArrayList<>();
                dias.add(Calendar.MONDAY);
                dias.add(Calendar.WEDNESDAY);
                dias.add(Calendar.FRIDAY);
                intent = new Intent(AlarmClock.ACTION_SET_ALARM).putExtra(AlarmClock.EXTRA_MESSAGE, "Estudar Android").putExtra(AlarmClock.EXTRA_HOUR,19).putExtra(AlarmClock.EXTRA_MINUTES,0).putExtra(AlarmClock.EXTRA_SKIP_UI, true).putExtra(AlarmClock.EXTRA_DAYS,dias);
                dispararIntent(intent);
                break;
            //Busca na web
            case 7:
                intent = new Intent(Intent.ACTION_SEARCH).putExtra(SearchManager.QUERY, "Novatec");
                dispararIntent(intent);
                break;
            //Configuracoes do aparelho
            case 8:
                intent = new Intent(Settings.ACTION_SETTINGS);
                dispararIntent(intent);
                break;
            //Acao customizada 1
            case 9:
                intent = new Intent("dominando.android.ACAO_CUSTOMIZADA");
                dispararIntent(intent);
                break;
            //Acao customizada 2
            case 10:
                uri = Uri.parse("produto://Notebook/Slim");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                dispararIntent(intent);
                break;
            case 11:
                finish();
        }
    }
}
