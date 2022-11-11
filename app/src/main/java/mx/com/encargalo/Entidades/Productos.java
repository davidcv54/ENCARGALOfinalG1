package mx.com.encargalo.Entidades;

public class Productos {

    private String productoImagen;
    private String productoDescripcion;
    private Float productoPrecioVenta;
    private Integer productoCantidad;
    private Float productoSubTotal;

    public Productos(){}

    public Productos(String productoImagen, String productoDescripcion, Float productoPrecioVenta, Integer productoCantidad, Float productoSubTotal) {
        this.productoImagen = productoImagen;
        this.productoDescripcion = productoDescripcion;
        this.productoPrecioVenta = productoPrecioVenta;
        this.productoCantidad = productoCantidad;
        this.productoSubTotal = productoSubTotal;
    }

    public String getProductoImagen() {
        return productoImagen;
    }

    public void setProductoImagen(String productoImagen) {
        this.productoImagen = productoImagen;
    }

    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    public void setProductoDescripcion(String productoDescripcion) {
        this.productoDescripcion = productoDescripcion;
    }

    public Float getProductoPrecioVenta() {
        return productoPrecioVenta;
    }

    public void setProductoPrecioVenta(Float productoPrecioVenta) {
        this.productoPrecioVenta = productoPrecioVenta;
    }

    public Integer getProductoCantidad() {
        return productoCantidad;
    }

    public void setProductoCantidad(Integer productoCantidad) {
        this.productoCantidad = productoCantidad;
    }

    public Float getProductoSubTotal() {
        return productoSubTotal;
    }

    public void setProductoSubTotal(Float productoSubTotal) {
        this.productoSubTotal = productoSubTotal;
    }
}
