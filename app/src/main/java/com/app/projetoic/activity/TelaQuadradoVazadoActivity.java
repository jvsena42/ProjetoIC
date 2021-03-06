package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.projetoic.R;
import com.app.projetoic.helper.PDFCreator;
import com.app.projetoic.helper.Utils;

public class TelaQuadradoVazadoActivity extends AppCompatActivity {

    private EditText editTextLado;
    private EditText editTextEspessura;
    private TextView textViewArea;
    private TextView textViewPerimetro;
    private TextView textViewIx;
    private TextView textViewIy;
    private TextView textViewix;
    private TextView textViewiy;
    private TextView textViewZx;
    private TextView textViewZy;
    private TextView textViewWx;
    private TextView textViewWy;
    private Button buttonCalcular;

    private String textLado;
    private String textEspessura;

    private PDFCreator pdfCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quadrado_vazado);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configirações iniciais
        editTextLado = findViewById(R.id.editTextLadoQVazado);
        editTextEspessura = findViewById(R.id.editTextEspessura);
        textViewArea = findViewById(R.id.textViewArea);
        textViewPerimetro = findViewById(R.id.textViewPerimetro);
        textViewIx = findViewById(R.id.textViewIx);
        textViewIy = findViewById(R.id.textViewIy);
        textViewix = findViewById(R.id.textViewix);
        textViewiy = findViewById(R.id.textViewiy);
        textViewZx = findViewById(R.id.textViewZx);
        textViewZy = findViewById(R.id.textViewZy);
        textViewWx = findViewById(R.id.textViewWx);
        textViewWy = findViewById(R.id.textViewWy);
        buttonCalcular = findViewById(R.id.buttonCalcular);

        //Evento de clique
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recuperar dados
                textLado = editTextLado.getText().toString();
                textEspessura = editTextEspessura.getText().toString();

                //Cálculo
                if (!textLado.isEmpty() && !textEspessura.isEmpty()) {

                    //Converter medidas para Float
                    Float medidaLadoMaior = Float.parseFloat(textLado);
                    Float medidaEspessura = Float.parseFloat(textEspessura);
                    Float medidaLadoMenor = medidaLadoMaior - (2f * medidaEspessura);

                    if (medidaLadoMenor <0) {
                        Toast.makeText(TelaQuadradoVazadoActivity.this, "Erro! digite uma espessura menor.", Toast.LENGTH_SHORT).show();
                    } else {

                        //Instanciar PDFCreator
                        pdfCreator = new PDFCreator(getApplicationContext());
                        pdfCreator.addLine("Lado (L) = " + textLado);
                        pdfCreator.addLine("Espessura (e) = " + textEspessura);

                        //Área
                        double areaMaior = (medidaLadoMaior * medidaLadoMaior);
                        double areaMenor = (medidaLadoMenor * medidaLadoMenor);
                        double area = areaMaior - areaMenor;
                        String textArea = String.valueOf(area);
                        textViewArea.setText("Área = " + textArea);
                        pdfCreator.addLine("Área = " + textArea);

                        //Perímetro
                        double perimetro = medidaLadoMaior * 4;
                        String textPerimetro = String.valueOf(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);
                        pdfCreator.addLine("Perímetro externo = " + textPerimetro);

                        //Momento de inercia
                        double momentoInercia =  (Math.pow(medidaLadoMaior,4)/12)-(Math.pow(medidaLadoMenor,4)/12);
                        String textMomentoInercia = Utils.arredondar(momentoInercia);
                        textViewIx.setText("Ix = " + textMomentoInercia);
                        textViewIy.setText("Iy = " + textMomentoInercia);
                        pdfCreator.addLine("Momento de inércia em x (Ix) = " + textMomentoInercia);
                        pdfCreator.addLine("Momento de inércia em y (Iy) = " + textMomentoInercia);

                        //Raio de giração
                        double raioGiracao = Math.sqrt((momentoInercia/area));
                        String textRaioGiracao = Utils.arredondar(raioGiracao);
                        textViewix.setText("ix = "+textRaioGiracao);
                        textViewiy.setText("iy = "+textRaioGiracao);
                        pdfCreator.addLine("Raio de giração em x (ix) = " + textRaioGiracao);
                        pdfCreator.addLine("Raio de giração em y (iy) = " + textRaioGiracao);

                        //Módulo plastico
                        double moduloPlastico = Math.pow(medidaLadoMaior,3)/4*(1-Math.pow(1-2*medidaEspessura/medidaLadoMaior,3));
                        String textModuloPlastico = Utils.arredondar(moduloPlastico);
                        textViewZx.setText("Zx = "+textModuloPlastico);
                        textViewZy.setText("Zy = "+textModuloPlastico);
                        pdfCreator.addLine("Módulo plástico em x (Zx) = " + textModuloPlastico);
                        pdfCreator.addLine("Módulo plástico em y (Zy) = " + textModuloPlastico);

                        //Modulo Elastico
                        double moduloElastico = (Math.pow(medidaLadoMaior,4)-Math.pow(medidaLadoMaior-2*medidaEspessura,4))/(6*medidaLadoMaior);
                        String textModuloElastico = Utils.arredondar(moduloElastico);
                        textViewWx.setText("Wx = "+textModuloElastico);
                        textViewWy.setText("Wy = "+textModuloElastico);
                        pdfCreator.addLine("Módulo elástico em x (Wx) = " + textModuloElastico);
                        pdfCreator.addLine("Módulo elástico em y (Wy) = " + textModuloElastico);

                       /* Log.i("TESTEVALOR", "Lado maior: " + medidaLadoMaior.toString());
                        Log.i("TESTEVALOR", "Lado menor: " + medidaLadoMenor.toString());
                        Log.i("TESTEVALOR", "Espessura: " + medidaEspessura.toString());
                        Log.i("TESTEVALOR", "AreaMaior: " + areaMaior);
                        Log.i("TESTEVALOR", "AreaMenor: " + areaMenor);
                        Log.i("TESTEVALOR", "Area: " + area);*/
                    }

                } else {
                    Toast.makeText(TelaQuadradoVazadoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_secundario, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_limpar:

                //Limpar EditText
                editTextEspessura.setText("");
                editTextLado.setText("");
                textLado = "";

                textViewArea.setText( "Área =");
                textViewPerimetro.setText("P. Ext.= ");
                textViewIx.setText("Ix = " );
                textViewIy.setText("Iy = ");
                textViewix.setText("ix = ");
                textViewiy.setText("iy = ");
                textViewZx.setText("Zx = ");
                textViewZy.setText("Zy = ");
                textViewWx.setText("Wx = ");
                textViewWy.setText( "Wy = ");
                break;
            case R.id.idNotacao:
                Intent intent2 = new Intent(this, NotacoesActivity.class);
                startActivity(intent2);
                break;
            case R.id.itemExportar:
                if (textLado != null && !textLado.equals("") && textEspessura != null && !textEspessura.equals("")) {
                    pdfCreator.createPage("tela_quadrado_vazado", getResources(), R.drawable.tela_quadrado_vazado);
                } else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
