package engenheiro.rios.IRR;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import engenheiro.rios.GuardaRios;
import engenheiro.rios.Login;
import engenheiro.rios.R;

public class IRR_1_6 extends AppCompatActivity {


    protected LinearLayout linearLayout;
    ArrayList<CheckBox> list;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irr_1_6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Novo Formulario");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        linearLayout = (LinearLayout) this.findViewById(R.id.irr_linear);
        String[] array=new String[]{"I. Canal sem alterações, estado natural",
                        "II. Canal ligeiramente perturbado",
                        "III. Início de uma importante alteração do canal",
                        "IV. Grande alteração do canal",
                        "V. Canal completamente alterado (canalizado, regularizado)"};
        list=Form_functions.createCheckboxes(array,linearLayout,this);




        list.get(0).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                   @Override
                                                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                       for (int i = 0; i < list.size(); i++) {
                                                           if (list.get(i).isChecked()){
                                                               Log.v("teste","id:"+i);
                                                           }
                                                       }
                                                   }
                                               }
        );







    }




    public void goto_next(View view){
        startActivity(new Intent(this, IRR_1_7.class));
        this.overridePendingTransition(0, 0);

    }

    public void goto_previous(View view){
        this.finish();
        this.overridePendingTransition(0, 0);
    }




    //menu action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.navigate_guardarios)
            startActivity(new Intent(this,GuardaRios.class));
        if(id==R.id.navigate_account)
            startActivity(new Intent(this,Login.class));
        return super.onOptionsItemSelected(item);
    }
}