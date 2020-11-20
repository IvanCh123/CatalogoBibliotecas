package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

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
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Usuario;
import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.ui.Bibliotecas.BibliotecaUbicacion;
import cr.ac.ucr.ecci.eseg.catbi.ui.Bibliotecas.RecycleViewBibliotecaConfig;

public class RecycleViewUserConfig {
    private Context contexto;
    private userAdapter userAdap;

    public void setConfig(final RecyclerView recyclerView, final Context context, final List<Usuario> listaUser, final List<String>keys, Activity activity){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                contexto = context;
                userAdap=new RecycleViewUserConfig.userAdapter(listaUser,keys);
                recyclerView.setLayoutManager(new LinearLayoutManager(contexto));
                recyclerView.setAdapter(userAdap);
            }
        });

    }


    class UserItem extends RecyclerView.ViewHolder {
        private TextView nombre_user_textView;
        private TextView email_user_textView;


        private String keys;

        public UserItem(ViewGroup parent){
            super(LayoutInflater.from(contexto).inflate(R.layout.nombre_list_item,parent,false));

            nombre_user_textView=(TextView) itemView.findViewById(R.id.nombre_user_textView);
            email_user_textView=(TextView) itemView.findViewById(R.id.email_user_textView);

        }

        public void bind(Usuario User, String key){
            nombre_user_textView.setText(User.getNombre());
            email_user_textView.setText(User.getCorreo());
            this.keys=key;
        }
    }

    class userAdapter extends RecyclerView.Adapter<RecycleViewUserConfig.UserItem> {
        private List<Usuario> listaUsuario;
        private List<String> key;

        public userAdapter(List<Usuario> listaUser, List<String> key) {
            this.listaUsuario = listaUser;
            this.key = key;

        }

        @NonNull
        @Override
        public RecycleViewUserConfig.UserItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecycleViewUserConfig.UserItem(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecycleViewUserConfig.UserItem holder, final int position) {
            holder.bind(listaUsuario.get(position), key.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = listaUsuario.get(position).getNombre();
                    //Intent intent1 = new Intent(contexto,BibliotecaUbicacion.class);//BibliotecaUbicacion.class
                    //intent1.putExtra("name", name);
//                    contexto.startActivity(intent1);
                    Toast.makeText(v.getContext(), "Nombre Seleccionado: "+name, Toast.LENGTH_LONG).show();
                    if (!hayConexionAInternet()) {
                        Toast.makeText(v.getContext(), "El dispositivo no tiene internet las funcionalidades del mapa son limitadas", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return listaUsuario.size();
        }

        public boolean hayConexionAInternet() {
            ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            return isConnected;
        }
    }
}
