package cr.ac.ucr.ecci.eseg.catbi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Biblioteca;

public class RecycleViewBibliotecaConfig {
    private Context contexto;
    private  bibliotecaAdapter biblioAdapter;

    public void setConfig(final RecyclerView recyclerView, final Context context, final List<Biblioteca> listaBiblio, final List<String>keys, Activity activity){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                contexto = context;
                biblioAdapter=new bibliotecaAdapter(listaBiblio,keys);
                recyclerView.setLayoutManager(new LinearLayoutManager(contexto));
                recyclerView.setAdapter(biblioAdapter);
            }
        });

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

        public void bind(Biblioteca bibliotecas, String key){
            biblioteca_horario.setText(bibliotecas.getHorario());
            biblioteca_nombre.setText(bibliotecas.getNombre());
            biblioteca_telefono.setText(bibliotecas.getTelefono());

            this.keys=key;
        }
    }

    class bibliotecaAdapter extends RecyclerView.Adapter<BibliotecaItem>{
        private List<Biblioteca> listaBiblioteca;
        private List<String> key;

        public bibliotecaAdapter(List<Biblioteca> listaBiblioteca, List<String> key) {
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
                    double L1=listaBiblioteca.get(position).getLatitud();
                    double L2=listaBiblioteca.get(position).getLongitud();
                    String name=listaBiblioteca.get(position).getNombre();
                    Intent intent1 = new Intent(contexto,
                            BibliotecaUbicacion.class);//BibliotecaUbicacion.class
                    intent1.putExtra("name", name);
                    intent1.putExtra("L1", L1);
                    intent1.putExtra("L2", L2);
                    contexto.startActivity(intent1);

                    if(!hayConexionAInternet()){
                        Toast.makeText(v.getContext(),"El dispositivo no tiene internet las funcionalidades del mapa son limitadas",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            Log.d("T4","4");
            return listaBiblioteca.size();
        }

        public boolean hayConexionAInternet(){
            ConnectivityManager cm = (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            return isConnected;
        }
    }
}
