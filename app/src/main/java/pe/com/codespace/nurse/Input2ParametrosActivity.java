package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Input2ParametrosActivity extends ActionBarActivity {
    double Param1, Param2;
    double resultado;
    String label1="", label2="", unidades="", descripcion="";
    int tipo = -1;
    EditText editText1 = null;
    EditText editText2 = null;
    TextView textViewResultado1 = null;
    TextView textViewResultado2 = null;
    TextView textViewDescription = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayShowTitleEnabled(false);
        setContentView(R.layout.activity_input2param);
        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewResultado2 = (TextView) findViewById(R.id.tvResultado2);
        textViewDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvTitleFormula = (TextView) findViewById(R.id.tvTitleFormula);
        TextView tvParam1 = (TextView) findViewById(R.id.tvInput1);
        TextView tvParam2 = (TextView) findViewById(R.id.tvInput2);
        editText1 = (EditText) findViewById(R.id.etInput1);
        editText2 = (EditText) findViewById(R.id.etInput2);
        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");

        switch (tipo){
            case 1://IMC
                tvTitleFormula.setText("Indice de Masa Corporal en Adultos");
                tvParam1.setText("Peso (kg): ");
                tvParam2.setText("Talla (cm): ");
                descripcion="Se calcula el IMC en base a la fórmula de la OMS.\n\n" +
                            "Bajo peso: < 18.5\nNormal: 18.5 - 24.99\nSobrepeso: >= 25.0\n" +
                            "Obesidad leve: 30.0 - 34.99\nObesidad moderada: 35.0 - 39.99\nObesidad mórbida: >= 40.0";
                break;
            case 2://Perdidas Insensibles
                tvTitleFormula.setText("Perdidas Insensibles en Adultos Normotermos");
                tvParam1.setText("Peso (kg): ");
                tvParam2.setText("Número de horas: ");
                descripcion="";
                break;
            case 3://SC en niños
                tvTitleFormula.setText("Superficie Corporal en Niños");
                tvParam1.setText("Peso (kg): ");
                tvParam2.setText("Talla (cm): ");
                descripcion="Se calcula la superficie corporal usando la fórmula de Haycock.";
                break;
            case 4://SC en adultos
                tvTitleFormula.setText("Superficie Corporal en Adultos");
                tvParam1.setText("Peso (kg): ");
                tvParam2.setText("Talla (cm): ");
                descripcion="Se calcula la superficie corporal usando la fórmula de Mollester.";
                break;
            case 10://Velocidad de Goteo
                tvTitleFormula.setText("Cálculo de la Velocidad de Goteo");
                tvParam1.setText("Volumen a infundir (ml): ");
                tvParam2.setText("Número de horas : ");
                descripcion="Esta fórmula calcula la velocidad de goteo de una infusión según el volumen a infundir y el tiempo en horas.\nSe considera 1ml = 20 gotas";
                break;
            case 11://
                tvTitleFormula.setText("Volumen de Infusión");
                tvParam1.setText("Velocidad de Goteo (gotas/min): ");
                tvParam2.setText("Número de horas : ");
                descripcion="Esta fórmula calcula el volumen total a infundir según la velocidad de goteo y el tiempo en horas.\nSe considera 1ml = 20 gotas";
                break;
            case 12://
                tvTitleFormula.setText("Tiempo de Infusión");
                tvParam1.setText("Volumen a infundir (ml): ");
                tvParam2.setText("Velocidad de goteo (gotas/min): ");
                descripcion="Esta fórmula calcula el tiempo total de infusión según el volumen total a infundir y la velocidad de goteo.\nSe considera 1ml = 20 gotas";
                break;
        }
        textViewDescription.setText(descripcion);

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewInput2Param);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.input, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();

                if(!Tools.isNumeric(s1) || ! Tools.isNumeric(s2)){
                    Toast.makeText(getApplicationContext(),"Ingrese los valores solicitados", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Param1 = Double.parseDouble(editText1.getText().toString());
                Param2 = Double.parseDouble(editText2.getText().toString());
                switch (tipo){
                    case 1://Indice de Masa Corporal
                        resultado = Formulas.IMC(Param1, Param2);
                        label1 = "IMC: ";
                        unidades="";

                        break;
                    case 2://Perdidas Insensibles
                        resultado = Formulas.PerdidaInsensibleAdultoNormotermo(Param1, Param2);
                        double agua = (double) Math.round((resultado/3)*100)/100;
                        label1 = "Perdidas Insensibles: ";
                        label2 = "Agua de Oxidación: ";
                        unidades = " cc.";

                        textViewResultado2.setText(label2 + agua + unidades);
                        textViewResultado2.setVisibility(View.VISIBLE);
                        break;
                    case 3://Superficie corporal en Niños
                        resultado = Formulas.ASCbyHaycock(Param1, Param2);
                        label1 = "Superficie Corporal: ";
                        unidades=" m2";

                        break;
                    case 4://Superficie corporal en Adultos
                        resultado = Formulas.ASCbyMollester(Param1, Param2);
                        label1 = "Superficie Corporal: ";
                        unidades=" m2";

                        break;
                    case 10://Velocidad de Goteo
                        resultado = Formulas.VelocidadGoteo(Param1, Param2);
                        label1 = "Velocidad de infusión: ";
                        unidades=" gotas/min";
                        break;
                    case 11://
                        resultado = Formulas.VolumenInfusion(Param1, Param2);
                        label1 = "Volumen de infusión: ";
                        unidades=" ml";
                        break;
                    case 12://
                        resultado = Formulas.TiempoInfusion(Param1, Param2);
                        label1 = "Tiempo de infusión: ";
                        unidades=" hr";
                        break;
                }
                textViewResultado1.setText(label1 + resultado + unidades);
                textViewResultado1.setVisibility(View.VISIBLE);

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                break;
            case R.id.action_clean:
                textViewResultado1.setVisibility(View.INVISIBLE);
                textViewResultado2.setVisibility(View.GONE);
                editText1.setText("");
                editText2.setText("");
                editText1.requestFocus();
                break;
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
