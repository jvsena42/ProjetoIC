package com.app.projetoic.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.projetoic.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageQuadrado;
    private ImageView imageQuadradoVazado;
    private ImageView imageRetangulo;
    private ImageView imageRetanguloVazado;
    private ImageView imageCirculo;
    private ImageView imageCirculoVazado;
    private ImageView imageTresQuartosCirculo;
    private ImageView imageSemicirculo;
    private ImageView imageQuadrante;
    private ImageView imageTrianguloEquilatero;
    private ImageView imageTrianguloRetangulo;
    private ImageView imagePerfilL;
    private ImageView imagePerfilC;
    private ImageView imagePerfilI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar itens
        inicializarItens();

        //Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Geometrix");
        toolbar.setTitleTextColor(getColor(R.color.titleColor));
        setSupportActionBar(toolbar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageQuadrado:
                Intent intent = new Intent(MainActivity.this, TelaQuadradoActivity.class);
                startActivity(intent);
                break;
            case R.id.imageQuadradoVazado:
                Intent intent2 = new Intent(MainActivity.this, TelaQuadradoVazadoActivity.class);
                startActivity(intent2);
                break;
            case R.id.imageRetangulo:
                Intent intent3 = new Intent(MainActivity.this, TelaRetanguloActivity.class);
                startActivity(intent3);
                break;
            case R.id.imageRetanguloVazado:
                Intent intent4 = new Intent(MainActivity.this, TelaRetanguloVazadoActivity.class);
                startActivity(intent4);
                break;
            case R.id.imageCirculo:
                Intent intent5 = new Intent(MainActivity.this, TelaCirculoActivity.class);
                startActivity(intent5);
                break;
            case R.id.imageCirculoVazado:
                Intent intent6 = new Intent(MainActivity.this, TelaCirculoVazadoActivity.class);
                startActivity(intent6);
                break;
            case R.id.imageTresQuartosCirculo:
                Intent intent7 = new Intent(MainActivity.this, TresQuartosCirculoActivity.class);
                startActivity(intent7);
                break;
            case R.id.imageSemicirculo:
                Intent intent8 = new Intent(MainActivity.this, TelaSemicirculoActivity.class);
                startActivity(intent8);
                break;
            case R.id.imageQuadrante:
                Intent intent9 = new Intent(MainActivity.this, TelaQuadranteActivity.class);
                startActivity(intent9);
                break;
            case R.id.imageTrianguloEquilatero:
                Intent intent10 = new Intent(MainActivity.this, TelaTrianguloEquilateroActivity.class);
                startActivity(intent10);
                break;
            case R.id.imageTrianguloRetangulo:
                Intent intent11 = new Intent(MainActivity.this, TelaTrianguloRetanguloActivity.class);
                startActivity(intent11);
                break;
            case R.id.imagePerfilL:
                Intent intent12 = new Intent(MainActivity.this, TelaPerfilLActivity.class);
                startActivity(intent12);
                break;
            case R.id.imagePerfilC:
                Intent intent13 = new Intent(MainActivity.this, TelaPerfilCActivity.class);
                startActivity(intent13);
                break;
            case R.id.imagePerfilI:
                Intent intent14 = new Intent(MainActivity.this, TelaPerfilIActivity.class);
                startActivity(intent14);
                break;

        }
    }

    private void inicializarItens() {
        imageQuadrado = findViewById(R.id.imageQuadrado);
        imageQuadradoVazado = findViewById(R.id.imageQuadradoVazado);
        imageRetangulo = findViewById(R.id.imageRetangulo);
        imageRetanguloVazado = findViewById(R.id.imageRetanguloVazado);
        imageCirculo = findViewById(R.id.imageCirculo);
        imageCirculoVazado = findViewById(R.id.imageCirculoVazado);
        imageTresQuartosCirculo = findViewById(R.id.imageTresQuartosCirculo);
        imageSemicirculo = findViewById(R.id.imageSemicirculo);
        imageQuadrante = findViewById(R.id.imageQuadrante);
        imageTrianguloEquilatero = findViewById(R.id.imageTrianguloEquilatero);
        imageTrianguloRetangulo = findViewById(R.id.imageTrianguloRetangulo);
        imagePerfilL = findViewById(R.id.imagePerfilL);
        imagePerfilC = findViewById(R.id.imagePerfilC);
        imagePerfilI = findViewById(R.id.imagePerfilI);

        imageQuadrado.setOnClickListener(this);
        imageRetanguloVazado.setOnClickListener(this);
        imageRetangulo.setOnClickListener(this);
        imageQuadradoVazado.setOnClickListener(this);
        imageCirculo.setOnClickListener(this);
        imageCirculoVazado.setOnClickListener(this);
        imageTresQuartosCirculo.setOnClickListener(this);
        imageSemicirculo.setOnClickListener(this);
        imageQuadrante.setOnClickListener(this);
        imageTrianguloEquilatero.setOnClickListener(this);
        imageTrianguloRetangulo.setOnClickListener(this);
        imagePerfilL.setOnClickListener(this);
        imagePerfilC.setOnClickListener(this);
        imagePerfilI.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemSobre:
                Intent intent = new Intent(MainActivity.this, SobreActivity.class);
                startActivity(intent);
                break;
            case R.id.idNotacaoGeral:
                Intent intent2 = new Intent(MainActivity.this, NotacoesActivity.class);
                startActivity(intent2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
