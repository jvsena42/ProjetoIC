package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TelaCirculoActivity extends AppCompatActivity {

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

    String textRaio, textArea, textPerimetro, textMomentoInerciaX, textMomentoInerciaY, textRaioGiracaoX,
            textRaioGiracaoY, textModuloPlasticoX, textModuloPlasticoY, textModuloElasticoX, textModuloElasticoY;

    private PDFCreator pdfCreator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_circulo);

        //Configurar Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);


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
                if (!textRaio.isEmpty()) {

                    //Instanciar PDFCreator
                    pdfCreator = new PDFCreator(getApplicationContext());

                    double medidaRaio = Float.parseFloat(textRaio);
                    pdfCreator.addLine("Raio (R) = " + textRaio);

                    //Área
                    double area = (Math.PI * Math.pow(medidaRaio, 2));
                    textArea = "Área = " + Utils.arredondar(area);
                    textViewArea.setText( textArea);
                    pdfCreator.addLine("Área = " + textArea);

                    //Perímetro
                    double perimetro = (Math.PI * 2 * medidaRaio);
                    textPerimetro = Utils.arredondar(perimetro);
                    textViewPerimetro.setText("P. Ext.= " + textPerimetro);
                    pdfCreator.addLine("Perímetro externo = " + textPerimetro);

                    //Momento de inercia
                    double momentoInercia = (Math.PI * Math.pow(medidaRaio, 4) / 4);
                    textMomentoInerciaX = Utils.arredondar(momentoInercia);
                    textMomentoInerciaY = Utils.arredondar(momentoInercia);
                    textViewIx.setText("Ix = " + textMomentoInerciaX);
                    textViewIy.setText("Iy = " + textMomentoInerciaY);
                    pdfCreator.addLine("Momento de inércia em x (Ix) = " + textMomentoInerciaX);
                    pdfCreator.addLine("Momento de inércia em y (Iy) = " + textMomentoInerciaY);

                    //Raio de giração
                    double raioGiracao = medidaRaio / 2;
                    textRaioGiracaoX =  Utils.arredondar(raioGiracao);
                    textRaioGiracaoY =  textRaioGiracaoX;
                    textViewix.setText("ix = " + textRaioGiracaoX);
                    textViewiy.setText("iy = " + textRaioGiracaoY);
                    pdfCreator.addLine("Raio de giração em x (ix) = " + textRaioGiracaoX);
                    pdfCreator.addLine("Raio de giração em y (iy) = " + textRaioGiracaoY);

                    //Módulo Plástico
                    double moduloPlastico = (4 * Math.pow(medidaRaio, 3) / 3);
                    textModuloPlasticoX = Utils.arredondar(moduloPlastico);
                    textModuloPlasticoY = textModuloPlasticoX;
                    textViewZx.setText("Zx = " + textModuloPlasticoX);
                    textViewZy.setText("Zy = " + textModuloPlasticoY);
                    pdfCreator.addLine("Módulo plástico em x (Zx) = " + textModuloPlasticoX);
                    pdfCreator.addLine("Módulo plástico em y (Zy) = " + textModuloPlasticoY);

                    //Módulo Elástico
                    double moduloElastico = (Math.PI * Math.pow(medidaRaio, 3) / 4);
                    textModuloElasticoX = Utils.arredondar(moduloElastico);
                    textModuloElasticoY = textModuloElasticoX;
                    textViewWx.setText("Wx = " + textModuloElasticoX);
                    textViewWy.setText( "Wy = " + textModuloElasticoY);
                    pdfCreator.addLine("Módulo elástico em x (Wx) = " + textModuloElasticoX);
                    pdfCreator.addLine("Módulo elástico em y (Wy) = " + textModuloElasticoY);


                } else {
                    Toast.makeText(TelaCirculoActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
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
                    pdfCreator.createPage("tela_quadrado", getResources(), R.drawable.tela_quadrado);
                } else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
