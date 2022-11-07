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
        //se creanlos modelos de las tablas y se sincronizan con las de la GUI
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
        //se agregan los listener para los botones de la ptanlla
        panelAdmin.getjBLogout().addActionListener(e -> regresarLogin());
        panelAdmin.getjBClienteAgregar().addActionListener(e -> agregarCliente());
        panelAdmin.getjBVendedorAgregar().addActionListener(e -> agregarVendedor());
    }
    //metodo apra agregar cliente desde los datos de la GUI
    public void agregarCliente(){
        String nombreCliente =  panelAdmin.getjTClienteNombre().getText().trim();
        //se verifica que el nombre no esté vacío
        if (Validators.isNullEmptyOrBlank(nombreCliente)) {
            //si está vacía se manda alerta
            JOptionPane.showMessageDialog(null, "Por favor no dejes casillas sin llenar.");
        }
        else{
            //si no está vacío se agrega el cliente con los metodos del admin
            admin.addCliente(nombreCliente, listaClientes);
            cargarPanel();
            panelAdmin.getjTClienteNombre().setText("");
        }
    }
    //metood para agregar vendedor al ArrayList de vendedores desde la GUI
    public void agregarVendedor(){
        String nombreVendedor =  panelAdmin.getjTVendedorNombre().getText().trim();
        String usuarioVendedor =  panelAdmin.getjTVendedorUsuario().getText().trim();
        String passwordVendedor =  panelAdmin.getjTVendedorPassword().getText().trim();
        //se verifica que ninfuno de los campos esté vacío
        if (Validators.isNullEmptyOrBlank(nombreVendedor) || Validators.isNullEmptyOrBlank(usuarioVendedor) || Validators.isNullEmptyOrBlank(passwordVendedor)) {
            JOptionPane.showMessageDialog(null, "Por favor no dejes casillas sin llenar.");
        }
        else{
            //si ninguno de los campos está vacío se agrega el vendedor al ArrayList y se vacían los campos
            admin.addVendedor(listaVendedores, nombreVendedor, usuarioVendedor, passwordVendedor);
            cargarPanel();
            panelAdmin.getjTVendedorNombre().setText("");
            panelAdmin.getjTVendedorUsuario().setText("");
            panelAdmin.getjTVendedorPassword().setText("");
        }
    }
    
    public void regresarLogin(){
        //metodo apra regresar a la pantalla del login
        panelAdmin.dispose();
        login.setVisible(true);
    }
    //metodo para cargar todos los campos de datos del panel del admin
    public void cargarPanel(){
        //se cargan los clientes al inicio del mes
        String clientesInicioMes = String.valueOf(admin.clientesInicioPeriodo(registroMensualClientes));
        panelAdmin.getjLClientesInicioMes().setText(clientesInicioMes);
        //clientes inicio trimestrs
        String clientesInicioTrimestre = String.valueOf(admin.clientesInicioTrimestre(listaClientes));
        panelAdmin.getjLClientesInicioTrimestre().setText(clientesInicioTrimestre);
        //clientes nuevos ultimo mes
        String clientesNuevosUltimoMes = String.valueOf(admin.clientesNuevos(registroMensualClientes));
        panelAdmin.getjLClientesNuevosUltimoMes().setText(clientesNuevosUltimoMes);
        //clientes nuevos ultimo trimestres
        String clientesNuevosUltimoTrimestre = String.valueOf(admin.clientesNuevosTrimestre(listaClientes));
        panelAdmin.getjLClientesNuevosUltimoTrimestre().setText(clientesNuevosUltimoTrimestre);
        //Clientes retenidos en el mes actual
        String clientesRetenenidosMesActual = String.valueOf(admin.clientesRetenidosPeriodo(registroMensualClientes));
        panelAdmin.getjLClientesRetenidosMesActual().setText(clientesRetenenidosMesActual);
        //clientes retenidos en el trimestre
        String clientesRetenidosTrimestreActual = String.valueOf(admin.clientesRetenidosTrimestre(listaClientes));
        panelAdmin.getjLClientesRetenidosTrimestreActual().setText(clientesRetenidosTrimestreActual);
        //promedio de ventas mensuales
        String promVentasMensuales = String.valueOf(admin.ventasMensualesPromedio(listaVentas));
        panelAdmin.getjLPromedioVentasMensuales().setText(promVentasMensuales);
        //promedio de ventas trimestrales
        String promVentasTrimestrales = String.valueOf(admin.ventasMensualesPromedio(listaVentas)*3);
        panelAdmin.getjLPromedioVentasTrimestrales().setText(promVentasTrimestrales);
        //retencion mensual promedio
        String retMensualProm = String.valueOf(admin.retencionMensualPromedio(registroMensualClientes)*100)+"%%";
        panelAdmin.getjLRetencionMensualPromedio().setText(retMensualProm);
        //queda por implementtar
        panelAdmin.getjLRetencionTrimestralPromedio();
        //retencion mensual del mes actual
        String retMensualUltima = String.valueOf(admin.retencionMensualUltima(registroMensualClientes)*100)+"%%";
        panelAdmin.getjLRetencionUltimoMes().setText(retMensualUltima);
        //retencion mensual del trimestre actual
        String retTrimestralUltima = String.valueOf(admin.retencionTrimestralUltima(listaClientes)*100)+"%%";
        panelAdmin.getjLRetencionUltimoTrimestre().setText(retTrimestralUltima);
        
        //implementacion llenado de tablas
        //se llenan las tablas con los valores de los ArrayList
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
