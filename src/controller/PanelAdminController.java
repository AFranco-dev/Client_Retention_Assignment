/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Admin;
import model.Cliente;
import model.RegistroMensualClientes;
import model.Vendedor;
import model.Venta;
import validators.Validators;
import view.Login;
import view.PanelAdmin;
import view.PanelVendedor;

/**
 *
 * @author jafra
 */
public class PanelAdminController {
    private ArrayList<Vendedor> listaVendedores;
    private ArrayList<Venta> listaVentas;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<RegistroMensualClientes> registroMensualClientes;
    private Admin admin;
    private Login login;
    private PanelAdmin panelAdmin;
    private DefaultTableModel tablaClientes, tablaVendedores;

    public PanelAdminController(
            ArrayList<Vendedor> listaVendedores, ArrayList<Venta> listaVentas, 
            ArrayList<Cliente> listaClientes, 
            ArrayList<RegistroMensualClientes> registroMensualClientes, 
            Admin admin, Login login, PanelAdmin panelAdmin) {
        this.listaVendedores = listaVendedores;
        this.listaVentas = listaVentas;
        this.listaClientes = listaClientes;
        this.registroMensualClientes = registroMensualClientes;
        this.admin = admin;
        this.login = login;
        this.panelAdmin = panelAdmin;
        tablaClientes = new DefaultTableModel();
        tablaClientes.addColumn("ID");
        tablaClientes.addColumn("Nombre");
        tablaClientes.addColumn("Compró ultimo mes");
        tablaClientes.addColumn("Compró ultimos 3 meses");
        panelAdmin.getjTableClientes().setModel(tablaClientes);
        tablaVendedores = new DefaultTableModel();
        tablaVendedores.addColumn("ID");
        tablaVendedores.addColumn("Nombre");
        tablaVendedores.addColumn("Usuario");
        tablaVendedores.addColumn("Contraseña");
        tablaVendedores.addColumn("Promedio Mensual");
        tablaVendedores.addColumn("PromedioTrimestral");
        tablaVendedores.addColumn("Ventas ultimo mes");
        panelAdmin.getjTableVendedores().setModel(tablaVendedores);
    }
    
    public void initPanelAdminController(){
        panelAdmin.getjBLogout().addActionListener(e -> regresarLogin());
        panelAdmin.getjBClienteAgregar().addActionListener(e -> agregarCliente());
        panelAdmin.getjBVendedorAgregar().addActionListener(e -> agregarVendedor());
    }
    
    public void agregarCliente(){
        String nombreCliente =  panelAdmin.getjTClienteNombre().getText().trim();
        if (Validators.isNullEmptyOrBlank(nombreCliente)) {
            JOptionPane.showMessageDialog(null, "Por favor no dejes casillas sin llenar.");
        }
        else{
            admin.addCliente(nombreCliente, listaClientes);
            cargarPanel();
        }
    }
    
    public void agregarVendedor(){
        String nombreVendedor =  panelAdmin.getjTVendedorNombre().getText().trim();
        String usuarioVendedor =  panelAdmin.getjTVendedorUsuario().getText().trim();
        String passwordVendedor =  panelAdmin.getjTVendedorPassword().getText().trim();
        if (Validators.isNullEmptyOrBlank(nombreVendedor) || Validators.isNullEmptyOrBlank(usuarioVendedor) || Validators.isNullEmptyOrBlank(passwordVendedor)) {
            JOptionPane.showMessageDialog(null, "Por favor no dejes casillas sin llenar.");
        }
        else{
            admin.addVendedor(listaVendedores, nombreVendedor, usuarioVendedor, passwordVendedor);
            cargarPanel();
        }
    }
    
    public void regresarLogin(){
        panelAdmin.dispose();
        login.setVisible(true);
    }
    
    public void cargarPanel(){
        String clientesInicioMes = String.valueOf(admin.clientesInicioPeriodo(registroMensualClientes));
        panelAdmin.getjLClientesInicioMes().setText(clientesInicioMes);
        //queda por implementar
        String clientesInicioTrimestre = String.valueOf(admin.clientesInicioTrimestre(listaClientes));
        panelAdmin.getjLClientesInicioTrimestre().setText(clientesInicioTrimestre);
        
        String clientesNuevosUltimoMes = String.valueOf(admin.clientesNuevos(registroMensualClientes));
        panelAdmin.getjLClientesNuevosUltimoMes().setText(clientesNuevosUltimoMes);
        //queda por implementar
        String clientesNuevosUltimoTrimestre = String.valueOf(admin.clientesNuevosTrimestre(listaClientes));
        panelAdmin.getjLClientesNuevosUltimoTrimestre().setText(clientesNuevosUltimoTrimestre);
        String clientesRetenenidosMesActual = String.valueOf(admin.clientesRetenidosPeriodo(registroMensualClientes));
        panelAdmin.getjLClientesRetenidosMesActual().setText(clientesRetenenidosMesActual);
        //queda por implementar
        String clientesRetenidosTrimestreActual = String.valueOf(admin.clientesRetenidosTrimestre(listaClientes));
        panelAdmin.getjLClientesRetenidosTrimestreActual().setText(clientesRetenidosTrimestreActual);
        String promVentasMensuales = String.valueOf(admin.ventasMensualesPromedio(listaVentas));
        panelAdmin.getjLPromedioVentasMensuales().setText(promVentasMensuales);
        //queda por implementar
        String promVentasTrimestrales = String.valueOf(admin.ventasMensualesPromedio(listaVentas)*3);
        panelAdmin.getjLPromedioVentasTrimestrales().setText(promVentasTrimestrales);
        String retMensualProm = String.valueOf(admin.retencionMensualPromedio(registroMensualClientes));
        panelAdmin.getjLRetencionMensualPromedio().setText(retMensualProm);
        //queda por implementtar
        panelAdmin.getjLRetencionTrimestralPromedio();
        String retMensualUltima = String.valueOf(admin.retencionMensualUltima(registroMensualClientes));
        panelAdmin.getjLRetencionUltimoMes().setText(retMensualUltima);
        //por implementar
        String retTrimestralUltima = String.valueOf(admin.retencionTrimestralUltima(listaClientes));
        panelAdmin.getjLRetencionUltimoTrimestre().setText(retTrimestralUltima);
        
        //implementacion llenado de tablas
        
        tablaClientes.setRowCount(0);
        if (!listaClientes.isEmpty()) {
            String[] clientes = new String[4];
            for (int i = 0; i < listaClientes.size(); i++) {
                Cliente get = listaClientes.get(i);
                clientes[0] = String.valueOf(get.getIdCliente());
                clientes[1] = get.getNombre();
                clientes[2] = String.valueOf(get.comproUltimoMes());
                clientes[3] = String.valueOf(get.comproUltimoTrimestre());
                tablaClientes.addRow(clientes);
            }
        }
        
        tablaVendedores.setRowCount(0);
        if (!listaVendedores.isEmpty()) {
            String[] vendedores = new String[7];
            for (int i = 0; i < listaVendedores.size(); i++) {
                Vendedor get = listaVendedores.get(i);
                vendedores[0] = String.valueOf(get.getIdVendedor());
                vendedores[1] = get.getNombre();
                vendedores[2] = get.getUsername();
                vendedores[3] = get.getPassword();
                vendedores[4] = String.valueOf(get.promVentasMensuales());
                vendedores[5] = String.valueOf(get.promVentasTrimestrales());
                vendedores[6] = String.valueOf(get.ventasUltimoPeriodo());
                tablaVendedores.addRow(vendedores);
            }
        }
        
    }
}
