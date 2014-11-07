package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class EscalaNoParametrosActivity extends ActionBarActivity {

    private static final int RASS=1;

    TextView textViewTitle,textViewResultado, textViewNotas;
    TextView textViewItem1, textViewItem2, textViewItem3, textViewItem4, textViewItem5;
    TextView textViewItem6, textViewItem7, textViewItem8, textViewItem9, textViewItem10;
    TextView textViewNum1, textViewNum2, textViewNum3, textViewNum4, textViewNum5;
    TextView textViewNum6, textViewNum7, textViewNum8, textViewNum9, textViewNum10;
    int numeroEscala;
    String tipoEscala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        prepararData(numeroEscala);
    }

    private void prepararData(int tipo) {
        switch(tipo){
            case RASS:
                textViewTitle.setText("Escala RASS");
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
                textViewNotas.setText("");
                textViewNotas.setVisibility(View.VISIBLE);
                tipoEscala="RASS";
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

}
