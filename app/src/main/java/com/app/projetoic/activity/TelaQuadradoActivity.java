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

public class TelaQuadradoActivity extends AppCompatActivity {

    private EditText editTextLado;
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

    private PDFCreator pdfCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quadrado);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextLado = findViewById(R.id.editTextLado);
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
                if (!textLado.isEmpty()){

                    //Instanciar PDFCreator
                    pdfCreator = new PDFCreator(getApplicationContext());

                    double medidaLado = Double.parseDouble(textLado);
                    pdfCreator.addLine("Lado (L) = " + medidaLado);

                    //Área
                    double area = medidaLado*medidaLado;
                    String textArea = Utils.arredondar(area);
                    textViewArea.setText("Área = " +textArea);
                    pdfCreator.addLine("Área = " + textArea);

                    //Perímetro
                    double perimetro = medidaLado*4;
                    String textPerimetro = Utils.arredondar(perimetro);
                    textViewPerimetro.setText("P. Ext. = " + textPerimetro);
                    pdfCreator.addLine("Perímetro externo = " + textPerimetro);

                    //Momento de inercia
                    double momentoInercia = (Math.pow(medidaLado,4)/12);
                    String textMomentoInercia = Utils.arredondar(momentoInercia);
                    textViewIx.setText("Ix = " + textMomentoInercia);
                    textViewIy.setText("Iy = " + textMomentoInercia);
                    pdfCreator.addLine("Momento de inércia em x (Ix) = " + textMomentoInercia);
                    pdfCreator.addLine("Momento de inércia em y (Iy) = " + textMomentoInercia);

                    //Raio de giração
                    double raioGiracao = medidaLado/Math.sqrt(12);
                    String textRaioGiracao = Utils.arredondar(raioGiracao);
                    textViewix.setText("ix = " + textRaioGiracao);
                    textViewiy.setText("iy = " + textRaioGiracao);
                    pdfCreator.addLine("Raio de giração em x (ix) = " + textRaioGiracao);
                    pdfCreator.addLine("Raio de giração em y (iy) = " + textRaioGiracao);

                    //Módulo Plástico
                    double moduloPlastico = Math.pow(medidaLado,3)/4;
                    String textModuloPlastico = Utils.arredondar(moduloPlastico);
                    textViewZx.setText("Zx = "+textModuloPlastico);
                    textViewZy.setText("Zy = "+textModuloPlastico);
                    pdfCreator.addLine("Módulo plástico em x (Zx) = " + textModuloPlastico);
                    pdfCreator.addLine("Módulo plástico em y (Zy) = " + textModuloPlastico);

                    //Módulo Elástico
                    double moduloElastico = Math.pow(medidaLado,3)/6;
                    String textModuloElastico = Utils.arredondar(moduloElastico);
                    textViewWx.setText("Wx = "+textModuloElastico);
                    textViewWy.setText("Wy = "+textModuloElastico);
                    pdfCreator.addLine("Módulo elástico em x (Wx) = " + textModuloElastico);
                    pdfCreator.addLine("Módulo elástico em y (Wy) = " + textModuloElastico);

                }else {
                    Toast.makeText(TelaQuadradoActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
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
                if (textLado != null && !textLado.equals("")) {
                    pdfCreator.createPage("tela_quadrado", getResources(), R.drawable.tela_quadrado);
                } else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
