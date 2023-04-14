package davi.nunes.ribeiro.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import davi.nunes.ribeiro.galeria.R;
import davi.nunes.ribeiro.galeria.adapter.MyAdapter;
import davi.nunes.ribeiro.galeria.model.MyItem;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;
    ArrayList<MyItem> itens = new ArrayList<>(); // cria uma lista de itens
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem); //obtem o floating button pelo id
        fabAddItem.setOnClickListener(new View.OnClickListener() { //seta um "ouvidor" pra quando clicar no botão
            @Override
            public void onClick(View view) { //seta um método para realizar quando clicar
                Intent i = new Intent(MainActivity.this, NewItemActivity.class); //cria nova intent que diz para navegar de MainActivity para NewItemActivity
                startActivityForResult(i, NEW_ITEM_REQUEST); //starta a activity, pedindo um resultado
            }
        });
        RecyclerView rvItens = findViewById(R.id.rvItems); // obtem o recycler view pelo id

        myAdapter = new MyAdapter(this, itens); // cria um MyAdapter
        rvItens.setAdapter(myAdapter); // seta o MyAdapter como o adapter do recycler view
        rvItens.setHasFixedSize(true); // indica que não há variação de tamanho entre os itens da lista

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // cria um gerenciador de layout linear, fazendo com que a lista seja mostrada linearmente e na vertical
        rvItens.setLayoutManager(layoutManager); // seta o gerenciador no recycler view

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL); // cria um decorador de itens, uma linha dividindo cada item da lista
        rvItens.addItemDecoration(dividerItemDecoration); // seta o decorador no recycler view
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // recebe o resultado da activity
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ITEM_REQUEST){ // se o codigo de pedido for igual ao de novo item
            if (resultCode == Activity.RESULT_OK){ // se o codigo de retorno for igual ao codigo de sucesso
                MyItem myItem = new MyItem(); // cria novo item
                myItem.title = data.getStringExtra("title"); // guarda o titulo
                myItem.desc = data.getStringExtra("desc"); // guarda a descrição
                myItem.photo = data.getData(); // guarda a foto
                itens.add(myItem); // adiciona à lista de itens
                myAdapter.notifyItemInserted(itens.size()-1); // notifica o adapter de que um novo item foi inserido
            }
        }
    }
}