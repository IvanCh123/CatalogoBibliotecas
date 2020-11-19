package cr.ac.ucr.ecci.eseg.catbi.ui.Administrar;

import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import cr.ac.ucr.ecci.eseg.catbi.R;

public class AdapterReservaItem extends ArrayAdapter<ReservaFila> {
    private LayoutInflater layoutInflater;

    public AdapterReservaItem(Context context, List<ReservaFila> objects)
    {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // holder pattern
        Holder holder = null;
        if (convertView == null)
        {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.reservacion_eliminar_list_element, null);
            holder.setTextViewTitle((TextView) convertView.findViewById(R.id.libro_reserva));
            holder.setTextViewSubtitle((TextView) convertView.findViewById(R.id.dias_rest_reserv_view));
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }

        ReservaFila row = getItem(position);
        holder.getTextViewTitle().setText(row.getReservacion().getTituloMaterial());
        holder.getTextViewSubtitle().setText(row.getReservacion().getFechaLimite());
        return convertView;
    }

    static class Holder
    {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox checkBox;

        public TextView getTextViewTitle()
        {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle)
        {
            this.textViewTitle = textViewTitle;
        }

        public TextView getTextViewSubtitle()
        {
            return textViewSubtitle;
        }

        public void setTextViewSubtitle(TextView textViewSubtitle)
        {
            this.textViewSubtitle = textViewSubtitle;
        }
        public CheckBox getCheckBox()
        {
            return checkBox;
        }
        public void setCheckBox(CheckBox checkBox)
        {
            this.checkBox = checkBox;
        }

    }
}
