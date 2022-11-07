/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 *
 * @author jafra
 */
public class Cliente extends Datos {
    //atributos unicos del cliente
    private int idCliente;
    private static int cantClientes = 0;
    private ArrayList<Venta> listaCompras;
    //constructor del objeto cliente
    public Cliente(String nombre) {
        this.nombre = nombre;
        this.idCliente = cantClientes;
        cantClientes++;
        this.listaCompras = new ArrayList<>();
    }
    //metodo para agregar venta al registro de compras del cliente
    public void addVenta(Venta nVenta) {
        this.getListaCompras().add(nVenta);
    }
    //meotodo para convertir a String el objeto Cliente
    @Override
    public String toString() {
        String datosCliente = String.format("########################\nID cliente: %d\nNombre: %s\n",
                this.idCliente, this.nombre);
        String comprasCliente = Venta.getVentasString(this.listaCompras);
        return datosCliente + comprasCliente;
    }
    //metodo para imprimir todos los clientes en consola
    public static void printClientes(ArrayList<Cliente> list) {
        if (list.isEmpty()) {
            System.out.println("No hay clientes.");
        }
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
            System.out.println(get.toString());
        }
    }
    //metodo para obtener toda la String de todos los clientes
    public static String getClientesString(ArrayList<Cliente> list) {
        if (list.isEmpty()) {
            return "No hay clientes.";
        }
        String clientes = "_-_-_-_-_-_-\n";
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
            clientes += get.toString() + "\n";
        }
        return clientes;
    }
    //metodo para verifiacar si el cliente compro en el ultimo mes
    public boolean comproUltimoMes() {
        YearMonth fechaActual = YearMonth.now();
        for (int i = 0; i < listaCompras.size(); i++) {
            Venta get = listaCompras.get(i);
            YearMonth fechaCompra = YearMonth.from(get.getFecha());
//            Se chequean todas ls compras y se verifica si alguna de estas fue 
//                    realizada en el ultimo mes, en cso que sí, se regresa true
            if (fechaActual.equals(fechaCompra)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean comproUltimoTrimestre() {
        YearMonth fechaActual = YearMonth.now();
        for (int i = 0; i < listaCompras.size(); i++) {
            Venta get = listaCompras.get(i);
            YearMonth fechaCompra = YearMonth.from(get.getFecha());
            //            Se chequean todas ls compras y se verifica si alguna de estas fue 
//                    realizada en el ultimo mtrimestre, en cso que sí, se regresa true
            if (fechaActual.equals(fechaCompra) || fechaActual.minusMonths(1).equals(fechaCompra) || fechaActual.minusMonths(2).equals(fechaCompra)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean comproPenultimoTrimestre() {
        YearMonth fechaActual = YearMonth.now();
        for (int i = 0; i < listaCompras.size(); i++) {
            Venta get = listaCompras.get(i);
            YearMonth fechaCompra = YearMonth.from(get.getFecha());
            //            Se chequean todas ls compras y se verifica si alguna de estas fue 
//                    realizada en el penultimo mtrimestre, en cso que sí, se regresa true
            if (fechaActual.minusMonths(3).equals(fechaCompra) || fechaActual.minusMonths(4).equals(fechaCompra) || fechaActual.minusMonths(5).equals(fechaCompra)) {
                return true;
            }
        }
        return false;
    }

    //getters y setters
    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the cantClientes
     */
    public static int getCantClientes() {
        return cantClientes;
    }

    /**
     * @param aCantClientes the cantClientes to set
     */
    public static void setCantClientes(int aCantClientes) {
        cantClientes = aCantClientes;
    }

    /**
     * @return the listaCompras
     */
    public ArrayList<Venta> getListaCompras() {
        return listaCompras;
    }

    /**
     * @param listaCompras the listaCompras to set
     */
    public void setListaCompras(ArrayList<Venta> listaCompras) {
        this.listaCompras = listaCompras;
    }
}
