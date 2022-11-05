/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.YearMonth;
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
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        for (int i = 0; i < listaClientesPorMes.size(); i++) {
            RegistroMensualClientes get = listaClientesPorMes.get(i);
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.retencionDeClientes();
            }
        }
        return 0;
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
}
