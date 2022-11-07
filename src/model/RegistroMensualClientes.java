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
//    Esta clase cuenta con un ArrayList de los clientes del mes que se le asigna
//            y otro con los clientes del mes anterior
    private ArrayList<Cliente> clientesDelMes;
    private ArrayList<Cliente> clientesDelMesPasado;
    private YearMonth mesDeCompra;
    //metodo para convertir a String el objeto
    @Override public String toString(){
        String periodo = String.format("$$$$$$$$$$$$$$$$$$$$$$\nPeriodo de la compra de los clientes: %s\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n", this.getMesDeCompra());
        String clientesDelPeriodo = "Clientes del periodo actual: \n" + Cliente.getClientesString(getClientesDelMes());
        String clientesPeriodoAnterior = "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\nClientes del periodo anterior: \n" + Cliente.getClientesString(getClientesDelMesPasado());
        String retencionDelMes = String.format("La retencion de clientes del periodo mensual ha sido del: %f%%\n", this.retencionDeClientes()*100);
        return periodo + clientesDelPeriodo + clientesPeriodoAnterior + retencionDelMes;
    }
    //metodo para imprimir todas las listas de clientes de un ArrayList
    public static void printClientesPeriodo(ArrayList<RegistroMensualClientes> list){
        if (list.isEmpty()) {
            System.out.println("No hay registros.");
        }
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
            System.out.println(get.toString());
            get.retencionDeClientes();
            System.out.println(get.clientesDelMes.size()-get.clientesNuevos());
        }
    }
    //metodo par obtener la cantidad de clientes nuevos del mes actual
    public int clientesNuevos() {
        if (getClientesDelMes().isEmpty()) {
            return 0;
        }
        int cNuevos = 0;
        for (int i = 0; i < getClientesDelMes().size(); i++) {
            Cliente get = getClientesDelMes().get(i);
            //se verifica que el cliente esté unicamente en el mes actual para aumentar el contador
            if (!(clientesDelMesPasado.contains(get))) {
                cNuevos++;
            }
        }
        return cNuevos;
    }
    //metodo para calcular la retención de clientes del mes del registro
    public double retencionDeClientes() {
        //si los clientes del mes está vacío, se regresa 0
        if (getClientesDelMes().isEmpty()) {
            return 0;
        }
        //si los clientes del mes pasado están vacíos, se regresa 1
        if (getClientesDelMesPasado().isEmpty()) {
            return 1;
        }
        //se definene las variables con sus metodos para obtenerlas respectivos
        double clientesFinPeriodo = Double.valueOf(getClientesDelMes().size());
        double clientesNuevosDelPeriodo = Double.valueOf(clientesNuevos());
        double clientesAlComienzo = Double.valueOf(getClientesDelMesPasado().size());
        //se regresa la retención de clientes
        return (clientesFinPeriodo-clientesNuevosDelPeriodo)/clientesAlComienzo;
    }
    //Constructor que se utiliza unicamente cuando se crea la primera venta del mes
    public RegistroMensualClientes(Venta primeraVenta) {
        this.clientesDelMes = new ArrayList<>();
        this.clientesDelMesPasado = new ArrayList<>();
        this.clientesDelMes.add(primeraVenta.getComprador());
        this.mesDeCompra = YearMonth.from(primeraVenta.getFecha());
    }
    //metodo que agrega cliente al registro, sin repetir
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
