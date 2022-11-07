/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jafra
 */
public class Venta {

    protected int idVenta;
    protected static int cantVentas = 0;
    protected Cliente comprador;
    protected Vendedor vendedor;
    protected LocalDate fecha;
    //constructor que utiliza la fecha actual
    public Venta(Cliente comprador, Vendedor vendedor) {
        this.idVenta = cantVentas;
        cantVentas++;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.fecha = LocalDate.now();
    }
    //constructor que utiliza una fecha dada
    public Venta(Cliente comprador, Vendedor vendedor, LocalDate date) {
        this.idVenta = cantVentas;
        cantVentas++;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.fecha = date;
    }
    //metodo para convertir a String la instancciua de venta
    @Override
    public String toString() {
        return String.format(
                "-----------------------\n"
                + "ID Venta: %d\nVendedor: %s\nComprador: %s\nFecha: %s",
                this.idVenta, this.vendedor.getNombre(), this.comprador.getNombre(), this.fecha.toString());
    }
    //Imprimir lasd ventasd de la listas de ventas
    public static void printVentas(ArrayList<Venta> list) {
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println(next.toString());
        }
    }
    //Regresa el String de la lsita de ventas
    public static String getVentasString(ArrayList<Venta> list) {
        if (list.isEmpty()) {
            return "No hay ventas/compras";
        }
        String ventas = "";
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            ventas += next.toString() + "\n";
        }
        return ventas;
    }

    /**
     * @return the idVenta
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the cantVentas
     */
    public static int getCantVentas() {
        return cantVentas;
    }

    /**
     * @param aCantVentas the cantVentas to set
     */
    public static void setCantVentas(int aCantVentas) {
        cantVentas = aCantVentas;
    }

    /**
     * @return the comprador
     */
    public Cliente getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
