package cr.ac.ucr.ecci.eseg.catbi.ui.Resultado;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Handler;

import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Material;
import cr.ac.ucr.ecci.eseg.catbi.R;

public class RecyclerViewMaterial_Config {
    private Context mContext;
    private MaterialAdapter mMaterialAdapter;

    public void setConfig(final RecyclerView recyclerView, final Context context, List<Material> materiales, List<String> keys, OnNoteListener onNoteListener, final String[] filtro, Activity activity){
        mContext = context;
        mMaterialAdapter = new MaterialAdapter(materiales, keys, onNoteListener);
        if(mMaterialAdapter.mListaMaterial.size() != 0){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext,"Mostrando resultados para: "+filtro[0]+".\n Buscando por: "+filtro[1]+ "\n En coleccion: "+filtro[2], Toast.LENGTH_LONG).show();
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(mMaterialAdapter);
                }
            });

        }else{
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext,"No se encontraron resultados.", Toast.LENGTH_LONG).show();
                }
            });

        }

    }


    class MaterialItemView extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitulo;
        private TextView mAutor;
        private TextView mFormato;
        private TextView mBiblioteca;

        private String key;

        OnNoteListener onNoteListener;

        public MaterialItemView(ViewGroup parent, OnNoteListener onNoteListener ){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.material_list_item, parent, false));

            mTitulo = (TextView) itemView.findViewById(R.id.titulo_textView);
            mAutor = (TextView) itemView.findViewById(R.id.autor_textView);
            mFormato = (TextView) itemView.findViewById(R.id.formato_textView);
            mBiblioteca = (TextView) itemView.findViewById(R.id.biblioteca_textView);

            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        public void bind(Material material, String key){
            mTitulo.setText(material.getTitulo());
            mAutor.setText(material.getAutor());
            mFormato.setText(material.getFormato());
            mBiblioteca.setText(material.getBiblioteca());
            this.key = key;
        }


        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    class MaterialAdapter extends RecyclerView.Adapter<MaterialItemView>{
        private List<Material> mListaMaterial;
        private List<String> mKeys;
        private OnNoteListener mOnNoteListener;

        private final int limit  = 10;

        public MaterialAdapter(List<Material> mListaMaterial, List<String> mKeys, OnNoteListener onNoteListener) {
            this.mListaMaterial = mListaMaterial;
            this.mKeys = mKeys;
            this.mOnNoteListener = onNoteListener;
        }

        @NonNull
        @Override
        public MaterialItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MaterialItemView(parent, mOnNoteListener);
        }

        @Override
        public void onBindViewHolder(@NonNull MaterialItemView holder, int position) {
            holder.bind(mListaMaterial.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mListaMaterial.size();
        }
    }
}
