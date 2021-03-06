package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class TelaCirculoVazadoActivity extends AppCompatActivity {

    private EditText editTextRaio;
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

    private String textRaio,textEspessura;

    private PDFCreator pdfCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_circulo_vazado);

        //Permissões
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configirações iniciais
        editTextRaio = findViewById(R.id.editTextRaio);
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
                textRaio = editTextRaio.getText().toString();
                textEspessura = editTextEspessura.getText().toString();

                //Cálculo
                if (!textRaio.isEmpty() && !textEspessura.isEmpty()) {

                    //Converter medidas para Float
                    Float medidaRaio = Float.parseFloat(textRaio);
                    Float medidaEspessura = Float.parseFloat(textEspessura);
                    Float medidaRaioMenor = medidaRaio-medidaEspessura;

                    if (medidaEspessura >= medidaRaio) {
                        Toast.makeText(TelaCirculoVazadoActivity.this, "Erro! digite uma espessura menor.", Toast.LENGTH_SHORT).show();
                    } else {

                        //Instanciar PDFCreator
                        pdfCreator = new PDFCreator(getApplicationContext());

                        //Adicionar raio e espessura
                        pdfCreator.addLine("Raio (R) = " + textRaio);
                        pdfCreator.addLine("Espessura (e) = " + textEspessura);

                        //Área
                        double areaMaior = Math.PI*Math.pow(medidaRaio,2);
                        double areaMenor = Math.PI*Math.pow(medidaRaioMenor,2);
                        double area = areaMaior - areaMenor;
                        String textArea = Utils.arredondar(area);
                        textViewArea.setText("Área = " + textArea);
                        pdfCreator.addLine("Área = " + textArea);

                        //Perímetro
                        double perimetro = 2*Math.PI*medidaRaio;
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);
                        pdfCreator.addLine("Perímetro = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaMaior = Math.PI*Math.pow(medidaRaio,4) /4;
                        double momentoInerciaMenor = Math.PI*Math.pow(medidaRaioMenor,4) /4;
                        double momentoInercia = momentoInerciaMaior - momentoInerciaMenor;
                        String textMomentoInercia = Utils.arredondar(momentoInercia);
                        textViewIx.setText("Ix = " + textMomentoInercia);
                        textViewIy.setText("Iy = " + textMomentoInercia);
                        pdfCreator.addLine("Momento de inércia em x (Ix) = " + textMomentoInercia);
                        pdfCreator.addLine("Momento de inércia em y (Iy) = " + textMomentoInercia);

                        //Raio de giração
                        double raioGiracao =  Math.sqrt((momentoInercia/area));
                        String textRaioGiracao = Utils.arredondar(raioGiracao);
                        textViewix.setText("ix = "+textRaioGiracao);
                        textViewiy.setText("iy = "+textRaioGiracao);
                        pdfCreator.addLine("Raio de giração em x (ix) = " + textRaioGiracao);
                        pdfCreator.addLine("Raio de giração em y (iy) = " + textRaioGiracao);

                        //Módulo plastico
                        double moduloPlastico = 4*Math.pow(medidaRaio,3)*(1-Math.pow((1-(medidaEspessura/medidaRaio)),3))/3;
                        String textModuloPlastico = Utils.arredondar(moduloPlastico);
                        textViewZx.setText("Zx = "+textModuloPlastico);
                        textViewZy.setText("Zy = "+textModuloPlastico);
                        pdfCreator.addLine("Módulo plástico em x (Zx) = " + textModuloPlastico);
                        pdfCreator.addLine("Módulo plástico em y (Zy) = " + textModuloPlastico);

                        //Modulo Elastico
                        double moduloElastico = Math.PI*Math.pow(medidaRaio,2)*medidaEspessura;
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
                    Toast.makeText(TelaCirculoVazadoActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
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
                editTextRaio.setText("");
                textRaio = "";
                textEspessura = "";

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
                if (textRaio != null && !textRaio.equals("") && textEspessura != null && !textEspessura.equals("")) {
                    pdfCreator.createPage("tela_circulo_vazado", getResources(), R.drawable.tela_circulo_vazado);
                } else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
