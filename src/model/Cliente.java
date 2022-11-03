/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jafra
 */
public class Cliente extends Datos {

    private int idCliente;
    private static int cantClientes = 0;
    private ArrayList<Venta> listaCompras;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.idCliente = cantClientes;
        cantClientes++;
        this.listaCompras = new ArrayList<>();
    }

    public void addVenta(Venta nVenta) {
        this.getListaCompras().add(nVenta);
    }

    @Override
    public String toString() {
        String datosCliente = String.format("########################\nID cliente: %d\nNombre: %s\n",
                this.idCliente, this.nombre);
        String comprasCliente = Venta.getVentasString(this.listaCompras);
        return datosCliente + comprasCliente;
    }

    public static void printClientes(ArrayList<Cliente> list) {
        if (list.isEmpty()) {
            System.out.println("No hay clientes.");
        }
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
            System.out.println(get.toString());
        }
    }

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
