package ldso.rios.MainActivities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ldso.rios.DataBases.DB_functions;
import ldso.rios.DataBases.User;
import ldso.rios.Form.Form_functions;
import ldso.rios.R;

public class Limpeza extends AppCompatActivity {
    LinearLayout layoutLimpeza;
    protected ArrayList<RadioButton> question1;
    protected ArrayList<RadioButton> question2;
    protected ArrayList<RadioButton> question3;
    protected ArrayList<RadioButton> question4;
    protected ArrayList<RadioButton> question5;
    protected ArrayList<RadioButton> question6;
    protected ArrayList<RadioButton> question7;
    protected ArrayList<RadioButton> question8;
    protected ArrayList<RadioButton> question9;
    protected ArrayList<RadioButton> question10;
    protected ArrayList<RadioButton> question11;
    protected ArrayList<RadioButton> question12;
    protected ArrayList<RadioButton> question13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limpeza);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Resources r = getResources();
        float px_float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
        int px = (int) px_float;
        px_float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, r.getDisplayMetrics());
        int px2 = (int) px_float;


        LinearLayout.LayoutParams radioParams;
        radioParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        radioParams.setMargins(0, px2, 0, px);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutLimpeza = (LinearLayout) this.findViewById(R.id.limpeza_linear);

        TextView text = new TextView(this);
        text.setText("Vegetação");
        text.setLayoutParams(radioParams);
        layoutLimpeza.addView(text);
        String[] options1 = {"Com ramos sobre o leito",
                "Com ramos e arbustos no leito",
                "Com obstrução parcial do leito(<25%)",
                "Com obstrução (entre 25 e 50% do leite)",
                "Com obstrução do leito (> 50%)"};
        question1 = Form_functions.createRadioButtons(options1, layoutLimpeza, this);

        TextView text2 = new TextView(this);
        text2.setText("Árvores caídas no leito do rio");
        text2.setLayoutParams(radioParams);
        layoutLimpeza.addView(text2);
        String[] options2 = {"1 tronco",
                "Mais de 2 troncos",
                "formação de barreira (< 50%)",
                "formação de barreira (> 50%)"};
        question2 = Form_functions.createRadioButtons(options2, layoutLimpeza, this);

        TextView text3 = new TextView(this);
        text3.setText("Silvados");
        text3.setLayoutParams(radioParams);
        layoutLimpeza.addView(text3);
        String[] options3 = {"1 metro",
                "2 metros ",
                "Mais de 3 metros"};
        question3 = Form_functions.createRadioButtons(options3, layoutLimpeza, this);

        TextView text4 = new TextView(this);
        text4.setText("Invasoras Exóticas");
        text4.setLayoutParams(radioParams);
        layoutLimpeza.addView(text4);
        String[] options4 = {"Canas",
                "Acácias",
                "Penachos"};
        question4 = Form_functions.createRadioButtons(options4, layoutLimpeza, this);


        TextView text5 = new TextView(this);
        text5.setText("Espécies aquáticas Autóctones");
        text5.setLayoutParams(radioParams);
        layoutLimpeza.addView(text5);
        String[] options5 = {
                "Tabúal",
                "Lírios Amarelos"
                };
        question5 = Form_functions.createRadioButtons(options5, layoutLimpeza, this);

        TextView text6 = new TextView(this);
        text6.setText("Espécies aquáticas exóticas");
        text6.setLayoutParams(radioParams);
        layoutLimpeza.addView(text6);
        String[] options6 = {"Jacinto",
                "Azola",
                "Pinheirinha"};
        question6 = Form_functions.createRadioButtons(options6, layoutLimpeza, this);

        TextView text7 = new TextView(this);
        text7.setText("Lixo doméstico");
        text7.setLayoutParams(radioParams);
        layoutLimpeza.addView(text7);
        String[] options7 = {"Lixo (resíduos domésticos)"};
        question7 = Form_functions.createRadioButtons(options7, layoutLimpeza, this);

        TextView text8 = new TextView(this);
        text8.setText("Lixo industrial");
        text8.setLayoutParams(radioParams);
        layoutLimpeza.addView(text8);
        String[] options8 = {"Algumas sobras de Produção industrial",
                "Libertação de Quimicos e residuos não tratados",
                "Descargas ou acidentes de quimicos perigosos ",
                "Residuos Hospitalares e quimicos perigosos"};
        question8 = Form_functions.createRadioButtons(options8, layoutLimpeza, this);

        TextView text9 = new TextView(this);
        text9.setText("Entulhos e restos de obras");
        text9.setLayoutParams(radioParams);
        layoutLimpeza.addView(text9);
        String[] options9 = {"Barros e cerámicas",
                "Metais e vidros",
                "Plásticos",
                "Químicos"};
        question9 = Form_functions.createRadioButtons(options9, layoutLimpeza, this);

        TextView text10 = new TextView(this);
        text10.setText("Sedimentação");
        text10.setLayoutParams(radioParams);
        layoutLimpeza.addView(text10);
        String[] options10 = {"Muita",
                "Normal",
                "Pouca"};
        question10 = Form_functions.createRadioButtons(options10, layoutLimpeza, this);

        TextView text11 = new TextView(this);
        text11.setText("Erosão");
        text11.setLayoutParams(radioParams);
        layoutLimpeza.addView(text11);
        String[] options11 = {"Erosão"};
        question11 = Form_functions.createRadioButtons(options11, layoutLimpeza, this);

        TextView text12 = new TextView(this);
        text12.setText("Descargas de poluição");
        text12.setLayoutParams(radioParams);
        layoutLimpeza.addView(text12);
        String[] options12 = {"Domésticas excepcional",
                "Doméstica Pontual",
                "Doméstica Continua",
                "Industrial excepcional",
                "Industrial Pontual",
                "Industrial Continua"};
        question12 = Form_functions.createRadioButtons(options12, layoutLimpeza, this);

        TextView text13 = new TextView(this);
        text13.setText("Falta de água");
        text13.setLayoutParams(radioParams);
        layoutLimpeza.addView(text13);
        String[] options13 = {"Natural",
                "Antrópica"};
        question13 = Form_functions.createRadioButtons(options13, layoutLimpeza, this);

    }

    public void saveLimpeza(View view) {
        String q1 = Form_functions.getRadioButtonOption_string(question1);
        String q2 = Form_functions.getRadioButtonOption_string(question2);
        String q3 = Form_functions.getRadioButtonOption_string(question3);
        String q4 = Form_functions.getRadioButtonOption_string(question4);
        String q5 = Form_functions.getRadioButtonOption_string(question5);
        String q6 = Form_functions.getRadioButtonOption_string(question6);
        String q7 = Form_functions.getRadioButtonOption_string(question7);
        String q8 = Form_functions.getRadioButtonOption_string(question8);
        String q9 = Form_functions.getRadioButtonOption_string(question9);
        String q10 = Form_functions.getRadioButtonOption_string(question10);
        String q11 = Form_functions.getRadioButtonOption_string(question11);
        String q12 = Form_functions.getRadioButtonOption_string(question12);
        String q13 = Form_functions.getRadioButtonOption_string(question13);

//      String q6 = String.valueOf(question6.getText());

        DB_functions.saveLimpeza(this, Form_functions.getUser(this.getApplicationContext())[0],Form_functions.getUser(this.getApplicationContext())[1], q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13);
    }

    public void saveLimpezaDB() {
        new Thread() {
            public void run() {
                Limpeza.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast toast = Toast.makeText(Limpeza.this, "Formulário de limpeza submetido", Toast.LENGTH_LONG);
                        toast.show();
                        Limpeza.this.finish();

                    }
                });
            }
        }.start();
    }

    public void errorLimpezaDB(final String responseMessage){
        new Thread()
        {
            public void run()
            {
                Limpeza.this.runOnUiThread(new Runnable()
                {
                    public void run() {
                        Toast toast = Toast.makeText(Limpeza.this, "Erro na submissão: "+responseMessage, Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
            }
        }.start();

    }



}
