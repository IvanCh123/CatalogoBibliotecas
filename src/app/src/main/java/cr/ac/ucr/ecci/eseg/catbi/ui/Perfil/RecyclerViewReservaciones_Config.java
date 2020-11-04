package cr.ac.ucr.ecci.eseg.catbi.ui.Perfil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import cr.ac.ucr.ecci.eseg.catbi.R;
import cr.ac.ucr.ecci.eseg.catbi.DataBaseRoom.Reservacion;

public class RecyclerViewReservaciones_Config {
    private Context mContext;
    private RecyclerViewReservaciones_Config.ReservacionesAdapter mReservacionAdapter;

    public void setConfig(final RecyclerView recyclerView, final Context context, final List<Reservacion> reservaciones, final List<String> keys, Activity activity){
        mContext = context;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mReservacionAdapter = new RecyclerViewReservaciones_Config.ReservacionesAdapter(reservaciones, keys);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(mReservacionAdapter);

            }
        });

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

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Reservacion reservacion, String key){
            mTitulo.setText(reservacion.getTituloMaterial());
            String fechaLimReserva = reservacion.getFechaLimite();
            int diasRestantes = getDiasRestantes(fechaLimReserva);
            if(diasRestantes>=0) {
                mDiasRestantes.setText(Integer.toString(diasRestantes) + " días restantes");
            }else{
                mDiasRestantes.setText(Integer.toString(Math.abs(diasRestantes)) + " días de retraso");
                mDiasRestantes.setTextColor(Color.parseColor("#FF0000"));
            }
            this.key = key;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public int getDiasRestantes(String fecha){
            LocalDate fechaActual = LocalDate.now();
            fechaActual.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate fechaLimite = LocalDate.parse(fecha,DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            long noOfDaysBetween = ChronoUnit.DAYS.between(fechaActual, fechaLimite);

            return (int) noOfDaysBetween;
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

        @RequiresApi(api = Build.VERSION_CODES.O)
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
