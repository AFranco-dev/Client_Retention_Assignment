/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_esd115_gt3_g4;

import java.util.ArrayList;
import java.util.Iterator;
import model.*;

/**
 *
 * @author jafra
 */
public class PROYECTO_ESD115_GT3_G4 {
    
    /**
     *
     * @param list
     */
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Vendedor> listaVendedores = new ArrayList<>();
        ArrayList<Venta> listaVentas = new ArrayList<>();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<RegistroMensualClientes> clientesMensuales = new ArrayList<>(); 
        
        Admin admin = new Admin("admin", "admin", "admin");
        
        listaVendedores.add(new Vendedor("Tonio", "TonioMax", "abc"));
        listaVendedores.add(new Vendedor("Ponio", "PonioMax", "abc"));
        listaVendedores.add(new Vendedor("Donio", "DonioMax", "abc"));
        
        listaClientes.add(new Cliente("Pedro"));
        listaClientes.add(new Cliente("Paco"));
        listaClientes.add(new Cliente("Pablo"));
        listaClientes.add(new Cliente("Pascal"));
        listaClientes.add(new Cliente("Paz"));
        listaClientes.add(new Cliente("Pio"));
        
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 0);
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 1);
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 2);
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 3);
        listaVendedores.get(2).addVenta(listaVentas, listaClientes, clientesMensuales, 4);
        listaVendedores.get(2).addVenta(listaVentas, listaClientes, clientesMensuales, 5);
        listaVendedores.get(2).addVenta(listaVentas, listaClientes, clientesMensuales, 0);
        
        System.out.println("proyecto_esd115_gt3_g4.PROYECTO_ESD115_GT3_G4.main()");
        Cliente.printClientes(listaClientes);
        Venta.printVentas(listaVentas);
        Vendedor.printVendedores(listaVendedores);
        RegistroMensualClientes.printClientesPeriodo(clientesMensuales);
    }
    
}
