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

    Bitmap bmp,scaleBmp;
    Date dateObj;
    DateFormat dateFormat;

    ArrayList<String> dados = new ArrayList<>();

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

                        //Limpar lista
                        dados.clear();

                        //Adicionar raio e espessura
                        dados.add("Raio (R) = " + textRaio);
                        dados.add("Espessura (e) = " + textEspessura);

                        //Área
                        double areaMaior = Math.PI*Math.pow(medidaRaio,2);
                        double areaMenor = Math.PI*Math.pow(medidaRaioMenor,2);
                        double area = areaMaior - areaMenor;
                        String textArea = Utils.arredondar(area);
                        textViewArea.setText("Área = " + textArea);
                        dados.add("Área = " + textArea);

                        //Perímetro
                        double perimetro = 2*Math.PI*medidaRaio;
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);
                        dados.add("Perímetro = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaMaior = Math.PI*Math.pow(medidaRaio,4) /4;
                        double momentoInerciaMenor = Math.PI*Math.pow(medidaRaioMenor,4) /4;
                        double momentoInercia = momentoInerciaMaior - momentoInerciaMenor;
                        String textMomentoInercia = Utils.arredondar(momentoInercia);
                        textViewIx.setText("Ix = " + textMomentoInercia);
                        textViewIy.setText("Iy = " + textMomentoInercia);
                        dados.add("Momento de inércia em x (Ix) = " + textMomentoInercia);
                        dados.add("Momento de inércia em y (Iy) = " + textMomentoInercia);

                        //Raio de giração
                        double raioGiracao =  Math.sqrt((momentoInercia/area));
                        String textRaioGiracao = Utils.arredondar(raioGiracao);
                        textViewix.setText("ix = "+textRaioGiracao);
                        textViewiy.setText("iy = "+textRaioGiracao);
                        dados.add("Raio de giração em x (ix) = " + textRaioGiracao);
                        dados.add("Raio de giração em y (iy) = " + textRaioGiracao);

                        //Módulo plastico
                        double moduloPlastico = 4*Math.pow(medidaRaio,3)*(1-Math.pow((1-(medidaEspessura/medidaRaio)),3))/3;
                        String textModuloPlastico = Utils.arredondar(moduloPlastico);
                        textViewZx.setText("Zx = "+textModuloPlastico);
                        textViewZy.setText("Zy = "+textModuloPlastico);
                        dados.add("Módulo plástico em x (Zx) = " + textModuloPlastico);
                        dados.add("Módulo plástico em y (Zy) = " + textModuloPlastico);

                        //Modulo Elastico
                        double moduloElastico = Math.PI*Math.pow(medidaRaio,2)*medidaEspessura;
                        String textModuloElastico = Utils.arredondar(moduloElastico);
                        textViewWx.setText("Wx = "+textModuloElastico);
                        textViewWy.setText("Wy = "+textModuloElastico);
                        dados.add("Módulo elástico em x (Wx) = " + textModuloElastico);
                        dados.add("Módulo elástico em y (Wy) = " + textModuloElastico);

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
                break;
            case R.id.idNotacao:
                Intent intent2 = new Intent(this, NotacoesActivity.class);
                startActivity(intent2);
                break;
            case R.id.itemExportar:
                if (textRaio != null && !textRaio.equals("") && textEspessura != null && !textEspessura.equals("")){
                    createPDF();
                }else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void createPDF() {

        //Configurar imagem
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.tela_circulo_vazado);
        scaleBmp = Bitmap.createScaledBitmap(bmp,75,75,false);

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

        canvas.drawBitmap(scaleBmp,pageWidth/2f-(scaleBmp.getWidth()/2f),60,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(12.0f);

        int startXPosition = 40;
        int endXPosition = mypageInfo1.getPageWidth() -40;
        int startYPosition = 180;

        for (int i=0;i<dados.size();i++){
            canvas.drawText(dados.get(i),startXPosition,startYPosition,myPaint);
            canvas.drawLine(startXPosition,startYPosition+8,endXPosition,startYPosition+8,myPaint);
            startYPosition +=25;
        }


        //Fim da página
        myPdfDocument.finishPage(myPage1);

        String nomeArquivo = "/tela_circulo_vazado " + date+".pdf";
        String filePath = Environment.getExternalStorageDirectory().getPath()+nomeArquivo;
        File file = new File(filePath);

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "PDF salvo em " + filePath, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Erro ao gerar PDF!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        myPdfDocument.close();
    }
}
