package davi.nunes.ribeiro.galeria.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>(); // guarda a lista de itens cadastrados

    public List<MyItem> getItens(){
        return itens; //obtem a lista de itens
    }
}
