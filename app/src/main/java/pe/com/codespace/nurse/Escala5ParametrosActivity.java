package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


public class Escala5ParametrosActivity extends ActionBarActivity {

    private static final int APGAR=51;
    private static final int APGAR_FAM=52;
    private static final int NORTON=53;
    private static final int DOWNTON=54;

    TextView textViewTitle,textViewResultado,textView1, textView2, textView3,textView4, textView5, textViewNotas;
    TextView textViewItemEscala1, textViewItemEscala2, textViewItemEscala3,textViewItemEscala4, textViewItemEscala5;
    Spinner dropdownItem1,dropdownItem2,dropdownItem3,dropdownItem4,dropdownItem5;
    int Param1, Param2, Param3, Param4, Param5;
    String[] items1, items2, items3, items4, items5;
    int[] items1Values, items2Values, items3Values, items4Values, items5Values;
    int numeroEscala;
    String tipoEscala;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_escala5parametros);
        numeroEscala = getIntent().getExtras().getInt("numeroEscala");

        textViewTitle = (TextView) findViewById(R.id.textViewTitleEscala);
        textViewResultado = (TextView) findViewById(R.id.textViewResultadoEscala);
        textViewNotas = (TextView) findViewById(R.id.textViewEscalaNotas);
        textViewItemEscala1 = (TextView) findViewById(R.id.textViewItemEscala1);
        textViewItemEscala2 = (TextView) findViewById(R.id.textViewItemEscala2);
        textViewItemEscala3 = (TextView) findViewById(R.id.textViewItemEscala3);
        textViewItemEscala4 = (TextView) findViewById(R.id.textViewItemEscala4);
        textViewItemEscala5 = (TextView) findViewById(R.id.textViewItemEscala5);
        textView1 = (TextView) findViewById(R.id.textViewVariable1);
        textView2 = (TextView) findViewById(R.id.textViewVariable2);
        textView3 = (TextView) findViewById(R.id.textViewVariable3);
        textView4 = (TextView) findViewById(R.id.textViewVariable4);
        textView5 = (TextView) findViewById(R.id.textViewVariable5);
        dropdownItem1 = (Spinner)findViewById(R.id.spinnerVariable1);
        dropdownItem2 = (Spinner)findViewById(R.id.spinnerVariable2);
        dropdownItem3 = (Spinner)findViewById(R.id.spinnerVariable3);
        dropdownItem4 = (Spinner)findViewById(R.id.spinnerVariable4);
        dropdownItem5 = (Spinner)findViewById(R.id.spinnerVariable5);
        prepararData(numeroEscala);
        textViewItemEscala1.setText(String.valueOf(Param1));
        textViewItemEscala2.setText(String.valueOf(Param2));
        textViewItemEscala3.setText(String.valueOf(Param3));
        textViewItemEscala4.setText(String.valueOf(Param4));
        textViewItemEscala5.setText(String.valueOf(Param5));
        UpdateRespuesta();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item, items1);
        dropdownItem1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_item, items2);
        dropdownItem2.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.spinner_item, items3);
        dropdownItem3.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.spinner_item, items4);
        dropdownItem4.setAdapter(adapter4);

        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, R.layout.spinner_item, items5);
        dropdownItem5.setAdapter(adapter5);

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

        dropdownItem4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param4 = items4Values[i];
                textViewItemEscala4.setText(String.valueOf(Param4));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        dropdownItem5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Param5 = items5Values[i];
                textViewItemEscala5.setText(String.valueOf(Param5));
                UpdateRespuesta();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    private void UpdateRespuesta(){
        textViewResultado.setText(tipoEscala + " :  " + String.valueOf(Param1+Param2+Param3+Param4+Param5));
    }

    private void prepararData(int tipo){
        switch (tipo){
            case APGAR:
                textViewTitle.setText("Test de Apgar");
                textView1.setText("Frecuencia Cardíaca");
                textView2.setText("Movimientos Respiratorios");
                textView3.setText("Color de la Piel");
                textView4.setText("Tono Muscular");
                textView5.setText("Respuesta a Estímulos");
                textViewNotas.setText("Notas");
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=2; Param2=2; Param3=2; Param4=2; Param5=2;
                items1 = new String[]{"> 100 por minuto","< 100 por minuto", "Ausente"};
                items1Values = new int[]{2,1,0};
                items2 = new String[]{"Buena", "Llanto fuerte","Ausente"};
                items2Values = new int[]{2,1,0};
                items3 = new String[]{"Rosada", "Normal(salvo manos y pies)", "Cianosis y palidez"};
                items3Values = new int[]{2,1,0};
                items4 = new String[]{"Movimientos activos","Extremidades flexionadas","Flacidez generalizada"};
                items4Values = new int[]{2,1,0};
                items5 = new String[]{"Enérgica", "Muecas", "Nula"};
                items5Values = new int[]{2,1,0};
                tipoEscala="Apgar";
                break;
            case APGAR_FAM:
                textViewTitle.setText("Test de Apgar Familiar");
                textView1.setText("¿Está satisfecho con la ayuda que recibe de su familia cuando tiene un problema?");
                textView2.setText("¿Conversan entre ustedes los problemas que tienen en casa?");
                textView3.setText("¿Las decisiones importantes se toman en conjunto en la casa?");
                textView4.setText("¿Está satisfecho con el tiempo que usted y su familia pasan juntos?");
                textView5.setText("¿Siente que su familia lo quiere?");
                textViewNotas.setText("Familias altamente funcionales :\n 7 a 10 puntos\n\n" +
                                      "Familias moderadamente funcionales :\n 4 a 6 puntos\n\n" +
                                      "Familias severamente disfuncionales :\n 0 a 3 puntos");
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=0; Param2=0; Param3=0; Param4=0; Param5=0;
                items1 = new String[]{"Casi nunca","A veces", "Casi siempre"};
                items1Values = new int[]{0,1,2};
                items2 = new String[]{"Casi nunca","A veces", "Casi siempre"};
                items2Values = new int[]{0,1,2};
                items3 = new String[]{"Casi nunca","A veces", "Casi siempre"};
                items3Values = new int[]{0,1,2};
                items4 = new String[]{"Casi nunca","A veces", "Casi siempre"};
                items4Values = new int[]{0,1,2};
                items5 = new String[]{"Casi nunca","A veces", "Casi siempre"};
                items5Values = new int[]{0,1,2};
                tipoEscala="Apgar Familiar";
                break;
            case NORTON:
                textViewTitle.setText("Escala de Norton Modificada");
                textView1.setText("Estado Fisico General");
                textView2.setText("Estado Mental");
                textView3.setText("Actividad");
                textView4.setText("Movilidad");
                textView5.setText("Incontinencia");
                textViewNotas.setText("Riesgo Muy Alto : 5 a 9 puntos\n" +
                                      "Riesgo Alto     : 10 a 12 puntos\n" +
                                      "Riesgo Medio    : 13 a 14 puntos\n" +
                                      "Riesgo Mínimo   : Más de 14 puntos");
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=4; Param2=4; Param3=4; Param4=4; Param5=4;
                items1 = new String[]{"Bueno","Mediano", "Regular","Muy Malo"};
                items1Values = new int[]{4,3,2,1};
                items2 = new String[]{"Alerta", "Apático","Confuso","Estuporoso"};
                items2Values = new int[]{4,3,2,1};
                items3 = new String[]{"Ambulante", "Disminuída", "Muy Limitada","Inmovil"};
                items3Values = new int[]{4,3,2,1};
                items4 = new String[]{"Total", "Camina con Ayuda", "Sentado","Encamado"};
                items4Values = new int[]{4,3,2,1};
                items5 = new String[]{"Ninguna","Ocasional","Urinaria o Fecal","Urinaria y Fecal"};
                items5Values = new int[]{4,3,2,1};
                tipoEscala="Norton Modificado";
                break;
            case DOWNTON:
                textViewTitle.setText("Escala de Riesgo de Caídas (J.H. Downton)");
                textView1.setText("Caídas previas");
                textView2.setText("Medicación");
                textView3.setText("Déficit sensorial");
                textView4.setText("Estado mental");
                textView5.setText("Deambulación");
                textViewNotas.setText("\nAlto Riesgo : > 2 puntos\n");
                textViewNotas.setVisibility(View.VISIBLE);
                Param1=0; Param2=0; Param3=0; Param4=0; Param5=0;
                items1 = new String[]{"No","Si"};
                items1Values = new int[]{0,1};
                items2 = new String[]{"Ninguno", "Sedantes","Diureticos","Hipotensores","Antiparkinsonianos","Antidepresivos","Otros medicamentos"};
                items2Values = new int[]{0,1,1,1,1,1,1};
                items3 = new String[]{"Ninguno", "Alteraciones visuales", "Alteraciones auditivas","Extremidades (ictus)"};
                items3Values = new int[]{0,1,1,1};
                items4 = new String[]{"Orientado", "Confuso"};
                items4Values = new int[]{0,1};
                items5 = new String[]{"Normal","Segura con ayuda","Insegura con ayuda/sin ayuda","Imposible"};
                items5Values = new int[]{0,1,1,1};
                tipoEscala="Puntos";
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
