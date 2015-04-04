package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class EscalaNoParametrosActivity extends ActionBarActivity {

    private static final int RASS=1001;
    private static final int RAMSAY=1002;

    TextView textViewTitle,textViewResultado, textViewNotas;
    TextView textViewItem1, textViewItem2, textViewItem3, textViewItem4, textViewItem5;
    TextView textViewItem6, textViewItem7, textViewItem8, textViewItem9, textViewItem10;
    TextView textViewNum1, textViewNum2, textViewNum3, textViewNum4, textViewNum5;
    TextView textViewNum6, textViewNum7, textViewNum8, textViewNum9, textViewNum10;
    LinearLayout linearLayout7, linearLayout8, linearLayout9, linearLayout10;
    int numeroEscala;
    //String tipoEscala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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

    }

    private void prepararData(int tipo) {
        switch(tipo){
            case RASS:
                textViewTitle.setText("Escala de Sedación RASS");
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
                textViewItem1.setText("Combativo");
                textViewItem2.setText("Muy Agitado");
                textViewItem3.setText("Agitado");
                textViewItem4.setText("Inquieto");
                textViewItem5.setText("Alerta y tranquilo");
                textViewItem6.setText("Somnoliento");
                textViewItem7.setText("Sedación ligera");
                textViewItem8.setText("Sedación moderada");
                textViewItem9.setText("Sedación profunda");
                textViewItem10.setText("No estimulable");
                textViewNotas.setText("Richmond Agitation Sedation Scale (RASS) es una escala usada para medir el nivel de agitación o sedación de un paciente.\nEs muy usada en pacientes con ventilación mecánica.");
                textViewNotas.setVisibility(View.VISIBLE);
                //tipoEscala="RASS";
                break;
            case RAMSAY:
                textViewTitle.setText("Escala de Sedación de RAMSAY");
                textViewNum1.setText("");
                textViewNum2.setText("1");
                textViewNum3.setText("2");
                textViewNum4.setText("3");
                textViewNum5.setText("");
                textViewNum6.setText("4");
                textViewNum7.setText("5");
                textViewNum8.setText("6");
                textViewNum9.setText("");
                textViewNum10.setText("");
                textViewItem1.setText("Despierto:");
                textViewItem2.setText("Ansioso y/o agitado");
                textViewItem3.setText("Tranquilo y colaborador");
                textViewItem4.setText("Responde sólo a órdenes\n\n");
                textViewItem5.setText("Dormido: Estímulo auditivo o glabelar");
                textViewItem6.setText("Respuesta enérgica");
                textViewItem7.setText("Respuesta lenta");
                textViewItem8.setText("No responde");
                textViewItem9.setText("");
                textViewItem10.setText("");
                textViewNotas.setText("La escala Ramsay es usada para evaluar el nivel de sedación de un paciente hospitalizado. Fue descrita por Michael A. E. Ramsay.");
                textViewNum1.setVisibility(View.GONE);
                textViewNum5.setVisibility(View.GONE);
                linearLayout9.setVisibility(View.GONE);
                linearLayout10.setVisibility(View.GONE);
                textViewNotas.setVisibility(View.VISIBLE);
                //tipoEscala="RAMSAY";
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.escala_glasgow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }

}
