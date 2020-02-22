package com.app.projetoic;

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

public class MainActivity extends AppCompatActivity {

    private ImageView imageQuadrado;
    private ImageView imageQuadradoVazado;
    private ImageView imageRetangulo;
    private ImageView imageRetanguloVazado;

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
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemConfiguracoes:
                Toast.makeText(getApplicationContext(),"Página configurações",Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemSobre:
                Toast.makeText(getApplicationContext(),"Página sobre",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void telaQuadrado (View view){
        Intent intent = new Intent(MainActivity.this,TelaQuadradoActivity.class);
        startActivity(intent);
    }

    private void inicializarItens(){
        imageQuadrado = findViewById(R.id.imageQuadrado);
        imageQuadradoVazado = findViewById(R.id.imageQuadradoVazado);
        imageRetangulo = findViewById(R.id.imageRetangulo);
        imageRetanguloVazado = findViewById(R.id.imageRetanguloVazado);
    }
}
