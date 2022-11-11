package mx.com.encargalo.Entidades;

public class Orden {

    private int idOrden;
    private String odFechaPedido;
    private String perNombreCompleto;
    private String odEstado;

    public Orden () {};
    public Orden(int idOrden, String odFechaPedido, String perNombreCompleto, String odEstado) {
        this.idOrden = idOrden;
        this.odFechaPedido = odFechaPedido;
        this.perNombreCompleto = perNombreCompleto;
        this.odEstado = odEstado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getOdFechaPedido() {

        return odFechaPedido;
    }

    public void setOdFechaPedido(String odFechaPedido) {
        this.odFechaPedido = odFechaPedido;
    }

    public String getPerNombreCompleto() {
        return perNombreCompleto;
    }

    public void setPerNombreCompleto(String perNombreCompleto) {
        this.perNombreCompleto = perNombreCompleto;
    }

    public String getOdEstado() {
        return odEstado;
    }

    public void setOdEstado(String odEstado) {
        this.odEstado = odEstado;
    }
}
