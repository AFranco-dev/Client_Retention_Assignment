/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jafra
 */
public class Vendedor extends Usuario {
    
    private int idVendedor;
    private static int cantVendedores = 0;
    private ArrayList<Venta> listaVentas;
    
    public Vendedor(String nombre, String username, String password) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.idVendedor = cantVendedores;
        cantVendedores++;
        listaVentas = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        String datosVendedor = String.format(
                "++++++++++++++++++++++++\nID vendedor: %d\nNombre: %s\nUsuario: %s\nPassword: %s\n-------------------\nVentas realizadas:\n",
                this.idVendedor, this.nombre, this.username, this.password);
        String ventasVendedor = Venta.getVentasString(this.listaVentas);
        String promVentas = String.format(
                "********************\nEl promedio de ventas mensuales es: %f\nEl promedio de ventas trimestrales es: %f\n",
                this.promVentasMensuales(), this.promVentasTrimestrales());
        return datosVendedor + ventasVendedor + promVentas;
    }
    
    public static void printVendedores(ArrayList<Vendedor> list) {
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println(next.toString());
        }
    }
    
    public void addVenta(ArrayList<Venta> listaVentas, ArrayList<Cliente> listaClientes, ArrayList<RegistroMensualClientes> listaMensual, int iD) {
        Venta venta = new Venta(listaClientes.get(iD), this);
        listaVentas.add(venta);
        this.listaVentas.add(venta);
        listaClientes.get(iD).addVenta(venta);
        if (listaMensual.isEmpty()) {
            listaMensual.add(new RegistroMensualClientes(venta));
            return;
        }
        YearMonth fechaVenta = YearMonth.from(venta.getFecha());
        for (int i = 0; i < listaMensual.size(); i++) {
            RegistroMensualClientes get = listaMensual.get(i);
            if (get.getMesDeCompra().equals(fechaVenta)) {
                get.addCliente(venta.getComprador());
                return;
            }
        }
        
        listaMensual.add(new RegistroMensualClientes(venta));
        listaMensual.get(listaMensual.size()-1).setClientesDelMesPasado(new ArrayList<>(listaMensual.get(listaMensual.size()-2).getClientesDelMes()));
    }
    
    public void addVenta(ArrayList<Venta> listaVentas, ArrayList<Cliente> listaClientes, ArrayList<RegistroMensualClientes> listaMensual, int iD, LocalDate date) {
        Venta venta = new Venta(listaClientes.get(iD), this, date);
        listaVentas.add(venta);
        this.listaVentas.add(venta);
        listaClientes.get(iD).addVenta(venta);
        if (listaMensual.isEmpty()) {
            listaMensual.add(new RegistroMensualClientes(venta));
            return;
        }
        YearMonth fechaVenta = YearMonth.from(venta.getFecha());
        for (int i = 0; i < listaMensual.size(); i++) {
            RegistroMensualClientes get = listaMensual.get(i);
            if (get.getMesDeCompra().equals(fechaVenta)) {
                get.addCliente(venta.getComprador());
                return;
            }
        }
        
        listaMensual.add(new RegistroMensualClientes(venta));
        listaMensual.get(listaMensual.size()-1).setClientesDelMesPasado(new ArrayList<>(listaMensual.get(listaMensual.size()-2).getClientesDelMes()));
    }
    
    public double promVentasMensuales() {
        if (this.listaVentas.isEmpty()) {
            return 0;
        }
        YearMonth primerVenta = YearMonth.from(this.listaVentas.get(0).getFecha());
        YearMonth ultimaVenta = YearMonth.from(this.listaVentas.get(this.listaVentas.size() - 1).getFecha());
        double cantMeses = Double.valueOf(ChronoUnit.MONTHS.between(primerVenta, ultimaVenta) + 1);
        double cantVentas = listaVentas.size();
        return cantVentas/cantMeses;
    }
    
    public double promVentasTrimestrales() {
        return promVentasMensuales() * 3;
    }
}
