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
        String datosVendedor = String.format("++++++++++++++++++++++++\nID vendedor: %d\nNombre: %s\nUsuario: %s\nPassword: %s\n-------------------\nVentas realizadas:\n", this.getIdVendedor(), this.nombre, this.username, this.password);
        String ventasVendedor = Venta.getVentasString(this.getListaVentas());
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

    public double promVentasMensuales() {
        if (this.getListaVentas().isEmpty()) {
            return 0;
        }
        YearMonth primerVenta = YearMonth.from(this.getListaVentas().get(0).getFecha());
        YearMonth ultimaVenta = YearMonth.from(this.getListaVentas().get(this.getListaVentas().size() - 1).getFecha());
        double cantMeses = Double.valueOf(ChronoUnit.MONTHS.between(primerVenta, ultimaVenta) + 1);
        double cantVentas = getListaVentas().size();
        return cantVentas / cantMeses;
    }

    public double promVentasTrimestrales() {
        return promVentasMensuales() * 3;
    }

    public int ventasUltimoPeriodo() {
        if (getListaVentas().isEmpty()) {
            return 0;
        }
        int cantVentas = 0;
        for (int i = 0; i < getListaVentas().size(); i++) {
            Venta get = getListaVentas().get(i);
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
