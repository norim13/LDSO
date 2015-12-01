package engenheiro.rios.Form.IRR.Perguntas;

import android.content.Context;
import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import engenheiro.rios.Form.IRR.Pergunta;
import engenheiro.rios.Form.Form_functions;

/**
 * Created by filipe on 24/11/2015.
 */
public class checkPergunta extends Pergunta implements Serializable {


    private static final long serialVersionUID = -3522050436780683216L;
    protected ArrayList<CheckBox> check_list;

    public checkPergunta(String[] options, String title, String subtitle, Boolean obly, Boolean other_option) {
        super(options, title, subtitle, obly, other_option);

    }

    @Override
    public void generate(LinearLayout linearLayout, Context context) {
        this.linearLayout=linearLayout;
        this.context=context;
        this.check_list= Form_functions.createCheckboxes(this.options,this.linearLayout,this.context);
    }

    @Override
    public void generateView(LinearLayout linearLayout, Context context) {
        LinearLayout.LayoutParams radioParams;
        radioParams = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        radioParams.setMargins(0, 100, 0, 20);
        TextView textView=new TextView(context);
        textView.setText(this.title + " - " + this.subtitle);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        textView.setLayoutParams(radioParams);
        linearLayout.addView(textView);
        generate(linearLayout, context);
        for(CheckBox cb:this.check_list)
            cb.setEnabled(false);

    }

    @Override
    public void getAnswer() {
        if (check_list==null)return;
        this.response=Form_functions.getCheckboxes(check_list);
    }

    @Override
    public void forceresponse() {

    }

    @Override
    public void setAnswer() {
        ArrayList<Integer> al= (ArrayList<Integer>) this.response;
        if(al==null)
            return;
        for (int i=0;i<check_list.size();i++){
            if(al.get(i)==1)
                check_list.get(i).setChecked(true);
        }
    }

    @Override
    public Object getList() {

        return this.check_list ;
    }

}