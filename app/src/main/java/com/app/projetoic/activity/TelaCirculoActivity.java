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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    Bitmap bmp,scaleBmp;
    Date dateObj;
    DateFormat dateFormat;

    String[] dados = new String[]{"Raio = ", "Espessura = ", "Momento de inércia em x (Ix) = ",
            "Momento de inércia em y (Iy) = ","Raio de giração em x (ix) = ","Raio de giração em y (iy) = ",
            "Módulo plástico em x (Zx) =", "Módulo plástico em y (Zy) =", "Módulo elástico em x (Wx) = ",
            "Módulo plástico em y (Wy) = "};

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
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        //Configurar imagem
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.tela_circulo);
        scaleBmp = Bitmap.createScaledBitmap(bmp,75,75,false);

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
                String textRaio = editTextRaio.getText().toString();
                if (!textRaio.isEmpty()) {
                    double medidaRaio = Float.parseFloat(textRaio);

                    //Área
                    double area = (Math.PI * Math.pow(medidaRaio, 2));
                    String textArea = String.valueOf(area);
                    textViewArea.setText("Área = " + textArea);

                    //Perímetro
                    double perimetro = (Math.PI * 2 * medidaRaio);
                    String textPerimetro = String.valueOf(perimetro);
                    textViewPerimetro.setText("P. Ext.= " + textPerimetro);

                    //Momento de inercia
                    double momentoInercia = (Math.PI * Math.pow(medidaRaio, 4) / 4);
                    String textMomentoInercia = String.valueOf(momentoInercia);
                    textViewIx.setText("Ix = " + textMomentoInercia);
                    textViewIy.setText("Iy = " + textMomentoInercia);

                    //Raio de giração
                    double raioGiracao = medidaRaio / 2;
                    String textRaioGiracao = String.valueOf(raioGiracao);
                    textViewix.setText("ix = " + textRaioGiracao);
                    textViewiy.setText("iy = " + textRaioGiracao);

                    //Módulo Plástico
                    double moduloPlastico = (4 * Math.pow(medidaRaio, 3) / 3);
                    String textModuloPlastico = String.valueOf(moduloPlastico);
                    textViewZx.setText("Zx = " + textModuloPlastico);
                    textViewZy.setText("Zy = " + textModuloPlastico);

                    //Módulo Elástico
                    double moduloElastico = (Math.PI * Math.pow(medidaRaio, 3) / 4);
                    String textModuloElastico = String.valueOf(moduloElastico);
                    textViewWx.setText("Wx = " + textModuloElastico);
                    textViewWy.setText("Wy = " + textModuloElastico);

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
                break;
            case R.id.idNotacao:
                Intent intent2 = new Intent(this, NotacoesActivity.class);
                startActivity(intent2);
                break;
            case R.id.itemExportar:
                createPDF();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void createPDF() {

        //Recuperar data atual
        dateObj = new Date();
        dateFormat = new SimpleDateFormat("HHmmss");
        String date = dateFormat.format(dateObj);

        PdfDocument myPdfDocument = new PdfDocument();

        int pageWidth = 596;
        int pageHeight = 842;

        //Criar o estilo
        Paint myPaint = new Paint();

        //Criar a folha no tamanho A4
        PdfDocument.PageInfo mypageInfo1 = new PdfDocument.PageInfo.Builder(pageWidth,pageHeight,1).create();

        //Início da página
        PdfDocument.Page myPage1 = myPdfDocument.startPage(mypageInfo1);
        Canvas canvas = myPage1.getCanvas();

        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.setTextSize(14.0f);
        canvas.drawText("GEOMETRIX",pageWidth/2f,40,myPaint);

        canvas.drawBitmap(scaleBmp,pageWidth/2f-(75f/2f),60,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(12.0f);

        int startXPosition = 40;
        int endXPosition = mypageInfo1.getPageWidth() -40;
        int startYPosition = 180;

        for (int i=0;i<10;i++){
            canvas.drawText(dados[i],startXPosition,startYPosition,myPaint);
            canvas.drawLine(startXPosition,startYPosition+8,endXPosition,startYPosition+8,myPaint);
            startYPosition +=25;
        }


        //Fim da página
        myPdfDocument.finishPage(myPage1);

        File file = new File(Environment.getExternalStorageDirectory(),"tela_circulo "+date+".pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "PDF salvo na memória interna", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Erro ao gerar PDF!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        myPdfDocument.close();
    }
}
