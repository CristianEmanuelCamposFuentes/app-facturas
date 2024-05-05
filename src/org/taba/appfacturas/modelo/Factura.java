package org.taba.appfacturas.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Factura {
    private int folio;
    private String descripcion;
    private Date fecha;
    private Cliente cliente;
    private ItemFactura[] items;
    private int indiceItems;
    public static final int MAX_ITEMS = 12;
    private static int ultimoFolio;

    public Factura(String descripcion, Cliente cliente) {
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.items = new ItemFactura[MAX_ITEMS];
        this.folio = ++ultimoFolio;
        this.fecha = new Date();
    }

    public int getFolio() {
        return folio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ItemFactura[] getItems() {
        return items;
    }

    public void addItemFactura(ItemFactura item) {
        if(indiceItems < MAX_ITEMS) {
        this.items[indiceItems++] = item;
        }
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float calcularTotal() {
        float total = 0.0f;
        for(ItemFactura item: items) {
            // Validar que no sea null
            if(!(item instanceof ItemFactura) ) continue;
            total += item.calcularImporte();
        }
        return total;
    }

    public String generarDetalle() {
        StringBuilder sb = new StringBuilder("Factura Nro: " + this.folio + "\n")
                .append("Cliente: " + this.cliente.getNombre() + "\n")
                .append("NIF: " + this.cliente.getNif() + "\n")
                .append("Descripción: " + this.getDescripcion() + "\n");

        // Formatear la fecha
        SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM, yyyy");

        sb.append("Fecha: " + df.format(this.fecha) + "\n");

        for(ItemFactura item: this.items) {
            if(item == null) continue;
            sb.append(item.getCantidad() + "\n");
            sb.append(item.getProducto().getNombre() + "\n");
            sb.append(item.calcularImporte() + "\n");
            sb.append("\n");

        }
        return sb.toString();
    }
}
