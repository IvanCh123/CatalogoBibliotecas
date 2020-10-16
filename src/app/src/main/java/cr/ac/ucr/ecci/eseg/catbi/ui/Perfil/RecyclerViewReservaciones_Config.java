package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.R;

public class RecyclerViewReservaciones_Config {
    private Context mContext;
    private RecyclerViewReservaciones_Config.ReservacionesAdapter mReservacionAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Reservacion> reservaciones, List<String> keys){
        mContext = context;
        mReservacionAdapter = new RecyclerViewReservaciones_Config.ReservacionesAdapter(reservaciones, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mReservacionAdapter);
    }
    class ReservacionItemView extends RecyclerView.ViewHolder{
        private TextView mTitulo;
        private TextView mDiasRestantes;

        private String key;

        public ReservacionItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.reservaciones_list_item, parent, false));

            mTitulo = (TextView) itemView.findViewById(R.id.titleTextView);
            mDiasRestantes = (TextView) itemView.findViewById(R.id.diasRestTextView);
        }

        public void bind(Reservacion reservacion, String key){
            mTitulo.setText(reservacion.getTituloMaterial());
            String fechaLimReserva = reservacion.getFechaLimite();
            int diasRestantes = getDiasRestantes(fechaLimReserva);
            mDiasRestantes.setText(Integer.toString(diasRestantes)+" días restantes");
            this.key = key;
        }

        public int getDiasRestantes(String fecha){
            int daysLeft = 0;
            String inputDateString = fecha;
            Calendar calCurr = Calendar.getInstance();
            Calendar day = Calendar.getInstance();
            try {
                day.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(inputDateString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(day.after(calCurr)){
                daysLeft = (day.get(Calendar.DAY_OF_MONTH) -(calCurr.get(Calendar.DAY_OF_MONTH)));
            }
            return daysLeft;
        }
    }

    class ReservacionesAdapter extends RecyclerView.Adapter<ReservacionItemView>{
        private List<Reservacion> mListaReservaciones;
        private List<String> mKeys;

        public ReservacionesAdapter(List<Reservacion> mListaReservaciones, List<String> mKeys) {
            this.mListaReservaciones = mListaReservaciones;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ReservacionItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ReservacionItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ReservacionItemView holder, int position) {
            holder.bind(mListaReservaciones.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mListaReservaciones.size();
        }
    }

}
