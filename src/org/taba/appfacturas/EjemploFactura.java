package org.taba.appfacturas;

import org.taba.appfacturas.modelo.*;

import java.util.Scanner;

public class EjemploFactura {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setNif("12345678A");
        cliente.setNombre("Cristian");

        Scanner s = new Scanner(System.in);
        System.out.print("Introduce la descripción de la Factura: ");
        String desc = s.nextLine();
        Factura factura = new Factura(desc, cliente);
        Factura f = new Factura(desc, cliente);

        // Iterar para ingresar 5 productos
        Producto producto;
        for (int i = 0; i < 5; i++) {
            // Crear el item
            producto = new Producto();
            System.out.print("Ingrese producto no " + producto.getCodigo() + ": ");
            producto.setNombre(s.nextLine());

            System.out.print("Introduce el precio del producto: ");
            producto.setPrecio(s.nextFloat());

            System.out.print("Introduce la cantidad del producto: ");
            factura.addItemFactura(new ItemFactura(s.nextInt(), producto));

            System.out.println();
            s.nextLine();
        }
        System.out.println(factura);
    }
}
