package pe.com.codespace.nurse;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


public class Escala3ParametrosActivity extends ActionBarActivity {

    private static final int GLASGOW=31;

    TextView textViewTitle,textViewResultado,textView1, textView2, textView3, textViewNotas;
    TextView textViewItemEscala1, textViewItemEscala2, textViewItemEscala3;
    Spinner dropdownItem1,dropdownItem2,dropdownItem3;
    int Param1, Param2, Param3;
    String[] items1, items2, items3;
    int[] items1Values, items2Values, items3Values;
    int numeroEscala;
    String tipoEscala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_escala3parametros);
        numeroEscala= getIntent().getExtras().getInt("numeroEscala");

        textViewTitle = (TextView) findViewById(R.id.textViewTitleEscala);
        textViewResultado = (TextView) findViewById(R.id.textViewResultadoEscala);
        textViewNotas = (TextView) findViewById(R.id.textViewEscalaNotas);
        textViewItemEscala1 = (TextView) findViewById(R.id.textViewItemEscala1);
        textViewItemEscala2 = (TextView) findViewById(R.id.textViewItemEscala2);
        textViewItemEscala3 = (TextView) findViewById(R.id.textViewItemEscala3);
        textView1 = (TextView) findViewById(R.id.textViewVariable1);
        textView2 = (TextView) findViewById(R.id.textViewVariable2);
        textView3 = (TextView) findViewById(R.id.textViewVariable3);
        dropdownItem1 = (Spinner)findViewById(R.id.spinnerVariable1);
        dropdownItem2 = (Spinner)findViewById(R.id.spinnerVariable2);
        dropdownItem3 = (Spinner)findViewById(R.id.spinnerVariable3);

        prepararData(numeroEscala);

        textViewItemEscala1.setText(String.valueOf(Param1));
        textViewItemEscala2.setText(String.valueOf(Param2));
        textViewItemEscala3.setText(String.valueOf(Param3));
        UpdateRespuesta();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, items1);
        dropdownItem1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, items2);
        dropdownItem2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, items3);
        dropdownItem3.setAdapter(adapter3);

        dropdownItem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param1 = items1Values[i];
                textViewItemEscala1.setText(String.valueOf(Param1));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param2 = items2Values[i];
                textViewItemEscala2.setText(String.valueOf(Param2));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param3 = items3Values[i];
                textViewItemEscala3.setText(String.valueOf(Param3));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void prepararData(int tipo) {
        switch(tipo){
            case GLASGOW:
                textViewTitle.setText("Escala de Coma de Glasgow");
                textView1.setText("Apertura Ocular");
                textView2.setText("Respuesta Verbal");
                textView3.setText("Respuesta Motora");
                textViewNotas.setText("Interpretación Neurológica :\n" +
                        "Consciente:       15 puntos\n" +
                        "Estupor ligero:   13-14 puntos\n" +
                        "Estupor moderado: 11-12 puntos\n" +
                        "Estupor profundo: 9-10 puntos\n" +
                        "Coma superficial: 7-8 puntos\n" +
                        "Coma moderado:    5-6 puntos\n" +
                        "Coma profundo:    3-4 puntos\n");
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=4; Param2=5; Param3=6;
                items1 = new String[]{"Espontánea","A la voz", "Al dolor", "Ninguna"};
                items1Values = new int[]{4,3,2,1};
                items2 = new String[]{"Orientada","Confusa", "Palabras inapropiadas", "Sonidos incomprensibles", "Ninguna"};
                items2Values = new int[]{5,4,3,2,1};
                items3 = new String[]{"Espontánea, normal","Localiza al tacto", "Localiza al dolor", "Decorticación", "Descerebración", "Ninguna"};
                items3Values = new int[]{6,5,4,3,2,1};
                tipoEscala="Glasgow";
                break;
            case 2:
                break;
        }

    }

    private void UpdateRespuesta(){
        textViewResultado.setText(tipoEscala + ": " + String.valueOf(Param1+Param2+Param3));
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
