package mx.com.encargalo.tendero.Inicio_sesion.ui.Mis_ordenes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import mx.com.encargalo.Adaptadores.AdapterOrden;
import mx.com.encargalo.Entidades.Orden;
import mx.com.encargalo.R;


public class mio_frgmisordenesprincipal extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    RecyclerView mio_moprclvlistaproductos;
    RequestQueue requestQueue;
    ArrayList<Orden> ArrayOrden;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_mio_frgmisordenesprincipal, container, false);
        mio_moprclvlistaproductos = vista.findViewById(R.id.mio_moprclvlistaproductos);
        ArrayOrden = new ArrayList<>();
        mio_moprclvlistaproductos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mio_moprclvlistaproductos.setHasFixedSize(true);
        requestQueue = Volley.newRequestQueue(getContext());
        webServicesOrdenes();
        return vista;
    }

    private void webServicesOrdenes() {
        String URL = "http://129.151.103.228/Encargalo/APIS/TenderoApp/c_lista_ordenes_mis_ordenes.php?sp_idTienda=4";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,URL, null, this, this);
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se pudo conectar"+ error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Orden orden = null;
        JSONArray json = response.optJSONArray("sp_c_lista_ordenes");
        try {
            for (int i = 0; i < json.length(); i++){
                orden = new Orden();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                orden.setIdOrden(jsonObject.optInt("idOrden"));
                orden.setOdFechaPedido(jsonObject.optString("odFechaPedido"));
                orden.setPerNombreCompleto(jsonObject.optString("perNombreCompleto"));
                orden.setOdEstado(jsonObject.optString("odEstado"));
                ArrayOrden.add(orden);
            }


            AdapterOrden adapter = new AdapterOrden(getContext(), ArrayOrden);


            mio_moprclvlistaproductos.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}