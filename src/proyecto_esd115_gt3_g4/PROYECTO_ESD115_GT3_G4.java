/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_esd115_gt3_g4;

import controller.LoginController;
import controller.PanelAdminController;
import controller.PanelVendedorController;
import java.time.LocalDate;
import java.util.ArrayList;
import model.*;
import view.Login;
import view.PanelAdmin;
import view.PanelVendedor;

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
        
        //Se crean las "bases de datos" 
        ArrayList<Vendedor> listaVendedores = new ArrayList<>();
        ArrayList<Venta> listaVentas = new ArrayList<>();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<RegistroMensualClientes> clientesMensuales = new ArrayList<>(); 
        //Se crea el administrador
        Admin admin = new Admin("admin", "admin", "admin");
        //Se crean las interfaces de usuario
        PanelAdmin panelAdmin = new PanelAdmin();
        Login login = new Login();
        PanelVendedor panelVendedor = new PanelVendedor();
        //Se crean los controladores de las interfaces de usuario y se inicializan
        PanelAdminController panelAdminController = new PanelAdminController(listaVendedores, listaVentas, listaClientes, clientesMensuales, admin, login, panelAdmin);
        panelAdminController.initPanelAdminController();
        
        PanelVendedorController panelVendedorController = new PanelVendedorController(listaClientes, login, panelVendedor, clientesMensuales, listaVentas);
        panelVendedorController.initPanelVendedorController();
        
        LoginController loginController = new LoginController(listaVendedores, admin, panelAdmin, panelVendedor, login, panelVendedorController, panelAdminController);
        loginController.initLoguinGUIControlador();
        //se hace visible la interfaz del login
        login.setVisible(true);
        //se agregan vendedores base 
        listaVendedores.add(new Vendedor("Tonio", "TonioMax", "abc"));
        listaVendedores.add(new Vendedor("Ponio", "PonioMax", "abc"));
        listaVendedores.add(new Vendedor("Donio", "DonioMax", "abc"));
        //se crean clientes base
        listaClientes.add(new Cliente("Pedro"));
        listaClientes.add(new Cliente("Paco"));
        listaClientes.add(new Cliente("Pablo"));
        listaClientes.add(new Cliente("Pascal"));
        listaClientes.add(new Cliente("Paz"));
        listaClientes.add(new Cliente("Pio"));
        
        //Se agregan ventas base en diferentes periodos de tiempo
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 0, LocalDate.now().minusMonths(5));
        
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 0, LocalDate.now().minusMonths(4));
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 1, LocalDate.now().minusMonths(4));
        
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 0, LocalDate.now().minusMonths(3));
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 1, LocalDate.now().minusMonths(3));
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 2, LocalDate.now().minusMonths(3));
        
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 0, LocalDate.now().minusMonths(2));
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 1, LocalDate.now().minusMonths(2));
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 2, LocalDate.now().minusMonths(2));
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 3, LocalDate.now().minusMonths(2));
        
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 0, LocalDate.now().minusMonths(1));
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 1, LocalDate.now().minusMonths(1));
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 2, LocalDate.now().minusMonths(1));
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 3, LocalDate.now().minusMonths(1));
        listaVendedores.get(2).addVenta(listaVentas, listaClientes, clientesMensuales, 4, LocalDate.now().minusMonths(1));
        
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 0);
        listaVendedores.get(0).addVenta(listaVentas, listaClientes, clientesMensuales, 1);
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 2);
        listaVendedores.get(1).addVenta(listaVentas, listaClientes, clientesMensuales, 3);
        listaVendedores.get(2).addVenta(listaVentas, listaClientes, clientesMensuales, 5);
        
        System.out.println("proyecto_esd115_gt3_g4.PROYECTO_ESD115_GT3_G4.main()");
//        Cliente.printClientes(listaClientes);
//        Venta.printVentas(listaVentas);
//        Vendedor.printVendedores(listaVendedores);
        //Se agregan prints a consola para poder realizar un debug correcto
        RegistroMensualClientes.printClientesPeriodo(clientesMensuales);
        System.out.println(admin.retencionMensualPromedio(clientesMensuales));
        System.out.println(admin.retencionMensualUltima(clientesMensuales));
        System.out.println("Clientes inicio periodo: ");
        System.out.println(admin.clientesInicioPeriodo(clientesMensuales));
        System.out.println("Clientes fin de periodo: ");
        System.out.println(admin.clientesFinPeriodo(clientesMensuales));
        System.out.println("Clientes nuevos del periodo: ");
        System.out.println(admin.clientesNuevos(clientesMensuales));
        System.out.println("Clientes retenidos del periodo: ");
        System.out.println(admin.clientesRetenidosPeriodo(clientesMensuales));
        
        System.out.println(listaVendedores.get(0).promVentasMensuales());
    }
    
}
