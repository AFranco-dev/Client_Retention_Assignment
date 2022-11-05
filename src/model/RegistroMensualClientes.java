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
        String periodo = String.format("$$$$$$$$$$$$$$$$$$$$$$\nPeriodo de la compra de los clientes: %s\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n", this.getMesDeCompra());
        String clientesDelPeriodo = "Clientes del periodo actual: \n" + Cliente.getClientesString(getClientesDelMes());
        String clientesPeriodoAnterior = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\nClientes del periodo anterior: \n" + Cliente.getClientesString(getClientesDelMesPasado());
        String retencionDelMes = String.format("La retencion de clientes del periodo mensual ha sido del: %f%%\n", this.retencionDeClientes()*100);
        return periodo + clientesDelPeriodo + clientesPeriodoAnterior + retencionDelMes;
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
    
    public int clientesNuevos() {
        if (getClientesDelMes().isEmpty()) {
            return 0;
        }
        int cNuevos = 0;
        for (int i = 0; i < getClientesDelMes().size(); i++) {
            Cliente get = getClientesDelMes().get(i);
            if (!(clientesDelMesPasado.contains(get))) {
                cNuevos++;
            }
        }
        return cNuevos;
    }

    public double retencionDeClientes() {
        if (getClientesDelMes().isEmpty()) {
            return 0;
        }
        if (getClientesDelMesPasado().isEmpty()) {
            return 1;
        }
        return (getClientesDelMes().size()-clientesNuevos())/getClientesDelMesPasado().size();
    }

    public RegistroMensualClientes(Venta primeraVenta) {
        this.clientesDelMes = new ArrayList<>();
        this.clientesDelMesPasado = new ArrayList<>();
        this.clientesDelMes.add(primeraVenta.getComprador());
        this.mesDeCompra = YearMonth.from(primeraVenta.getFecha());
    }

    public void addCliente(Cliente cliente) {
        if (!(this.clientesDelMes.contains(cliente))) {
            this.getClientesDelMes().add(cliente);
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
