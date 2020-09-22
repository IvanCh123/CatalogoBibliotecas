package cr.ac.ucr.ecci.eseg.catbi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.BusquedaViewModel;

public class RecycleViewBibliotecaConfig {
    private Context contexto;
    private  bibliotecaAdapter biblioAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<ListarBibliotecas> listaBiblio, List<String>keys){
        contexto = context;
        biblioAdapter=new bibliotecaAdapter(listaBiblio,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(contexto));
        recyclerView.setAdapter(biblioAdapter);
    }


    class BibliotecaItem extends RecyclerView.ViewHolder {
        private TextView biblioteca_horario;
        private TextView biblioteca_nombre;
        private TextView biblioteca_telefono;

        private String keys;

        public BibliotecaItem(ViewGroup parent){
            super(LayoutInflater.from(contexto).inflate(R.layout.biblioteca_list_item,parent,false));

            biblioteca_horario=(TextView) itemView.findViewById(R.id.biblioteca_horario);
            biblioteca_nombre=(TextView) itemView.findViewById(R.id.biblioteca_nombre);
            biblioteca_telefono=(TextView) itemView.findViewById(R.id.biblioteca_telefono);
        }

        public void bind(ListarBibliotecas bibliotecas, String key){
            biblioteca_horario.setText(bibliotecas.getHorario());
            biblioteca_nombre.setText(bibliotecas.getNombre());
            biblioteca_telefono.setText(bibliotecas.getTelefono());

            this.keys=key;
        }
    }

    class bibliotecaAdapter extends RecyclerView.Adapter<BibliotecaItem>{
        private List<ListarBibliotecas> listaBiblioteca;
        private List<String> key;

        public bibliotecaAdapter(List<ListarBibliotecas> listaBiblioteca, List<String> key) {
            this.listaBiblioteca = listaBiblioteca;
            this.key = key;
            Log.d("T1",listaBiblioteca.get(1).getHorario());
        }

        @NonNull
        @Override
        public BibliotecaItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("T2","2");
            return new BibliotecaItem(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BibliotecaItem holder, final int position) {
            Log.d("T3","3");
            holder.bind(listaBiblioteca.get(position),key.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Coordenada",String.valueOf(listaBiblioteca.get(position).getLatitud()));
                    Log.d("Coordenada",String.valueOf(listaBiblioteca.get(position).getLongitud()));
                }
            });
        }

        @Override
        public int getItemCount() {
            Log.d("T4","4");
            return listaBiblioteca.size();
        }
    }
}
