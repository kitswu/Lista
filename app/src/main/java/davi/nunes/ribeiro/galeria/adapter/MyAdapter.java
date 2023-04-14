package davi.nunes.ribeiro.galeria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import davi.nunes.ribeiro.galeria.R;
import davi.nunes.ribeiro.galeria.activity.MainActivity;
import davi.nunes.ribeiro.galeria.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter{
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity); // lê o arquivo xml de layout
        View v = inflater.inflate(R.layout.item_list, parent, false); // cria os elementos de layout e guarda num objeto view
        return new MyViewHolder(v); // guarda o objeto view em um novo view holder
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);
        View v = holder.itemView; // obtemos a view no holder

        // preenchemos a interface com os dados do item
        ImageView imvFoto = v.findViewById(R.id.imvPhoto);
        imvFoto.setImageURI(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.desc);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
