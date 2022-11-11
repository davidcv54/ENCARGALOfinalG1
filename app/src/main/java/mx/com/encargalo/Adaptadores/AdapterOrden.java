package mx.com.encargalo.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import mx.com.encargalo.Entidades.Orden;
import mx.com.encargalo.R;

public class AdapterOrden extends RecyclerView.Adapter<AdapterOrden.ViewHolder>{

    LayoutInflater inflater;
    ArrayList<Orden> listaOrdenes;

    public AdapterOrden(Context context, ArrayList<Orden> listaOrdenes){
        this.inflater = LayoutInflater.from(context);
        this.listaOrdenes = listaOrdenes;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View vista = inflater.inflate(R.layout.mio_menuitemlistaordenes, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterOrden.ViewHolder holder, int position) {
        int idOrden = listaOrdenes.get(position).getIdOrden();
        String odFechaPedido = listaOrdenes.get(position).getOdFechaPedido();
        String perNombreCompleto = listaOrdenes.get(position).getPerNombreCompleto();
        String odEstado = listaOrdenes.get(position).getOdEstado();
        holder.idOrden.setText(String.valueOf(idOrden));
        holder.odFechaPedido.setText(odFechaPedido);
        holder.perNombreCompleto.setText(perNombreCompleto);
        holder.odEstado.setText(odEstado);
    }

    @Override
    public int getItemCount() {
        return listaOrdenes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idOrden, odFechaPedido, perNombreCompleto, odEstado, txt_Detalle;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            idOrden = itemView.findViewById(R.id.txt_IdOrden);
            odFechaPedido = itemView.findViewById(R.id.txt_OrdCreada);
            perNombreCompleto = itemView.findViewById(R.id.txt_Nombres);
            odEstado = itemView.findViewById(R.id.txt_OrdEstado);
            txt_Detalle = itemView.findViewById(R.id.mio_moptxtdetalle);
            txt_Detalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.nav_misordenesdetallepedido);
                }
            });
        }
    }


}
