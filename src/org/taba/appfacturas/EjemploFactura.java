package org.taba.appfacturas;

import org.taba.appfacturas.modelo.Cliente;
import org.taba.appfacturas.modelo.Factura;
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
            System.out.println("Ingrese producto no " + producto.getCodigo() + ": ");
            System.out.println("Introduce el nombre del producto: ");
            nombre = s.nextLine();
            System.out.println("Introduce el precio del producto: ");
            precio = Float.parseFloat(s.nextLine());
            System.out.println("Introduce la cantidad del producto: ");
            cantidad = Integer.parseInt(s.nextLine());
            // Crear el producto
            producto = new Producto();
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            // Crear el Item
        }



    }
}
