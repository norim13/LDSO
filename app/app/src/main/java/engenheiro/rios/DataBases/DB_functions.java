package engenheiro.rios.DataBases;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import engenheiro.rios.FormIRR;
import engenheiro.rios.GuardaRios_form;
import engenheiro.rios.IRR.Form_functions;
import engenheiro.rios.Login;
import engenheiro.rios.Register;

/**
 * Created by filipe on 02/11/2015.
 */

public class DB_functions {

    public static void saveUser(final String nome, final String email, final String password, final String password_confirmation, final Register register_class) throws IOException, JSONException {

        new Thread(new Runnable() {
            public void run() {
                try {

                String url = "http://riosmais.herokuapp.com/api/v1/sign_up";
                URL object = null;
                object = new URL(url);
                HttpURLConnection con = null;
                con = (HttpURLConnection) object.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestMethod("POST");
                con.connect();
                JSONObject jsonObject = new JSONObject();
                JSONObject user = new JSONObject();
                    try {
                        jsonObject.accumulate("nome", nome);
                        jsonObject.accumulate("email", email);
                        jsonObject.accumulate("password", password);
                        jsonObject.accumulate("password_confirmation", password_confirmation);
                        jsonObject.accumulate("telef", "111111111");
                        user.accumulate("user", jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                Log.w("teste", user.toString());

                OutputStream os = null;
                os = con.getOutputStream();
                OutputStreamWriter osw = null;
                osw = new OutputStreamWriter(os, "UTF-8");
                osw.write(user.toString());
                osw.flush();
                osw.close();
                int HttpResult = 0;
                StringBuilder sb=null;
                sb= new StringBuilder();
                HttpResult = con.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }

                    final String[] error_txt = {""};
                    final Boolean[] error = {false};

                    try {
                        JSONObject obj = new JSONObject(sb.toString());
                        try {
                            error_txt[0] = obj.getString("error");
                            error[0] =true;
                        } catch (JSONException ignored) {
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.e("resposta:","a meio     error:"+ error_txt[0]);
                    register_class.register_response(error[0], error_txt[0]);

                    br.close();


                    System.out.println("" + sb.toString());

                } else {


                    System.out.println(con.getResponseMessage());
                }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


    public static void saveForm(final String token, final HashMap<Integer, Object> answers2, final ArrayList<Integer[]> values_irr) throws IOException, JSONException {
        Log.e("form","entrou");
        Log.e("form","values_irr_size:"+values_irr.size());
        String values="";
        for (int i=0;i<values_irr.size();i++){
            values=values+" |"+i+":";
            for (int j=0;j<values_irr.get(i).length;j++)
                values=values+" "+values_irr.get(i)[j]+",";

        }
        Log.e("form",values);

        new Thread(new Runnable() {
            public void run() {
                try {
                    String url = "http://riosmais.herokuapp.com/api/v2/form_irrs?user_email="+"fil.fmiranda@gmail.com"+"&user_token="+token;
                    Log.e("teste",url);
                    URL object = null;
                    object = new URL(url);
                    HttpURLConnection con = null;
                    con = (HttpURLConnection) object.openConnection();
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestMethod("GET");

                    con.connect();
                    JSONObject jsonObject = new JSONObject();
                    JSONObject user = new JSONObject();

                    JSONObject obj_json=new JSONObject();
                    JSONObject response=new JSONObject();
                    ArrayList<Float> af;
                    ArrayList<Integer> ai;

                    ArrayList<Integer> arrayList_Hidrogeomorfologia = new ArrayList<Integer> ();
                    ArrayList<Integer> arrayList_QualidadeDaAgua= new ArrayList<Integer>();
                    ArrayList<Integer> arrayList_AlteracoesAntropicas = new ArrayList<Integer>();
                    ArrayList<Integer> arrayList_CorredorEcologico = new ArrayList<Integer>();
                    ArrayList<Integer> arrayList_ParticipacaoPublica = new ArrayList<Integer>();
                    ArrayList<Integer> arrayList_OrganizacaoPlaneamento = new ArrayList<Integer>();





                    try {
                        response.accumulate("idRio", "201");
                        response.accumulate("margem", "1");
                        Log.e("form", "size:" + answers2.size());
                        response.accumulate("tipoDeVale", (int) answers2.get(1));

                        response.accumulate("perfilDeMargens", (int) answers2.get(2));

                        af = (ArrayList<Float>) answers2.get(3);
                        response.accumulate("larguraDaSuperficieDaAgua",af.get(0));
                        response.accumulate("profundidadeMedia",af.get(1));
                        response.accumulate("velocidadeMedia",af.get(2));
                        response.accumulate("seccao",af.get(3));
                        response.accumulate("caudal",af.get(4));

                        ai= (ArrayList<Integer>) answers2.get(4);
                        response.accumulate("substratoDasMargens_soloArgiloso",ai.get(0));
                        response.accumulate("substratoDasMargens_arenoso",ai.get(1));
                        response.accumulate("substratoDasMargens_pedregoso",ai.get(2));
                        response.accumulate("substratoDasMargens_rochoso",ai.get(3));
                        response.accumulate("substratoDasMargens_artificialPedra",ai.get(4));
                        response.accumulate("substratoDasMargens_artificialBetao",ai.get(5));

                        ai= (ArrayList<Integer>) answers2.get(5);
                        response.accumulate("substratoDoLeito_blocoseRocha",ai.get(0));
                        response.accumulate("substratoDoLeito_calhaus",ai.get(1));
                        response.accumulate("substratoDoLeito_cascalho",ai.get(2));
                        response.accumulate("substratoDoLeito_areia",ai.get(3));
                        response.accumulate("substratoDoLeito_limo",ai.get(4));
                        response.accumulate("substratoDoLeito_solo",ai.get(5));
                        response.accumulate("substratoDoLeito_artificial",ai.get(6));
                        response.accumulate("substratoDoLeito_naoEVisivel",ai.get(7));


                        response.accumulate("estadoGeraldaLinhadeAgua",(int) answers2.get(6));

                        ai= (ArrayList<Integer>) answers2.get(7);
                        response.accumulate("erosao_semErosao",ai.get(0));
                        response.accumulate("erosao_formacaomais3",ai.get(1));
                        response.accumulate("erosao_formacao1a3",ai.get(2));
                        response.accumulate("erosao_quedamuros",ai.get(3));
                        response.accumulate("erosao_rombos", ai.get(4));
                        arrayList_Hidrogeomorfologia.add(Form_functions.getmax(ai, values_irr.get(7)));

                        ai= (ArrayList<Integer>) answers2.get(8);
                        response.accumulate("sedimentacao_ausente", ai.get(0));
                        response.accumulate("sedimentacao_decomposicao",ai.get(1));
                        response.accumulate("sedimentacao_mouchoes",ai.get(2));
                        response.accumulate("sedimentacao_ilhassemveg",ai.get(3));
                        response.accumulate("sedimentacao_ilhascomveg",ai.get(4));
                        response.accumulate("sedimentacao_deposicaosemveg",ai.get(5));
                        response.accumulate("sedimentacao_deposicaocomveg",ai.get(6));
                        response.accumulate("sedimentacao_rochas", ai.get(7));
                        arrayList_Hidrogeomorfologia.add(Form_functions.getmax(ai, values_irr.get(8)));

                        Log.e("form", "Hidrogeomorfologia" + arrayList_Hidrogeomorfologia.get(0) + "," + arrayList_Hidrogeomorfologia.get(1));

                        af= (ArrayList<Float>) answers2.get(9);
                        response.accumulate("pH",af.get(0));
                        response.accumulate("condutividade",af.get(1));
                        response.accumulate("temperatura",af.get(2));
                        response.accumulate("nivelDeOxigenio",af.get(3));
                        response.accumulate("percentagemDeOxigenio",af.get(4));
                        response.accumulate("nitratos",af.get(5));
                        response.accumulate("nitritos",af.get(6));
                        response.accumulate("transparencia",af.get(7));

                        ai= (ArrayList<Integer>) answers2.get(10);
                        response.accumulate("oleo",ai.get(0));
                        response.accumulate("espuma",ai.get(1));
                        response.accumulate("esgotos",ai.get(2));
                        response.accumulate("impurezas",ai.get(3));
                        response.accumulate("sacosDePlastico",ai.get(4));
                        response.accumulate("latas",ai.get(5));
                        //response.accumulate("indiciosNaAgua_outros", "");
                        Log.e("form", "a ler:" + 10);
                        arrayList_QualidadeDaAgua.add(Form_functions.getmax(ai, values_irr.get(10)));



                        response.accumulate("corDaAgua", (int) answers2.get(11));
                        Log.e("form", "a ler:" + 11);
                        arrayList_QualidadeDaAgua.add(Form_functions.getmax((int) answers2.get(11), values_irr.get(11)));


                        response.accumulate("odorDaAgua", (int) answers2.get(12));
                        Log.e("form", "a ler:" + 12);
                        arrayList_QualidadeDaAgua.add(Form_functions.getmax((int) answers2.get(12), values_irr.get(12)));


                        ai= (ArrayList<Integer>) answers2.get(13);
                        response.accumulate("planarias",ai.get(0));
                        response.accumulate("hirudineos",ai.get(1));
                        response.accumulate("oligoquetas",ai.get(2));
                        response.accumulate("simulideos",ai.get(3));
                        response.accumulate("quironomideos",ai.get(4));
                        response.accumulate("ancilideo",ai.get(5));
                        response.accumulate("limnideo",ai.get(6));
                        response.accumulate("bivalves",ai.get(7));
                        response.accumulate("patasNadadoras",ai.get(8));
                        response.accumulate("pataLocomotoras",ai.get(9));
                        response.accumulate("trichopteroS",ai.get(10));
                        response.accumulate("trichopteroC",ai.get(11));
                        response.accumulate("odonata",ai.get(12));
                        response.accumulate("heteropteros",ai.get(13));
                        response.accumulate("plecopteros",ai.get(14));
                        response.accumulate("baetideo",ai.get(15));
                        response.accumulate("cabecaPlanar",ai.get(16));
                        response.accumulate("crustaceos",ai.get(17));
                        response.accumulate("acaros",ai.get(18));
                        response.accumulate("pulgaDeAgua",ai.get(19));
                        response.accumulate("insetos",ai.get(20));
                        response.accumulate("megalopteres", ai.get(21));
                        Log.e("form", "a ler:" + 13);
                        arrayList_QualidadeDaAgua.add(Form_functions.getmax(ai, values_irr.get(13)));

                        Log.e("form", "QualidadeDaAgua" + arrayList_QualidadeDaAgua.get(0)
                                + "," + arrayList_QualidadeDaAgua.get(1)
                                + "," + arrayList_QualidadeDaAgua.get(2)
                                + "," + arrayList_QualidadeDaAgua.get(3));



                        ai= (ArrayList<Integer>) answers2.get(14);
                        response.accumulate("intervencoes_edificios",ai.get(0));
                        response.accumulate("intervencoes_pontes",ai.get(1));
                        response.accumulate("intervencoes_limpezasDasMargens",ai.get(2));
                        response.accumulate("intervencoes_estabilizacaoDeMargens",ai.get(3));
                        response.accumulate("intervencoes_estabilizacaoDeMargens",ai.get(4));
                        response.accumulate("intervencoes_modelacaoDeMargensNatural",ai.get(5));
                        response.accumulate("intervencoes_modelacaoDeMargensArtificial",ai.get(6));
                        response.accumulate("intervencoes_barragem",ai.get(7));
                        response.accumulate("intervencoes_diques",ai.get(8));
                        response.accumulate("intervencoes_rioCanalizado",ai.get(9));
                        response.accumulate("intervencoes_rioEntubado",ai.get(10));
                        response.accumulate("intervencoes_esporoes",ai.get(11));
                        response.accumulate("intervencoes_paredoes",ai.get(12));
                        response.accumulate("intervencoes_tecnicasDeEngenhariaNatural", ai.get(13));
                        arrayList_AlteracoesAntropicas.add(Form_functions.getmax(ai, values_irr.get(14)));



                        response.accumulate("intervencoes_outras", "");



                        ai= (ArrayList<Integer>) answers2.get(15);
                        response.accumulate("ocupacao_florestaNatural",ai.get(0));
                        response.accumulate("ocupacao_florestaPlantadas",ai.get(1));
                        response.accumulate("ocupacao_matoAlto",ai.get(2));
                        response.accumulate("ocupacao_matoRasteiro",ai.get(3));
                        response.accumulate("ocupacao_pastagem",ai.get(4));
                        response.accumulate("ocupacao_agricultura",ai.get(5));
                        response.accumulate("ocupacao_espacoAbandonado",ai.get(6));
                        response.accumulate("ocupacao_jardins",ai.get(7));
                        response.accumulate("ocupacao_zonaEdificada",ai.get(8));
                        response.accumulate("ocupacao_zonaIndustrial",ai.get(9));
                        response.accumulate("ocupacao_ruas",ai.get(10));
                        response.accumulate("ocupacao_entulho", ai.get(11));
                        arrayList_AlteracoesAntropicas.add(Form_functions.getmax(ai, values_irr.get(15)));

                        ai= (ArrayList<Integer>) answers2.get(16);
                        response.accumulate("patrimonio_moinho",ai.get(0));
                        response.accumulate("patrimonio_acude",ai.get(1));
                        response.accumulate("patrimonio_microAcude1",ai.get(2));
                        response.accumulate("patrimonio_microAcude2",ai.get(3));
                        response.accumulate("patrimonio_barragem",ai.get(4));
                        response.accumulate("patrimonio_levadas",ai.get(5));
                        response.accumulate("patrimonio_pesqueiras",ai.get(6));
                        response.accumulate("patrimonio_escadasDePeixe",ai.get(7));
                        response.accumulate("patrimonio_poldras",ai.get(8));
                        response.accumulate("patrimonio_pontesSemPilar",ai.get(9));
                        response.accumulate("patrimonio_pontesComPilar",ai.get(10));
                        response.accumulate("patrimonio_passagemAVau",ai.get(11));
                        response.accumulate("patrimonio_barcos",ai.get(12));
                        response.accumulate("patrimonio_cais",ai.get(13));
                        response.accumulate("patrimonio_igreja",ai.get(14));
                        response.accumulate("patrimonio_solares",ai.get(15));
                        response.accumulate("patrimonio_nucleoHabitacional",ai.get(16));
                        response.accumulate("patrimonio_edificiosParticulares",ai.get(17));
                        response.accumulate("patrimonio_edificiosPublicos",ai.get(18));
                        response.accumulate("patrimonio_ETA",ai.get(19));
                        response.accumulate("patrimonio_descarregadoresDeAguasPluviais",ai.get(20));
                        response.accumulate("patrimonio_coletoresSaneamento",ai.get(21));
                        response.accumulate("patrimonio_defletoresArtificiais", ai.get(22));
                        response.accumulate("patrimonio_motaLateral", ai.get(23));
                        arrayList_AlteracoesAntropicas.add(Form_functions.getmax(ai, values_irr.get(16)));

                        ai= (ArrayList<Integer>) answers2.get(17);
                        response.accumulate("poluicao_descargasDomesticas",ai.get(0));
                        response.accumulate("poluicao_descargasETAR",ai.get(1));
                        response.accumulate("poluicao_descargasIndustriais",ai.get(2));
                        response.accumulate("poluicao_descargasQuimicas",ai.get(3));
                        response.accumulate("poluicao_descargasAguasPluviais",ai.get(4));
                        response.accumulate("poluicao_presencaCriacaoAnimais",ai.get(5));
                        response.accumulate("poluicao_lixeiras",ai.get(6));
                        response.accumulate("poluicao_lixoDomestico",ai.get(7));
                        response.accumulate("poluicao_entulho",ai.get(8));
                        response.accumulate("poluicao_monstrosDomesticos",ai.get(9));
                        response.accumulate("poluicao_sacosDePlastico",ai.get(10));
                        response.accumulate("poluicao_latasMaterialFerroso", ai.get(11));
                        response.accumulate("poluicao_queimadas", ai.get(12));
                        arrayList_AlteracoesAntropicas.add(Form_functions.getmax(ai, values_irr.get(17)));

                        String st="";
                        for (int i=0;i<arrayList_AlteracoesAntropicas.size();i++){
                            st+=arrayList_AlteracoesAntropicas.get(i)+" , ";
                        }
                        Log.e("form","_AlteraçõesAntropicas: "+st);

                        ai= (ArrayList<Integer>) answers2.get(18);
                        response.accumulate("salamandraLusitanica",ai.get(0));
                        response.accumulate("salamandraPintasAmarelas",ai.get(1));
                        response.accumulate("tritaoVentreLaranja", ai.get(2));
                        response.accumulate("raIberica", ai.get(3));
                        response.accumulate("raVerde", ai.get(4));
                        response.accumulate("sapoComum", ai.get(5));
                        arrayList_CorredorEcologico.add(Form_functions.getmax(ai, values_irr.get(18)));


                        ai= (ArrayList<Integer>) answers2.get(19);
                        response.accumulate("lagartoDeAgua",ai.get(0));
                        response.accumulate("cobraAguaDeColar", ai.get(1));
                        response.accumulate("cagado", ai.get(2));
                        arrayList_CorredorEcologico.add(Form_functions.getmax(ai, values_irr.get(19)));

                        response.accumulate("repteis_outro", "");

                        ai= (ArrayList<Integer>) answers2.get(20);
                        response.accumulate("guardaRios",ai.get(0));
                        response.accumulate("garcaReal",ai.get(1));
                        response.accumulate("melroDeAgua",ai.get(2));
                        response.accumulate("galinhaDeAgua", ai.get(3));
                        response.accumulate("patoReal", ai.get(4));
                        response.accumulate("tentilhaoComum", ai.get(5));
                        response.accumulate("chapimReal", ai.get(6));
                        arrayList_CorredorEcologico.add(Form_functions.getmax(ai, values_irr.get(20)));

                        response.accumulate("aves_outro", "");

                        ai= (ArrayList<Integer>) answers2.get(21);
                        response.accumulate("lontras",ai.get(0));
                        response.accumulate("morcegosDeAgua",ai.get(1));
                        response.accumulate("toupeiraDaAgua", ai.get(2));
                        response.accumulate("ratoDeAgua", ai.get(3));
                        response.accumulate("ouricoCacheiro", ai.get(4));
                        response.accumulate("armilho", ai.get(5));
                        arrayList_CorredorEcologico.add(Form_functions.getmax(ai, values_irr.get(21)));

                        response.accumulate("mamiferos_outro", "");

                        ai= (ArrayList<Integer>) answers2.get(22);
                        response.accumulate("enguia",ai.get(0));
                        response.accumulate("lampreia",ai.get(1));
                        response.accumulate("salmao", ai.get(2));
                        response.accumulate("truta", ai.get(3));
                        response.accumulate("bogaPortuguesa", ai.get(4));
                        response.accumulate("bogaDoNorte", ai.get(5));
                        arrayList_CorredorEcologico.add(Form_functions.getmax(ai, values_irr.get(22)));

                        response.accumulate("peixes_outro", "");

                        ai= (ArrayList<Integer>) answers2.get(23);
                        response.accumulate("percaSol",ai.get(0));
                        response.accumulate("tartarugaDaFlorida",ai.get(1));
                        response.accumulate("caranguejoPeludoChines",ai.get(2));
                        response.accumulate("gambusia",ai.get(3));
                        response.accumulate("mustelaVison", ai.get(4));
                        response.accumulate("lagostimVermelho", ai.get(5));
                        response.accumulate("trutaArcoIris", ai.get(6));
                        response.accumulate("achiga", ai.get(7));
                        arrayList_CorredorEcologico.add(Form_functions.getmax(ai, values_irr.get(23)));

                        response.accumulate("fauna_outro", "");

                        ai= (ArrayList<Integer>) answers2.get(24);
                        response.accumulate("salgueiral",ai.get(0));
                        response.accumulate("amial",ai.get(1));
                        response.accumulate("freixal",ai.get(2));
                        response.accumulate("choupal",ai.get(3));
                        response.accumulate("ulmeiral",ai.get(4));
                        response.accumulate("sanguinos",ai.get(5));
                        response.accumulate("ladual",ai.get(6));
                        response.accumulate("tramazeiras", ai.get(7));
                        response.accumulate("carvalhal", ai.get(8));
                        response.accumulate("sobreiral", ai.get(9));
                        response.accumulate("azinhal", ai.get(10));
                        arrayList_CorredorEcologico.add(Form_functions.getmax(ai, values_irr.get(24)));
                        response.accumulate("flora_outro", "");


                        response.accumulate("conservacaoBosqueRibeirinho",(int) answers2.get(25));

                        ai= (ArrayList<Integer>) answers2.get(26);
                        response.accumulate("silvas",ai.get(0));
                        response.accumulate("ervaDaFortuna",ai.get(1));
                        response.accumulate("plumas", ai.get(2));
                        response.accumulate("lentilhaDaAgua", ai.get(3));
                        response.accumulate("pinheirinha", ai.get(4));
                        response.accumulate("jacintoDeAgua", ai.get(5));
                        arrayList_CorredorEcologico.add(Form_functions.getmax(ai, values_irr.get(26)));
                        response.accumulate("vegetacaoInvasora_outro", "");


                        response.accumulate("obstrucaoDoLeitoMargens", (int) answers2.get(27));
                        arrayList_CorredorEcologico.add(Form_functions.getmax((int) answers2.get(27), values_irr.get(27)));


                        response.accumulate("disponibilizacaoDeInformacao", (int) answers2.get(28));
                        arrayList_ParticipacaoPublica.add(Form_functions.getmax((int) answers2.get(28), values_irr.get(28)));
                        response.accumulate("envolvimentoPublico", (int) answers2.get(29));
                        arrayList_ParticipacaoPublica.add(Form_functions.getmax((int) answers2.get(29), values_irr.get(29)));
                        response.accumulate("acao", (int) answers2.get(30));
                        arrayList_ParticipacaoPublica.add(Form_functions.getmax((int) answers2.get(30), values_irr.get(30)));


                        response.accumulate("legislacao", (int) answers2.get(31));
                        arrayList_OrganizacaoPlaneamento.add(Form_functions.getmax((int) answers2.get(31), values_irr.get(31)));
                        response.accumulate("estrategia", (int) answers2.get(32));
                        arrayList_OrganizacaoPlaneamento.add(Form_functions.getmax((int) answers2.get(32), values_irr.get(32)));
                        response.accumulate("gestaoDasIntervencoes", (int) answers2.get(33));
                        arrayList_OrganizacaoPlaneamento.add(Form_functions.getmax((int) answers2.get(33), values_irr.get(33)));

                        response.accumulate("irr_hidrogeomorfologia", Collections.max(arrayList_Hidrogeomorfologia));
                        response.accumulate("irr_qualidadedaagua",Collections.max(arrayList_QualidadeDaAgua));
                        response.accumulate("irr_alteracoesantropicas",Collections.max(arrayList_AlteracoesAntropicas));
                        response.accumulate("irr_corredorecologico",Collections.max(arrayList_CorredorEcologico));
                        response.accumulate("irr_participacaopublica", Collections.max(arrayList_ParticipacaoPublica));
                        response.accumulate("irr_organizacaoeplaneamento", Collections.max(arrayList_OrganizacaoPlaneamento));
                        ArrayList<Integer> array_final= new ArrayList<Integer>();
                        array_final.add(Collections.max(arrayList_Hidrogeomorfologia));
                        array_final.add(Collections.max(arrayList_QualidadeDaAgua));
                        array_final.add(Collections.max(arrayList_AlteracoesAntropicas));
                        array_final.add(Collections.max(arrayList_CorredorEcologico));
                        array_final.add(Collections.max(arrayList_ParticipacaoPublica));
                        array_final.add(Collections.max(arrayList_OrganizacaoPlaneamento));
                        response.accumulate("irr",Collections.max(array_final));

                        obj_json.accumulate("form_irr",response);
                    } catch (JSONException e) {
                        Log.e("teste","nao criou o json");
                        e.printStackTrace();
                    }

                    String veryLongString=obj_json.toString();
                    int maxLogSize = 1000;
                    for(int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
                        int start = i * maxLogSize;
                        int end = (i+1) * maxLogSize;
                        end = end > veryLongString.length() ? veryLongString.length() : end;
                        Log.e("teste", veryLongString.substring(start, end));
                    }


                    OutputStream os = null;
                    os = con.getOutputStream();
                    OutputStreamWriter osw = null;
                    osw = new OutputStreamWriter(os, "UTF-8");
                    osw.write(obj_json.toString());
                    osw.flush();
                    osw.close();
                    int HttpResult = 0;
                    StringBuilder sb=null;
                    sb= new StringBuilder();
                    HttpResult = con.getResponseCode();
                    Log.e("teste","antes de ver se esta ok: "+HttpResult);
                    if (HttpResult == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }


                        br.close();
                        String erro_sb="";

                        try {
                            JSONObject obj = new JSONObject(sb.toString());
                            try {
                                erro_sb = obj.getString("error");
                            } catch (JSONException ignored) {
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("teste", "erro_sb:" + erro_sb);

                    } else {
                        Log.e("teste","dentro do else");
                        Log.e("teste","nao deu a conection");
                        Log.e("teste", con.getResponseMessage());
                        System.out.println("erro:" + con.getResponseMessage());
                        System.out.println("erro:"+con.getErrorStream());

                        BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        br.close();
                        Log.e("teste","sb:"+sb.toString());

                        Log.e("teste", "fodeu");

                        StringBuilder builder = new StringBuilder();
                        builder.append(con.getResponseCode())
                                .append(" ")
                                .append(con.getResponseMessage())
                                .append("\n");

                        Map<String, List<String>> map = con.getHeaderFields();
                        for (Map.Entry<String, List<String>> entry : map.entrySet())
                        {
                            if (entry.getKey() == null)
                                continue;
                            builder.append( entry.getKey())
                                    .append(": ");

                            List<String> headerValues = entry.getValue();
                            Iterator<String> it = headerValues.iterator();
                            if (it.hasNext()) {
                                builder.append(it.next());

                                while (it.hasNext()) {
                                    builder.append(", ")
                                            .append(it.next());
                                }
                            }

                            builder.append("\n");
                        }
                        Log.e("teste","fodeu");
                        System.out.println(builder);

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }



    public static void login(final String email, final String password, final Login login) throws IOException, JSONException {


        new Thread(new Runnable() {
            public void run() {

                try {
                    String url = "http://riosmais.herokuapp.com/api/v1/sign_in";
                    URL object = null;
                    object = new URL(url);
                    HttpURLConnection con = null;
                    con = (HttpURLConnection) object.openConnection();
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestMethod("POST");
                    con.connect();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.accumulate("user_login", email);
                        jsonObject.accumulate("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Log.w("teste", jsonObject.toString());

                    OutputStream os = null;
                    os = con.getOutputStream();
                    OutputStreamWriter osw = null;
                    osw = new OutputStreamWriter(os, "UTF-8");
                    osw.write(jsonObject.toString());
                    osw.flush();
                    osw.close();
                    int HttpResult = 0;
                    StringBuilder sb=null;
                    sb= new StringBuilder();
                    HttpResult = con.getResponseCode();
                    if (HttpResult == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line).append("\n");
                        }

                        br.close();

                        final String[] authentication_token = {""};
                        final String[] error_txt = {""};
                        final Boolean[] error = {false};
                        String name="";
                        String email="";

                        /*{"id":2,"nome":"Filipe Miranda","access":null,"created_at":"2015-11-11T19:21:13.255Z",
                        "updated_at":"2015-11-11T19:21:13.255Z","email":"fil.fmiranda@gmail.com",
                        "authentication_token":"muWniRNBN-NSdF5vJdHy","distrito":null,"concelho":null,
                        "telef":null,"habilitacoes":null,"profissao":null,"formacao":null}
                         */

                        try {
                            JSONObject obj = new JSONObject(sb.toString());
                            try {
                                error_txt[0] = obj.getString("error");
                                error[0] =true;
                            } catch (JSONException ignored) {
                            }
                            try {
                                if(!error[0]) {
                                    authentication_token[0] = obj.getString("authentication_token");
                                    name=obj.getString("nome");
                                    email=obj.getString("email");
                                }
                            }
                            catch (JSONException ignored){
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("resposta:","a meio     error:"+ error_txt[0] +" autenticacao:"+ authentication_token[0]);
                        login.login_response(error[0],error_txt[0],authentication_token[0],name,email);




                        System.out.println("errozinho:" + sb.toString());

                    } else {
                        Log.e("teste","error: "+con.getResponseMessage());
                        System.out.println(con.getResponseMessage());
                    }


                } catch (IOException e) {
                    Log.e("teste","stacktrace");
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static void getForms(final String token, final FormIRR formIRR) throws IOException, JSONException {


        new Thread(new Runnable() {
            public void run() {


                String url = "http://riosmais.herokuapp.com/api/v2/form_irrs?user_email="+"fil.fmiranda@gmail.com"+"&user_token="+token;

                URL obj = null;
                try {
                    obj = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                HttpURLConnection con = null;
                try {
                    con = (HttpURLConnection) obj.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // optional default is GET
                try {
                    con.setRequestMethod("GET");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }

                //add request header
                con.setRequestProperty("Content-Type", "application/json");

                int responseCode = 0;
                try {
                    responseCode = con.getResponseCode();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = null;
                try {
                    in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String inputLine;
                StringBuffer response = new StringBuffer();

                try {
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //print result
                System.out.println(response.toString());
                Log.e("teste", response.toString());

                formIRR.formsFromUser(response.toString());
                try {
                    JSONArray jsonarray  = new JSONArray(response.toString());

                    Log.e("teste","tamanh:"+jsonarray.length());
                    for(int i=0; i<jsonarray.length(); i++){
                        JSONObject form_irr_json = jsonarray.getJSONObject(i);

                        String name = form_irr_json.getString("name");

                        System.out.println(name);
                        System.out.println(url);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }


    public static void saveGuardaRios(final GuardaRios_form guardaRios_form,final String token, final String q1, final String q2, final String q3, final String q4, final ArrayList<Integer> q5, final String q6) {


        new Thread(new Runnable() {
            public void run() {

                try {
                    String url = "http://riosmais.herokuapp.com/api/v2/guardarios?user_email="+"fil.fmiranda@gmail.com"+"&user_token="+token;
                    Log.e("teste",url);
                    URL object = null;
                    object = new URL(url);
                    HttpURLConnection con = null;
                    con = (HttpURLConnection) object.openConnection();
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestMethod("POST");
                    con.connect();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.accumulate("rio","201.02");
                    jsonObject.accumulate("local",q1);
                    jsonObject.accumulate("voar",q2);
                    jsonObject.accumulate("cantar",q3);
                    jsonObject.accumulate("alimentar",q4);
                    jsonObject.accumulate("parado",q5.get(0));
                    jsonObject.accumulate("beber",q5.get(1));
                    jsonObject.accumulate("cacar",q5.get(2));
                    jsonObject.accumulate("cuidarcrias",q5.get(3));
                    jsonObject.accumulate("outro",q6);
                    jsonObject.accumulate("lat","");
                    jsonObject.accumulate("lon","");
                    jsonObject.accumulate("nomeRio","");
                    JSONObject guardarios= new JSONObject();
                    guardarios.accumulate("guardario",jsonObject);



                    Log.w("teste", jsonObject.toString());
                    Log.e("teste",guardarios.toString());

                    OutputStream os = null;
                    os = con.getOutputStream();
                    OutputStreamWriter osw = null;
                    osw = new OutputStreamWriter(os, "UTF-8");
                    osw.write(guardarios.toString());
                    osw.flush();
                    osw.close();
                    int HttpResult = 0;
                    StringBuilder sb=null;
                    sb= new StringBuilder();
                    HttpResult = con.getResponseCode();
                    if (HttpResult == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line).append("\n");
                        }

                        br.close();

                       // Log.e("resposta:","a meio     error:"+ error_txt[0] +" autenticacao:"+ authentication_token[0]);
                        //login.login_response(error[0],error_txt[0],authentication_token[0],name,email);




                        System.out.println("errozinho:" + sb.toString());
                        guardaRios_form.saveGuardaRiosDB();

                    } else {
                        Log.e("teste","error: "+con.getResponseMessage());
                        System.out.println(con.getResponseMessage());
                    }


                } catch (IOException e) {
                    Log.e("teste","stacktrace");
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}



