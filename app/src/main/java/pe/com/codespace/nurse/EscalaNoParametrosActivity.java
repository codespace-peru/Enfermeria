package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static pe.com.codespace.nurse.MyValues.*;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class EscalaNoParametrosActivity extends AppCompatActivity {

    TextView textViewTitle,textViewResultado, textViewNotas;
    TextView textViewItem1, textViewItem2, textViewItem3, textViewItem4, textViewItem5;
    TextView textViewItem6, textViewItem7, textViewItem8, textViewItem9, textViewItem10;
    TextView textViewNum1, textViewNum2, textViewNum3, textViewNum4, textViewNum5;
    TextView textViewNum6, textViewNum7, textViewNum8, textViewNum9, textViewNum10;
    LinearLayout linearLayout7, linearLayout8, linearLayout9, linearLayout10;
    int numeroEscala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_escalanoparametros);
        numeroEscala= getIntent().getExtras().getInt("numeroEscala");

        textViewTitle = (TextView) findViewById(R.id.textViewTitleEscala);
        textViewResultado = (TextView) findViewById(R.id.textViewResultadoEscala);
        textViewNotas = (TextView) findViewById(R.id.textViewEscalaNotas);
        textViewNum1 = (TextView) findViewById(R.id.textViewNum1);
        textViewNum2 = (TextView) findViewById(R.id.textViewNum2);
        textViewNum3 = (TextView) findViewById(R.id.textViewNum3);
        textViewNum4 = (TextView) findViewById(R.id.textViewNum4);
        textViewNum5 = (TextView) findViewById(R.id.textViewNum5);
        textViewNum6 = (TextView) findViewById(R.id.textViewNum6);
        textViewNum7 = (TextView) findViewById(R.id.textViewNum7);
        textViewNum8 = (TextView) findViewById(R.id.textViewNum8);
        textViewNum9 = (TextView) findViewById(R.id.textViewNum9);
        textViewNum10 = (TextView) findViewById(R.id.textViewNum10);
        textViewItem1 = (TextView) findViewById(R.id.textViewItem1);
        textViewItem2 = (TextView) findViewById(R.id.textViewItem2);
        textViewItem3 = (TextView) findViewById(R.id.textViewItem3);
        textViewItem4 = (TextView) findViewById(R.id.textViewItem4);
        textViewItem5 = (TextView) findViewById(R.id.textViewItem5);
        textViewItem6 = (TextView) findViewById(R.id.textViewItem6);
        textViewItem7 = (TextView) findViewById(R.id.textViewItem7);
        textViewItem8 = (TextView) findViewById(R.id.textViewItem8);
        textViewItem9 = (TextView) findViewById(R.id.textViewItem9);
        textViewItem10 = (TextView) findViewById(R.id.textViewItem10);
        linearLayout7 = (LinearLayout) findViewById(R.id.escala_linear7);
        linearLayout8 = (LinearLayout) findViewById(R.id.escala_linear8);
        linearLayout9 = (LinearLayout) findViewById(R.id.escala_linear9);
        linearLayout10 = (LinearLayout) findViewById(R.id.escala_linear10);

        prepararData(numeroEscala);

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewEscalaSinParam);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Analytics
        Tracker tracker = ((AnalyticsApplication)  getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        String nameActivity = getApplicationContext().getPackageName() + "." + this.getClass().getSimpleName();
        tracker.setScreenName(nameActivity);
        tracker.enableAdvertisingIdCollection(true);
        tracker.send(new HitBuilders.AppViewBuilder().build());

    }

    private void prepararData(int tipo) {
        switch(tipo){
            case RASS:
                textViewTitle.setText(getResources().getString(R.string.scale_rass_title));
                textViewNum1.setText("+4");
                textViewNum2.setText("+3");
                textViewNum3.setText("+2");
                textViewNum4.setText("+1");
                textViewNum5.setText("0");
                textViewNum6.setText("-1");
                textViewNum7.setText("-2");
                textViewNum8.setText("-3");
                textViewNum9.setText("-4");
                textViewNum10.setText("-5");
                textViewItem1.setText(getResources().getString(R.string.rass_combativo));
                textViewItem2.setText(getResources().getString(R.string.rass_muy_agitado));
                textViewItem3.setText(getResources().getString(R.string.rass_agitado));
                textViewItem4.setText(getResources().getString(R.string.rass_inquieto));
                textViewItem5.setText(getResources().getString(R.string.rass_alerta));
                textViewItem6.setText(getResources().getString(R.string.rass_somnoliento));
                textViewItem7.setText(getResources().getString(R.string.rass_sedacion_ligera));
                textViewItem8.setText(getResources().getString(R.string.rass_sedacion_moderada));
                textViewItem9.setText(getResources().getString(R.string.rass_sedacion_profunda));
                textViewItem10.setText(getResources().getString(R.string.rass_no_estimulable));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_rass));
                textViewNotas.setVisibility(View.VISIBLE);
                break;
            case RAMSAY:
                textViewTitle.setText(getResources().getString(R.string.scale_ramsay_title));
                textViewNum2.setText("1");
                textViewNum3.setText("2");
                textViewNum4.setText("3");
                textViewNum6.setText("4");
                textViewNum7.setText("5");
                textViewNum8.setText("6");
                textViewItem1.setText(getResources().getString(R.string.ramsay_despierto));
                textViewItem2.setText(getResources().getString(R.string.ramsay_ansioso));
                textViewItem3.setText(getResources().getString(R.string.ramsay_tranquilo));
                textViewItem4.setText(getResources().getString(R.string.ramsay_solo_ordenes));
                textViewItem5.setText(getResources().getString(R.string.ramsay_estimulo_auditivo));
                textViewItem6.setText(getResources().getString(R.string.ramsay_respuesta_energica));
                textViewItem7.setText(getResources().getString(R.string.ramsay_respuesta_lenta));
                textViewItem8.setText(getResources().getString(R.string.ramsay_no_responde));
                textViewNotas.setText(getResources().getString(R.string.descripcion_scale_ramsay));
                textViewNum1.setVisibility(View.GONE);
                textViewNum5.setVisibility(View.GONE);
                linearLayout9.setVisibility(View.GONE);
                linearLayout10.setVisibility(View.GONE);
                textViewNotas.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
