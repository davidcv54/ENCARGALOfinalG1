package mx.com.encargalo.tendero.Inicio_sesion.ui.Mis_ordenes;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.com.encargalo.Adaptadores.AdapterProductos;
import mx.com.encargalo.Entidades.Productos;
import mx.com.encargalo.R;

public class mio_frgdetalleproducto extends Fragment {

    Button mio_dpbtncancelarorden,mio_dpbtnenviarorden;
    Dialog mDialog;
    TextView mio_dptxtcontadorproductos;
    Dialog dialog;
    RequestQueue requestQueue;
    RecyclerView rclvlistaproductos;
    View confirmacion, cancelacion;
    ArrayList<Productos> arrayProductos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_mio_frgdetalleproducto, container, false);

        mio_dpbtncancelarorden=vista.findViewById(R.id.mio_dpbtncancelarorden);
        //mio_dpbtnenviarorden=vista.findViewById(R.id.mio_dpbtnenviarorden);
        mio_dptxtcontadorproductos = vista.findViewById(R.id.mio_dptxtcontadorproductos);
        arrayProductos = new ArrayList<>();
        rclvlistaproductos = vista.findViewById(R.id.mio_rclvlistaproductos);
        rclvlistaproductos.setLayoutManager(new LinearLayoutManager(this.getContext()));

        rclvlistaproductos.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(getContext());
        webServicesProductos();
        /*mio_dpbtncancelarorden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelacion=v;
                elegirAccion();
            }
        });
        mio_dpbtnenviarorden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacion=v;
                enviarorden();
            }
        });
        */
        return vista;
    }

    private void webServicesProductos() {
        String url = "http://129.151.103.228/Encargalo/APIS/TenderoApp/c_detalle_orden_x_id_mis_ordenes.php?id_orden=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Productos producto = null;
                        JSONArray json = response.optJSONArray("consulta");
                        try {
                            for (int i = 0; i < json.length(); i++){
                                producto = new Productos();
                                JSONObject jsonObject = null;
                                jsonObject = json.getJSONObject(i);
                                producto.setProductoDescripcion(jsonObject.optString("proDescripcion"));
                                producto.setProductoImagen(jsonObject.optString("lptImagen1"));
                                producto.setProductoCantidad(jsonObject.optInt("doCantidad"));
                                producto.setProductoCantidad(jsonObject.optInt("doCantidad"));

                                arrayProductos.add(producto);
                            }


                            AdapterProductos adapter = new AdapterProductos(getContext(), arrayProductos);
                            rclvlistaproductos.setAdapter(adapter);
                            mio_dptxtcontadorproductos.setText(" "+String.valueOf(arrayProductos.size()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se pudo conectar"+ error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private void enviarorden() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.mio_lytenviarorden);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        Button ecancelar = dialog.findViewById(R.id.mio_eobtncancelar);
        ecancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button eenviar = dialog.findViewById(R.id.mio_eobtnaceptar);
        eenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(confirmacion).navigate(R.id.nav_misordenesconfirmacion);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void elegirAccion() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.mio_lytcancelarorden);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        final Button cancelar = dialog.findViewById(R.id.mio_cobtncancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button aceptar = dialog.findViewById(R.id.mio_cobtnaceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(cancelacion).navigate(R.id.nav_misordenesdetallepedido);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}