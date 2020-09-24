package cr.ac.ucr.ecci.eseg.catbi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.ui.Resultado.Material;

public class RecyclerViewMaterial_Config {
    private Context mContext;
    private MaterialAdapter mMaterialAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Material> materiales, List<String> keys, OnNoteListener onNoteListener){
        mContext = context;
        mMaterialAdapter = new MaterialAdapter(materiales, keys, onNoteListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mMaterialAdapter);
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
