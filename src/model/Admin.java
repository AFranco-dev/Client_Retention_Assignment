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

/**
 *
 * @author jafra
 */
public class Admin extends Usuario {

    private int idAdministrador;
    private static int cantAdministradores = 0;

    public Admin(String nombre, String username, String password) {
        this.idAdministrador = cantAdministradores;
        this.cantAdministradores++;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
    }

    public double retencionMensualUltima(ArrayList<RegistroMensualClientes> listaClientesPorMes) {
        if (listaClientesPorMes.isEmpty()) {
            return 0;
        }
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < listaClientesPorMes.size(); i++) {
            RegistroMensualClientes get = listaClientesPorMes.get(i);
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.retencionDeClientes();
            }
        }
        return 0;
    }
    
    public double retencionTrimestralUltima(ArrayList<Cliente> list) {
        if (list.isEmpty()) {
            return 0;
        }
        if (clientesInicioTrimestre(list) == 0) {
            return 0;
        }
        return (clientesFinTrimestre(list)-clientesNuevosTrimestre(list))/clientesInicioTrimestre(list);
    }
    
    public double clientesInicioTrimestre(ArrayList<Cliente> list){
        double clientes = 0;
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
            if (get.comproPenultimoTrimestre()) {
                clientes++;
            }
        }
        return clientes;
    }
    
    public double clientesFinTrimestre(ArrayList<Cliente> list){
        double clientes = 0;
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
            if (get.comproUltimoTrimestre()) {
                clientes++;
            }
        }
        return clientes;
    }
    
    public double clientesNuevosTrimestre(ArrayList<Cliente> list){
        double clientes = 0;
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
            if (!get.comproPenultimoTrimestre() && get.comproUltimoTrimestre()) {
                clientes++;
            }
        }
        return clientes;
    }
    
    public double clientesRetenidosTrimestre(ArrayList<Cliente> list){
        return clientesFinTrimestre(list)-clientesNuevosTrimestre(list);
    }
    
    public double retencionMensualPromedio(ArrayList<RegistroMensualClientes> list) {
        if (list.isEmpty()) {
            return 0;
        }
        if (list.size()==1) {
            return list.get(0).retencionDeClientes();
        }
        double suma =0;
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
            suma+=get.retencionDeClientes();
        }
        return suma/list.size();
    }
    
    public int clientesInicioPeriodo (ArrayList<RegistroMensualClientes> list) {
        if (list.isEmpty()) {
            return 0;
        }
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.getClientesDelMesPasado().size();
            }
        }
        return 0;
    }
    
    public int clientesNuevos (ArrayList<RegistroMensualClientes> list) {
        if (list.isEmpty()) {
            return 0;
        }
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.clientesNuevos();
            }
        }
        return 0;
    }
    
    public int clientesFinPeriodo (ArrayList<RegistroMensualClientes> list) {
        if (list.isEmpty()) {
            return 0;
        }
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.getClientesDelMes().size();
            }
        }
        return 0;
    }
    
    public int clientesRetenidosPeriodo (ArrayList<RegistroMensualClientes> list) {
        if (list.isEmpty()) {
            return 0;
        }
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.getClientesDelMes().size()-get.clientesNuevos();
            }
        }
        return 0;
    }
    
    public double ventasMensualesPromedio (ArrayList<Venta> list) {
        if (list.isEmpty()) {
            return 0;
        }
        YearMonth primerVenta = YearMonth.from(list.get(0).getFecha());
        YearMonth ultimaVenta = YearMonth.from(list.get(list.size() - 1).getFecha());
        double cantMeses = Double.valueOf(ChronoUnit.MONTHS.between(primerVenta, ultimaVenta) + 1);
        double cantVentas = list.size();
        return cantVentas / cantMeses;
    }
    
    public void addVendedor (ArrayList<Vendedor> list, String nombre, String usuario, String contra){
        Vendedor v = new Vendedor(nombre, usuario, contra);
        list.add(v);
    }
}
