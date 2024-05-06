package org.taba.appfacturas;

import org.taba.appfacturas.modelo.Cliente;
import org.taba.appfacturas.modelo.Factura;
import org.taba.appfacturas.modelo.ItemFactura;
import org.taba.appfacturas.modelo.Producto;

import java.util.Scanner;

public class EjemploFactura {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setNif("12345678A");
        cliente.setNombre("Cristian");

        Scanner s = new Scanner(System.in);
        System.out.println("Introduce la descripción de la Factura: ");
        String desc = s.nextLine();
        Factura factura = new Factura(desc, cliente);
        Factura f = new Factura(desc, cliente);

        // Iterar para ingresar 5 productos
        Producto producto;
        String nombre;
        float precio;
        int cantidad;

        for (int i = 0; i < 5; i++) {
            // Crear el item
            producto = new Producto();
            System.out.print("Ingrese producto no " + producto.getCodigo() + ": ");
            nombre = s.next();
            producto.setNombre(nombre);

            System.out.print("Introduce el precio del producto: ");
            precio = s.nextFloat();
            producto.setPrecio(precio);

            System.out.print("Introduce la cantidad del producto: ");
            cantidad = s.nextInt();

            // Crear el Item
            ItemFactura item = new ItemFactura(cantidad, producto);
            factura.addItemFactura(item);

            System.out.println();
        }
        System.out.println(factura.generarDetalle());
    }
}
