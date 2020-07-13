package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TelaQuadranteActivity extends AppCompatActivity {

    private EditText editTextRaio;
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

    private String textRaio;

    private PDFCreator pdfCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quadrante);

        //Configurar Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextRaio = findViewById(R.id.editTextRaio);
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
                textRaio = editTextRaio.getText().toString();
                if (!textRaio.isEmpty()){
                    double medidaRaio = Float.parseFloat(textRaio);

                    //Instanciar PDFCreator
                    pdfCreator = new PDFCreator(getApplicationContext());
                    pdfCreator.addLine("Raio (R) = " + textRaio);

                    //Área
                    double area = (Math.PI*Math.pow(medidaRaio,2)/4);
                    String textArea = Utils.arredondar(area);
                    textViewArea.setText("Área = " +textArea);
                    pdfCreator.addLine("Área = " + textArea);

                    //Perímetro
                    double perimetro = ((Math.PI*2*medidaRaio)/4)+(2*medidaRaio);
                    String textPerimetro = Utils.arredondar(perimetro);
                    textViewPerimetro.setText("P. Ext.= " + textPerimetro);
                    pdfCreator.addLine("Perímetro externo = " + textPerimetro);

                    //Momento de inercia
                    double momentoInercia = (Math.PI*Math.pow(medidaRaio,4)/16);
                    String textMomentoInercia = Utils.arredondar(momentoInercia);
                    textViewIx.setText("Ix = " + textMomentoInercia);
                    textViewIy.setText("Iy = " + textMomentoInercia);
                    pdfCreator.addLine("Momento de inércia em x (Ix) = " + textMomentoInercia);
                    pdfCreator.addLine("Momento de inércia em y (Iy) = " + textMomentoInercia);

                    //Raio de giração
                    double raioGiracao = Math.sqrt(momentoInercia/area);
                    String textRaioGiracao = Utils.arredondar(raioGiracao);
                    textViewix.setText("ix = " + textRaioGiracao);
                    textViewiy.setText("iy = " + textRaioGiracao);
                    pdfCreator.addLine("Raio de giração em x (ix) = " + textRaioGiracao);
                    pdfCreator.addLine("Raio de giração em y (iy) = " + textRaioGiracao);

                    //Módulo Plástico
                   /* float moduloPlastico = (float) (3*Math.PI*Math.pow(medidaRaio,3)/16);
                    String textModuloPlastico = String.valueOf(moduloPlastico);
                    textViewZx.setText("Zx = "+textModuloPlastico);
                    textViewZy.setText("Zy = "+textModuloPlastico);*/

                    //Módulo Elástico
                    double moduloElastico = (Math.PI*Math.pow(medidaRaio,3)/16);
                    String textModuloElastico = Utils.arredondar(moduloElastico);
                    textViewWx.setText("Wx = "+textModuloElastico);
                    textViewWy.setText("Wy = "+textModuloElastico);
                    pdfCreator.addLine("Módulo elástico em x (Wx) = " + textModuloElastico);
                    pdfCreator.addLine("Módulo elástico em y (Wy) = " + textModuloElastico);

                }else {
                    Toast.makeText(TelaQuadranteActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
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
                editTextRaio.setText("");
                textRaio = "";

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
                if (textRaio != null && !textRaio.equals("")) {
                    pdfCreator.createPage("tela_quadrante", getResources(), R.drawable.tela_quadrante);
                } else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
