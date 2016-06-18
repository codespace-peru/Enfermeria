package pe.com.codespace.nurse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class PreferencesActivity extends AppCompatActivity {

    RadioButton rdbMetrico, rdbIngles;
    RadioGroup radioGroupSistemas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.preferencias_title);
        }

        radioGroupSistemas = (RadioGroup) findViewById(R.id.radioGroupSistemas);
        rdbMetrico = (RadioButton) radioGroupSistemas.findViewById(R.id.rdbMetrico);
        rdbIngles = (RadioButton) radioGroupSistemas.findViewById(R.id.rdbIngles);

        CargarPreferencias();

        radioGroupSistemas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rdbMetrico:
                        PrefSingleton.getInstance().putPreference(MyValues.SISTEMA_METRICO,true);
                        PrefSingleton.getInstance().putPreference(MyValues.SISTEMA_INGLES,false);
                        break;
                    case R.id.rdbIngles:
                        PrefSingleton.getInstance().putPreference(MyValues.SISTEMA_INGLES, true);
                        PrefSingleton.getInstance().putPreference(MyValues.SISTEMA_METRICO, false);
                        break;
                }
                //globalClass.editor.commit();
            }
        });

    }

    public void CargarPreferencias(){
        rdbMetrico.setChecked(PrefSingleton.getInstance().getPreference(MyValues.SISTEMA_METRICO,true));
        rdbIngles.setChecked(PrefSingleton.getInstance().getPreference(MyValues.SISTEMA_INGLES,false));
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
