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

public class TelaRetanguloActivity extends AppCompatActivity {

    private EditText editTextBase;
    private EditText editTextAltura;
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

    private String textBase;
    private String textAltura;

    private PDFCreator pdfCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_retangulo);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextBase = findViewById(R.id.editTextBase);
        editTextAltura = findViewById(R.id.editTextAltura);
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
                textBase = editTextBase.getText().toString();
                textAltura = editTextAltura.getText().toString();

                if (!textBase.isEmpty() || !textAltura.isEmpty()) {

                    double medidaBase = Float.parseFloat(textBase);
                    double medidaAltura = Float.parseFloat(textAltura);

                    if (medidaBase > 0 && medidaAltura > 0) {

                        //Instanciar PDFCreator
                        pdfCreator = new PDFCreator(getApplicationContext());
                        pdfCreator.addLine("Base (b) = " + textBase);
                        pdfCreator.addLine("Altura (h) = " + textAltura);

                        //Área
                        double area = medidaBase*medidaAltura;
                        String textArea = Utils.arredondar(area);
                        textViewArea.setText("Área = " +textArea);
                        pdfCreator.addLine("Área = " + textArea);

                        //Perímetro
                        double perimetro = medidaBase*2+medidaAltura*2;
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);
                        pdfCreator.addLine("Perímetro externo = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaX =  (Math.pow(medidaAltura,3)*medidaBase/12);
                        double momentoInerciaY =  (Math.pow(medidaBase,3)*medidaAltura/12);
                        String textMomentoInerciaX = Utils.arredondar(momentoInerciaX);
                        String textMomentoInerciaY = Utils.arredondar(momentoInerciaY);
                        textViewIx.setText("Ix = " + textMomentoInerciaX);
                        textViewIy.setText("Iy = " + textMomentoInerciaY);
                        pdfCreator.addLine("Momento de inércia em x (Ix) = " + textMomentoInerciaX);
                        pdfCreator.addLine("Momento de inércia em y (Iy) = " + textMomentoInerciaY);

                        //Raio de giração
                        double raioGiracaoX = Math.sqrt((momentoInerciaX/area));
                        double raioGiracaoY = Math.sqrt((momentoInerciaY/area));
                        String textRaioGiracaoX = Utils.arredondar(raioGiracaoX);
                        String textRaioGiracaoY = Utils.arredondar(raioGiracaoY);
                        textViewix.setText("ix = " + textRaioGiracaoX);
                        textViewiy.setText("iy = " + textRaioGiracaoY);
                        pdfCreator.addLine("Raio de giração em x (ix) = " + textRaioGiracaoX);
                        pdfCreator.addLine("Raio de giração em y (iy) = " + textRaioGiracaoY);

                        //Módulo Plástico
                        double moduloPlasticoX = (Math.pow(medidaAltura,2)*medidaBase/4);
                        double moduloPlasticoY = (Math.pow(medidaBase,2)*medidaAltura/4);
                        String textModuloPlasticoX = Utils.arredondar(moduloPlasticoX);
                        String textModuloPlasticoY = Utils.arredondar(moduloPlasticoY);
                        textViewZx.setText("Zx = "+textModuloPlasticoX);
                        textViewZy.setText("Zy = "+textModuloPlasticoY);
                        pdfCreator.addLine("Módulo plástico em x (Zx) = " + textModuloPlasticoX);
                        pdfCreator.addLine("Módulo plástico em y (Zy) = " + textModuloPlasticoY);

                        //Módulo Elástico
                        double moduloElasticoX = (medidaBase*Math.pow(medidaAltura,2)/6);
                        double moduloElasticoY = (medidaAltura*Math.pow(medidaBase,2)/6);
                        String textModuloElasticoX = Utils.arredondar(moduloElasticoX);
                        String textModuloElasticoY = Utils.arredondar(moduloElasticoY);
                        textViewWx.setText("Wx = "+textModuloElasticoX);
                        textViewWy.setText("Wy = "+textModuloElasticoY);
                        pdfCreator.addLine("Módulo elástico em x (Wx) = " + textModuloElasticoX);
                        pdfCreator.addLine("Módulo elástico em y (Wy) = " + textModuloElasticoY);

                    } else {
                    Toast.makeText(TelaRetanguloActivity.this, "Utilize valores válidos!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TelaRetanguloActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
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
                editTextBase.setText("");
                editTextAltura.setText("");
                textBase = "";
                textAltura = "";

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
                if (textBase != null && !textBase.equals("") && textAltura != null && !textAltura.equals("")) {
                    pdfCreator.createPage("tela_retangulo", getResources(), R.drawable.tela_retangulo);
                } else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
