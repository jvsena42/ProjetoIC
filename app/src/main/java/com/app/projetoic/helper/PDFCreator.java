package com.app.projetoic.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;

import com.app.projetoic.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PDFCreator {

    Bitmap image;
    Date dateObj;
    DateFormat dateFormat;

    Context context;
    ArrayList<String> dados = new ArrayList<>();

    public PDFCreator(Context context) {
        this.context = context;
        dados.clear();
    }

    public void addLine(String textLine){
        dados.add(textLine);
    }

    public void createPage(String pageName, Resources res, int idImage){
        //Configurar imagem
        this.image = BitmapFactory.decodeResource(res,idImage);
        Bitmap scaleBmp = Bitmap.createScaledBitmap(image,75,75,false);

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
        int startYPosition = 60+ scaleBmp.getHeight() + 40;

        for (int i=0;i<dados.size();i++){
            canvas.drawText(dados.get(i),startXPosition,startYPosition,myPaint);
            canvas.drawLine(startXPosition,startYPosition+8,endXPosition,startYPosition+8,myPaint);
            startYPosition +=25;
        }


        //Fim da página
        myPdfDocument.finishPage(myPage1);

        String nomeArquivo = "/"+ pageName + " " + date+".pdf";
        String filePath = Environment.getExternalStorageDirectory().getPath()+nomeArquivo;
        File file = new File(filePath);

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(context, "PDF salvo em " + filePath, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Erro ao gerar PDF!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        myPdfDocument.close();
    }
}
