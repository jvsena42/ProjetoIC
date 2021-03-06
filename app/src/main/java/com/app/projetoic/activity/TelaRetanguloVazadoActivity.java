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

public class TelaRetanguloVazadoActivity extends AppCompatActivity {

    private EditText editTextBase;
    private EditText editTextAltura;
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

    private String textBase;
    private String textAltura;
    private  String textEspessura;

    private PDFCreator pdfCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_retangulo_vazado);

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

        //Configuracoes iniciais
        editTextBase = findViewById(R.id.editTextBase);
        editTextAltura = findViewById(R.id.editTextAltura);
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
                textBase = editTextBase.getText().toString();
                textAltura = editTextAltura.getText().toString();
                textEspessura = editTextEspessura.getText().toString();

                if (!textBase.isEmpty() && !textAltura.isEmpty() && !textEspessura.isEmpty()){
                    double medidaBase = Float.parseFloat(textBase);
                    double medidaAltura = Float.parseFloat(textAltura);
                    double medidaEspessura = Float.parseFloat(textEspessura);
                    double medidaBaseInterna = medidaBase-(2*medidaEspessura);
                    double medidaAlturaInterna = medidaAltura-(2*medidaEspessura);

                    if (medidaBaseInterna>0 && medidaAlturaInterna>0){

                        //Instanciar PDFCreator
                        pdfCreator = new PDFCreator(getApplicationContext());
                        pdfCreator.addLine("Base (b) = " + textBase);
                        pdfCreator.addLine("Altura (h) = " + textAltura);
                        pdfCreator.addLine("Espessura (e) = " + textEspessura);

                        //Área
                        double area = (medidaBase*medidaAltura)-(medidaBaseInterna*medidaAlturaInterna);
                        String textArea = Utils.arredondar(area);
                        textViewArea.setText("Área = " +textArea);
                        pdfCreator.addLine("Área = " + textArea);

                        //Perímetro
                        double perimetro = medidaBase*2+medidaAltura*2;
                        String textPerimetro = Utils.arredondar(perimetro);
                        textViewPerimetro.setText("P. Ext. = " + textPerimetro);
                        pdfCreator.addLine("Perímetro externo = " + textPerimetro);

                        //Momento de inercia
                        double momentoInerciaX = ((Math.pow(medidaAltura,3)*medidaBase/12)-(Math.pow(medidaAlturaInterna,3)*medidaBaseInterna/12));
                        double momentoInerciaY = ((Math.pow(medidaBase,3)*medidaAltura/12)-(Math.pow(medidaBaseInterna,3)*medidaAlturaInterna/12));
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

                        //Módulo plastico
                        double moduloPlasticoX = (Math.pow(medidaAltura,2)*medidaBase/4*(1-(1-2*medidaEspessura/medidaBase)*Math.pow(1-2*medidaEspessura/medidaAltura,2)));
                        double moduloPlasticoY = (Math.pow(medidaBase,2)*medidaAltura/4*(1-(1-2*medidaEspessura/medidaAltura)*Math.pow(1-2*medidaEspessura/medidaBase,2)));
                        String textModuloPlasticoX = Utils.arredondar(moduloPlasticoX);
                        String textModuloPlasticoY = Utils.arredondar(moduloPlasticoY);
                        textViewZx.setText("Zx = "+textModuloPlasticoX);
                        textViewZy.setText("Zy = "+textModuloPlasticoY);
                        pdfCreator.addLine("Módulo plástico em x (Zx) = " + textModuloPlasticoX);
                        pdfCreator.addLine("Módulo plástico em y (Zy) = " + textModuloPlasticoY);

                        //Módulo elástico
                        double moduloElasticoX = ((medidaBase*medidaAltura)*medidaEspessura + (Math.pow(medidaBase,2)*medidaEspessura/3));
                        double moduloElasticoY = ((medidaBase*medidaAltura)*medidaEspessura + (Math.pow(medidaAltura,2)*medidaEspessura/3));
                        String textModuloElasticoX = Utils.arredondar(moduloElasticoX);
                        String textModuloElasticoY = Utils.arredondar(moduloElasticoY);
                        textViewWx.setText("Wx = "+textModuloElasticoX);
                        textViewWy.setText("Wy = "+textModuloElasticoY);
                        pdfCreator.addLine("Módulo elástico em x (Wx) = " + textModuloElasticoX);
                        pdfCreator.addLine("Módulo elástico em y (Wy) = " + textModuloElasticoY);

                    }else {
                        Toast.makeText(TelaRetanguloVazadoActivity.this, "Erro! digite uma espessura menor ou medidas maiores para os lados", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(TelaRetanguloVazadoActivity.this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
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
                editTextEspessura.setText("");
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
                if (textBase != null && !textBase.equals("") && textAltura != null && !textAltura.equals("") && textEspessura != null && !textEspessura.equals("")) {
                    pdfCreator.createPage("tela_retangulo_vazado", getResources(), R.drawable.tela_retangulo_vazado);
                } else {
                    Toast.makeText(this, "Preencha todos os valores!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
