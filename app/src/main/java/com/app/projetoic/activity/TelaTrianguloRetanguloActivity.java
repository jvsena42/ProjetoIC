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

public class TelaTrianguloRetanguloActivity extends AppCompatActivity {

    private EditText editTextB;
    private EditText editTextC;
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

    private String textLadoB;
    private String textLadoC;

    private PDFCreator pdfCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_triangulo_retangulo);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextB = findViewById(R.id.editTextB);
        editTextC = findViewById(R.id.editTextC);
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
                textLadoB = editTextB.getText().toString();
                textLadoC = editTextC.getText().toString();
                if (!textLadoB.isEmpty() && !textLadoC.isEmpty()) {

                    double medidaLadoB = Float.parseFloat(textLadoB);
                    double medidaLadoC = Float.parseFloat(textLadoC);
                    double medidaLadoA = Math.sqrt(Math.pow(medidaLadoB,2)+Math.pow(medidaLadoC,2));

                    //Instanciar PDFCreator
                    pdfCreator = new PDFCreator(getApplicationContext());
                    pdfCreator.addLine("Lado A = " + Utils.arredondar(medidaLadoA));
                    pdfCreator.addLine("Lado B = " + textLadoB);
                    pdfCreator.addLine("Lado C = " + textLadoC);

                    //Área
                    double area = medidaLadoB * medidaLadoC/2;
                    String textArea = Utils.arredondar(area);
                    textViewArea.setText("Área = " + textArea);
                    pdfCreator.addLine("Área = " + textArea);

                    //Perímetro
                    double perimetro = medidaLadoA + medidaLadoB +medidaLadoC;
                    String textPerimetro = Utils.arredondar(perimetro);
                    textViewPerimetro.setText("P. Ext. = " + textPerimetro);
                    pdfCreator.addLine("Perímetro externo = " + textPerimetro);

                    //Momento de inercia
                    double momentoInerciaX = (medidaLadoC*Math.pow(medidaLadoB,3)/36);
                    double momentoInerciaY = (medidaLadoB*Math.pow(medidaLadoC,3)/36);
                    String textMomentoInerciaX = Utils.arredondar(momentoInerciaX);
                    String textMomentoInerciaY = Utils.arredondar(momentoInerciaY);
                    textViewIx.setText("Ix = " + textMomentoInerciaX);
                    textViewIy.setText("Iy = " + textMomentoInerciaY);
                    pdfCreator.addLine("Momento de inércia em x (Ix) = " + textMomentoInerciaX);
                    pdfCreator.addLine("Momento de inércia em y (Iy) = " + textMomentoInerciaY);

                    //Raio de giração
                    double raioGiracaoX = Math.sqrt(momentoInerciaX/area);
                    double raioGiracaoY = Math.sqrt(momentoInerciaY/area);
                    String textRaioGiracaoX = Utils.arredondar(raioGiracaoX);
                    String textRaioGiracaoY = Utils.arredondar(raioGiracaoY);
                    textViewix.setText("ix = " + textRaioGiracaoX);
                    textViewiy.setText("iy = " + textRaioGiracaoY);
                    pdfCreator.addLine("Raio de giração em x (ix) = " + textRaioGiracaoX);
                    pdfCreator.addLine("Raio de giração em y (iy) = " + textRaioGiracaoY);

                    //Módulo Plástico
                    double moduloPlasticoX = 0.0864*medidaLadoC*Math.pow(medidaLadoB, 2);
                    double moduloPlasticoY = 0.0864*medidaLadoB*Math.pow(medidaLadoC, 2);
                    String textModuloPlasticoX = Utils.arredondar(moduloPlasticoX);
                    String textModuloPlasticoY = Utils.arredondar(moduloPlasticoY);
                    textViewZx.setText("Zx = " + textModuloPlasticoX);
                    textViewZy.setText("Zy = " + textModuloPlasticoY);
                    pdfCreator.addLine("Módulo plástico em x (Zx) = " + textModuloPlasticoX);
                    pdfCreator.addLine("Módulo plástico em y (Zy) = " + textModuloPlasticoY);

                    //Módulo Elástico
                    double moduloElasticoX = (medidaLadoC*Math.pow(medidaLadoB, 2) / 24);
                    double moduloElasticoY = (medidaLadoB*Math.pow(medidaLadoC, 2) / 24);
                    String textModuloElasticoX = Utils.arredondar(moduloElasticoX);
                    String textModuloElasticoY = Utils.arredondar(moduloElasticoY);
                    textViewWx.setText("Wx = " + textModuloElasticoX);
                    textViewWy.setText("Wy = " + textModuloElasticoY);
                    pdfCreator.addLine("Módulo elástico em x (Wx) = " + textModuloElasticoX);
                    pdfCreator.addLine("Módulo elástico em y (Wy) = " + textModuloElasticoY);

                } else {
                    Toast.makeText(TelaTrianguloRetanguloActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
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
                editTextB.setText("");
                editTextC.setText("");
                textLadoB = "";
                textLadoC = "";
                break;
            case R.id.idNotacao:
                Intent intent2 = new Intent(this, NotacoesActivity.class);
                startActivity(intent2);
                break;
            case R.id.itemExportar:
                if (textLadoB != null && !textLadoB.equals("") && textLadoC != null && !textLadoC.equals("") ) {
                    pdfCreator.createPage("tela_triangulo_retangulo", getResources(), R.drawable.tela_triangulo_retangulo);
                } else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
