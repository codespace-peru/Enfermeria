package pe.com.codespace.nurse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static pe.com.codespace.nurse.MyValues.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class RegladetresActivity extends AppCompatActivity {
    double Param1, Param2, Param3;
    double resultado;
    int tipo = -1;
    EditText editText1 = null;
    EditText editText2 = null;
    EditText editText3 = null;
    TextView textViewResultado1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regladetres);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        textViewResultado1 = (TextView) findViewById(R.id.tvResultado1);

        TextView textViewDescription = (TextView) findViewById(R.id.tvDescription);
        editText1 = (EditText) findViewById(R.id.etInput1);
        editText2 = (EditText) findViewById(R.id.etInput2);
        editText3 = (EditText) findViewById(R.id.etInput3);
        Intent intent = getIntent();
        tipo = intent.getExtras().getInt(TIPO_FORMULAS);
        switch (tipo){
            case REGLATRES://Regla de tres simple
                getSupportActionBar().setTitle(getResources().getString(R.string.formula_regla_tres_title));
                textViewDescription.setText(getResources().getString(R.string.descripcion_regla3));
                break;
        }

        //Agregar el adView
        AdView adView = (AdView)this.findViewById(R.id.adViewReglasParam);
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_calculate:
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();

                if(!Tools.isNumeric(s1) || ! Tools.isNumeric(s2) || ! Tools.isNumeric(s3)){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_ingrese_valores), Toast.LENGTH_SHORT).show();
                    return false;
                }
                Param1 = Double.parseDouble(editText1.getText().toString());
                Param2 = Double.parseDouble(editText2.getText().toString());
                Param3 = Double.parseDouble(editText3.getText().toString());
                switch (tipo){
                    case REGLATRES://Regla de tres simple
                        resultado = Formulas.Regla3Simple(Param1, Param2, Param3);
                        break;
                }
                textViewResultado1.setText("X = " + Double.toString(resultado));

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(getCurrentFocus()!=null){
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }

                break;
            case R.id.action_clean:
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
                textViewResultado1.setText("X");
                editText1.requestFocus();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
