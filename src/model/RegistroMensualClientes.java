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
public class RegistroMensualClientes {

    private ArrayList<Cliente> clientesDelMes;
    private ArrayList<Cliente> clientesDelMesPasado;
    private YearMonth mesDeCompra;

    @Override public String toString(){
        String periodo = String.format(
                "$$$$$$$$$$$$$$$$$$$$$$\nPeriodo de la compra de los clientes: %s\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n", this.mesDeCompra);
        String clientesDelPeriodo = "Clientes del periodo actual: \n" + Cliente.getClientesString(clientesDelMes);
        String clientesPeriodoAnterior = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\nClientes del periodo anterior: \n" + Cliente.getClientesString(clientesDelMesPasado);
        return periodo + clientesDelPeriodo + clientesPeriodoAnterior;
    }
    
    public static void printClientesPeriodo(ArrayList<RegistroMensualClientes> list){
        if (list.isEmpty()) {
            System.out.println("No hay registros.");
        }
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
            System.out.println(get.toString());
        }
    }
    
    public double clientesNuevos() {
        double cNuevos = 0;
        if (clientesDelMes.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < clientesDelMes.size(); i++) {
            Cliente get = clientesDelMes.get(i);
            if (!(clientesDelMesPasado.contains(get))) {
                cNuevos++;
            }
        }
        return cNuevos;
    }

    public double retencionDeClientes() {
        if (clientesDelMes.isEmpty()) {
            return 0;
        }
        if (clientesDelMesPasado.isEmpty()) {
            return 1;
        }
        return (clientesDelMes.size()-clientesNuevos())/clientesDelMesPasado.size();
    }

    public RegistroMensualClientes(Venta primeraVenta) {
        this.clientesDelMes = new ArrayList<>();
        this.clientesDelMesPasado = new ArrayList<>();
        this.clientesDelMes.add(primeraVenta.getComprador());
        this.mesDeCompra = YearMonth.from(primeraVenta.getFecha());
    }

    public void addCliente(Cliente cliente) {
        if (!(this.clientesDelMes.contains(cliente))) {
            this.clientesDelMes.add(cliente);
        }
    }

    /**
     * @return the clientesDelMes
     */
    public ArrayList<Cliente> getClientesDelMes() {
        return clientesDelMes;
    }

    /**
     * @param clientesDelMes the clientesDelMes to set
     */
    public void setClientesDelMes(ArrayList<Cliente> clientesDelMes) {
        this.clientesDelMes = clientesDelMes;
    }

    /**
     * @return the mesDeCompra
     */
    public YearMonth getMesDeCompra() {
        return mesDeCompra;
    }

    /**
     * @param mesDeCompra the mesDeCompra to set
     */
    public void setMesDeCompra(YearMonth mesDeCompra) {
        this.mesDeCompra = mesDeCompra;
    }

    /**
     * @return the clientesDelMesPasado
     */
    public ArrayList<Cliente> getClientesDelMesPasado() {
        return clientesDelMesPasado;
    }

    /**
     * @param clientesDelMesPasado the clientesDelMesPasado to set
     */
    public void setClientesDelMesPasado(ArrayList<Cliente> clientesDelMesPasado) {
        this.clientesDelMesPasado = clientesDelMesPasado;
    }
}
