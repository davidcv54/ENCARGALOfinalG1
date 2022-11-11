package mx.com.encargalo.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import mx.com.encargalo.Entidades.Productos;
import mx.com.encargalo.R;

public class AdapterProductos extends RecyclerView.Adapter<AdapterProductos.ViewHolder>{

    ArrayList<Productos> model;
    LayoutInflater inflater;
    public AdapterProductos(Context context, ArrayList<Productos> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //Quitar cuando la consulta sea desde HTTPS
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View vista = inflater.inflate(R.layout.mio_dpitemlistaproducto, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterProductos.ViewHolder holder, int position) {
        String productoURL = "http://129.151.103.228/Encargalo/APIS/TenderoApp/"+model.get(position).getProductoImagen();
        Bitmap productoImg = obtenerImagenDeURL(productoURL);
        String productoDescripcion = model.get(position).getProductoDescripcion();
        Integer productoCantidad = model.get(position).getProductoCantidad();
        holder.productoDescripcion.setText(productoDescripcion);
        holder.productoCantidad.setText(String.valueOf(productoCantidad));
        holder.productoImagen.setImageBitmap(productoImg);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView productoImagen;
        TextView productoDescripcion, productoCantidad;
        /*
        private Integer ;
        private Float productoSubTotal;
        */
        public ViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);
            productoDescripcion = itemView.findViewById(R.id.mio_varstrDetalleProducto2);
            productoCantidad = itemView.findViewById(R.id.mio_varstrDetalleProducto3);
            productoImagen = itemView.findViewById(R.id.mio_icondetalleproducto);
        }
    }
    //función que retorna la imagen desde el url enviando el parametro de la url
    public static Bitmap obtenerImagenDeURL(String urlImagen) {
        try {
            URL url = new URL(urlImagen);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
