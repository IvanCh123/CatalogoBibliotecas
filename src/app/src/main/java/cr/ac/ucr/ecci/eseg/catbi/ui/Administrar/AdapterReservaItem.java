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
import android.widget.Toast;

import cr.ac.ucr.ecci.eseg.catbi.R;

public class AdapterReservaItem extends ArrayAdapter<ReservaFila> /*implements View.OnClickListener*/{
    private LayoutInflater layoutInflater;

    public AdapterReservaItem(Context context, List<ReservaFila> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.reservacion_eliminar_list_element, null);
            holder.setTextViewMaterial((TextView) convertView.findViewById(R.id.libro_reserva));
            holder.setTextViewFecha((TextView) convertView.findViewById(R.id.dias_rest_reserv_view));
            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }

        ReservaFila row = getItem(position);
        holder.getTextViewMaterial().setText(row.getReservacion().getTituloMaterial());
        holder.getTextViewFecha().setText(row.getReservacion().getFechaLimite());
/*
        holder.getCheckBox().setTag(position);
        holder.getCheckBox().setChecked(row.isCheck());
        holder.getCheckBox().setOnClickListener(this);*/

        return convertView;
    }

    /*@Override
    public void onClick(View v) {

        CheckBox checkBox = (CheckBox) v;
        int position = (Integer) v.getTag();
        getItem(position).setCheck(checkBox.isChecked());
        Toast.makeText(getContext(), "Se a seleccionado un item", Toast.LENGTH_SHORT).show();
    }*/

    static class Holder {
        TextView textViewMaterial;
        TextView textViewFecha;
        CheckBox checkBox;

        public TextView getTextViewMaterial()
        {
            return textViewMaterial;
        }

        public void setTextViewMaterial(TextView textViewMaterial)
        {
            this.textViewMaterial = textViewMaterial;
        }

        public TextView getTextViewFecha()
        {
            return textViewFecha;
        }

        public void setTextViewFecha(TextView textViewFecha)
        {
            this.textViewFecha = textViewFecha;
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
