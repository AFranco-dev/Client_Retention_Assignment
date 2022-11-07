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
    //parametros del vendedor que incluye su lista de ventas
    private int idVendedor;
    private static int cantVendedores = 0;
    private ArrayList<Venta> listaVentas;
    //cosntructor del vendedor
    public Vendedor(String nombre, String username, String password) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.idVendedor = cantVendedores;
        cantVendedores++;
        listaVentas = new ArrayList<>();
    }
    //funcion para convertir a String una isntancia de vendedor
    @Override
    public String toString() {
        String datosVendedor = String.format("++++++++++++++++++++++++\nID vendedor: %d\nNombre: %s\nUsuario: %s\nPassword: %s\n-------------------\nVentas realizadas:\n", this.getIdVendedor(), this.nombre, this.username, this.password);
        String ventasVendedor = Venta.getVentasString(this.getListaVentas());
        String promVentas = String.format(
                "********************\nEl promedio de ventas mensuales es: %f\nEl promedio de ventas trimestrales es: %f\n",
                this.promVentasMensuales(), this.promVentasTrimestrales());
        return datosVendedor + ventasVendedor + promVentas;
    }
    //metodo para imprimir en consola una lista de vendedores
    public static void printVendedores(ArrayList<Vendedor> list) {
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println(next.toString());
        }
    }
    //Metodo para agregar venta
    public void addVenta(ArrayList<Venta> listaVentas, ArrayList<Cliente> listaClientes, ArrayList<RegistroMensualClientes> listaMensual, int iD) {
        //se crea una nueva venta
        Venta venta = new Venta(listaClientes.get(iD), this);
        //se agrega a la lista de ventas del programa main
        listaVentas.add(venta);
        //se agrega a la lista de ventas del cliente
        this.getListaVentas().add(venta);
        //se agrega a la lista de compras del cliente
        listaClientes.get(iD).addVenta(venta);
        //si la venta es la primera, se crea un objeto nuevo de registro mensual
        if (listaMensual.isEmpty()) {
            listaMensual.add(new RegistroMensualClientes(venta));
            return;
        }
        
        YearMonth fechaVenta = YearMonth.from(venta.getFecha());
        for (int i = 0; i < listaMensual.size(); i++) {
            RegistroMensualClientes get = listaMensual.get(i);
            //si existe un registro mensual con la fecha de la venta, se agrega la venta
            if (get.getMesDeCompra().equals(fechaVenta)) {
                get.addCliente(venta.getComprador());
                return;
            }
        }
        //si no existe un registro mensual con la fecha se crea uno nuevo
        listaMensual.add(new RegistroMensualClientes(venta));
        listaMensual.get(listaMensual.size() - 1).setClientesDelMesPasado(new ArrayList<>(listaMensual.get(listaMensual.size() - 2).getClientesDelMes()));
    }
    
    //Es un polimorfismo de la clase anterior que toma un atributo de localdate como parametro extra
    public void addVenta(ArrayList<Venta> listaVentas, ArrayList<Cliente> listaClientes, ArrayList<RegistroMensualClientes> listaMensual, int iD, LocalDate date) {
        Venta venta = new Venta(listaClientes.get(iD), this, date);
        listaVentas.add(venta);
        this.getListaVentas().add(venta);
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
        listaMensual.get(listaMensual.size() - 1).setClientesDelMesPasado(new ArrayList<>(listaMensual.get(listaMensual.size() - 2).getClientesDelMes()));
    }
    //metodo para calcular las ventas mensuales del vendedor
    public double promVentasMensuales() {
        //si no tiene ventas se regresa cero
        if (this.getListaVentas().isEmpty()) {
            return 0;
        }
        //se calcula el numero de mese con CHRONOUNIT.MONTHS y YearMonth
        YearMonth primerVenta = YearMonth.from(this.getListaVentas().get(0).getFecha());
        YearMonth ultimaVenta = YearMonth.from(this.getListaVentas().get(this.getListaVentas().size() - 1).getFecha());
        double cantMeses = Double.valueOf(ChronoUnit.MONTHS.between(primerVenta, ultimaVenta) + 1);
        //se calcula el numero de ventas
        double cantVentas = getListaVentas().size();
        //se regresa el promedio mensual de ventas
        return cantVentas / cantMeses;
    }

    public double promVentasTrimestrales() {
        //se proyecta el promedio mensual en periodo trimestral
        return promVentasMensuales() * 3;
    }

    public int ventasUltimoPeriodo() {
        //si no hay ventas se regresa cero
        if (getListaVentas().isEmpty()) {
            return 0;
        }
        //se inicializa a cero la cantidad de ventasd
        int cantVentas = 0;
        for (int i = 0; i < getListaVentas().size(); i++) {
            Venta get = getListaVentas().get(i);
            //si las ventas son del mes actual se aumenta el contador.
            if (YearMonth.from(get.getFecha()).equals(YearMonth.now())) {
                cantVentas++;
            }
        }
        return cantVentas;
    }

    /**
     * @return the idVendedor
     */
    public int getIdVendedor() {
        return idVendedor;
    }

    /**
     * @param idVendedor the idVendedor to set
     */
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    /**
     * @return the cantVendedores
     */
    public static int getCantVendedores() {
        return cantVendedores;
    }

    /**
     * @param aCantVendedores the cantVendedores to set
     */
    public static void setCantVendedores(int aCantVendedores) {
        cantVendedores = aCantVendedores;
    }

    /**
     * @return the listaVentas
     */
    public ArrayList<Venta> getListaVentas() {
        return listaVentas;
    }

    /**
     * @param listaVentas the listaVentas to set
     */
    public void setListaVentas(ArrayList<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }
}
