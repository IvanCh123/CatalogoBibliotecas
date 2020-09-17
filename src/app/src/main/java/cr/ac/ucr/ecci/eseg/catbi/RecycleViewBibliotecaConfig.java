package cr.ac.ucr.ecci.eseg.catbi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.BusquedaFragment;
import cr.ac.ucr.ecci.eseg.catbi.ui.Busqueda.BusquedaViewModel;
import cr.ac.ucr.ecci.eseg.catbi.ui.Ubicacion.UbicacionFragment;

public class RecycleViewBibliotecaConfig {
    private Context contexto;
    private  bibliotecaAdapter biblioAdapter;

    public void setConfig(RecyclerView recyclerView, BusquedaViewModel context, List<ListarBibliotecas> listaBiblio, List<String>keys){
        contexto= BusquedaFragment.getContext();
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
        }

        @NonNull
        @Override
        public BibliotecaItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BibliotecaItem(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BibliotecaItem holder, int position) {
            holder.bind(listaBiblioteca.get(position),key.get(position));
        }

        @Override
        public int getItemCount() {
            return listaBiblioteca.size();
        }
    }
}
