package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static pe.com.codespace.nurse.MyValues.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class Input3ParametrosActivity extends AppCompatActivity {
    double Param1, Param2;
    double resultado;
    String label1="", unidades="", descripcion="";
    int tipo = -1;
    EditText editText1 = null;
    EditText editText2 = null;
    EditText editText3 = null;
    TextView textViewResultado1 = null;
    TextView textViewResultado2 = null;
    TextView textViewDescription = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input3param);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setIcon(R.drawable.ic_launcher);
        }

        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);
        textViewResultado2 = (TextView) findViewById(R.id.tvResultado2);
        textViewDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvTitleFormula = (TextView) findViewById(R.id.tvTitleFormula);
        TextView tvParam1 = (TextView) findViewById(R.id.tvInput1);
        TextView tvParam2 = (TextView) findViewById(R.id.tvInput2);
        TextView tvParam3 = (TextView) findViewById(R.id.tvInput3);
        editText1 = (EditText) findViewById(R.id.etInput1);
        editText2 = (EditText) findViewById(R.id.etInput2);
        editText3 = (EditText) findViewById(R.id.etInput3);
        Intent intent = getIntent();
        tipo = intent.getExtras().getInt("formula");

        switch (tipo){
            case VOLUMEN_FARMACOS_UCI:
                tvTitleFormula.setText("Test con 3 Parametros");
                tvParam1.setText("Parametro 1: ");
                tvParam2.setText("Parametro 2: ");
                tvParam3.setText("Parametro 3: ");
                descripcion="Descripcion de la formula";
                break;
            case 1:case 2:
                break;
        }

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewInput3Param);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Analytics
        Tracker tracker = ((AnalyticsApplication)  getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        String nameActivity = getApplicationContext().getPackageName() + "." + this.getClass().getSimpleName();
        tracker.setScreenName(nameActivity);
        tracker.enableAdvertisingIdCollection(true);
        tracker.send(new HitBuilders.AppViewBuilder().build());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();

                if( !Tools.isNumeric(s1) || !Tools.isNumeric(s2) ||  !Tools.isNumeric(s3)) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_ingrese_valores), Toast.LENGTH_SHORT).show();
                    return false;
                }
                Param1 = Double.parseDouble(editText1.getText().toString());
                Param2 = Double.parseDouble(editText2.getText().toString());
                switch (tipo){
                    case VOLUMEN_FARMACOS_UCI:
                        resultado = Formulas.VolumenDopamina(Param1, Param2);
                        label1 = "IMC: ";
                        unidades="";
                        break;
                }
                textViewResultado1.setText(label1 + resultado + unidades);
                textViewResultado1.setVisibility(View.VISIBLE);
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(getCurrentFocus()!=null){
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                break;
            case R.id.action_clean:
                textViewResultado1.setVisibility(View.INVISIBLE);
                textViewResultado2.setVisibility(View.GONE);
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                editText1.requestFocus();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
