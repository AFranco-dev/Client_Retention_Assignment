/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.RegistroMensualClientes;
import model.Vendedor;
import model.Venta;
import validators.Validators;
import view.Login;
import view.PanelVendedor;

/**
 *
 * @author jafra
 */
public class PanelVendedorController {
    private Vendedor loggedInVendedor;
    private ArrayList<Cliente> listaClientes;
    private Login login;
    private PanelVendedor panelVendedor;
    private DefaultTableModel tablaClientes;
    private ArrayList<RegistroMensualClientes> registroMensualClientes;
    private ArrayList<Venta> listaVentas;

    public PanelVendedorController(
            ArrayList<Cliente> listaClientes, Login login, PanelVendedor panelVendedor, 
            ArrayList<RegistroMensualClientes> registroMensualClientes, 
            ArrayList<Venta> listaVentas) {
        loggedInVendedor = null;
        this.listaClientes = listaClientes;
        this.login = login;
        this.panelVendedor = panelVendedor;
        this.registroMensualClientes = registroMensualClientes;
        this.listaVentas = listaVentas;
        //se crea la tabla modelo para la tabla de la interfaz y se sincroniza con la tabla de la interfaz
        tablaClientes = new DefaultTableModel();
        tablaClientes.addColumn("ID cliente");
        tablaClientes.addColumn("Nombre cliente");
        panelVendedor.getjTableClienteID().setModel(tablaClientes);
    }

    
    //metood para incializar el controlador del panel del vendedor
    public void initPanelVendedorController(){
        //event listeners:
        //para el JB de agregar cliente
        getPanelVendedor().getjBClienteAgregar().addActionListener(e -> agregarCliente());
        //para el JB de agregar venta
        getPanelVendedor().getjBVentaAgregarVenta().addActionListener(e -> agregarVenta());
        //para el JB de regresar a la pantalla de login
        getPanelVendedor().getjBLogout().addActionListener(e -> regresarLogin());
    }
    //metood para agregar un cliente al ArrayList de clientes
    public void agregarCliente(){
        //se limpia el input
        String nombreCliente =  panelVendedor.getjTClienteNombre().getText().trim();
        //si el input no está vacío se agrega el cliente
        if (Validators.isNullEmptyOrBlank(nombreCliente)) {
            JOptionPane.showMessageDialog(null, "Por favor no dejes casillas sin llenar.");
        }
        else{
            //se agegar el cliente y se recarga el panel y se limpian los campos de entrada
            loggedInVendedor.addCliente(nombreCliente, listaClientes);
            cargarPanel();
            panelVendedor.getjTClienteNombre().setText("");
        }
    }
    //metodo para gregar una venta al arrayList de venta, a la lista de ventas del vendedor y lista de compras del cliente
    public void agregarVenta(){
        String iDString = panelVendedor.getjTVentaIDCliente().getText();
        //se valida que el input sea un entero positivo entre el rango de las ID de cliente
        //si lo es se agrega la venta, se limpian los campos y se recarga el panel
        if (Validators.isInt(iDString)) {
            int iD = Integer.valueOf(iDString);
            if (Validators.isIntInclusiveBetween(0, listaClientes.size()-1, iD)) {
                loggedInVendedor.addVenta(listaVentas, listaClientes, registroMensualClientes, iD);
                cargarPanel();
                panelVendedor.getjTVentaIDCliente().setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Por favor introduzca un ID de cliente valido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor introduzca un ID de cliente valido.");
        }
    }
    //metodo para regresar al login
    public void regresarLogin(){
        //se desecha el panel del vendedor
        panelVendedor.dispose();
        //se vuelve visible el panel del login
        login.setVisible(true);
    }
    //metodo para cargar el panel del vendedor
    public void cargarPanel() {
        //se setean las metricas del vendedor que ha hecho login
        panelVendedor.getjLPromedioVentasMensuales().setText(String.valueOf(getLoggedInVendedor().promVentasMensuales()));
        panelVendedor.getjLPromedioVentasTrimestrales().setText(String.valueOf(getLoggedInVendedor().promVentasTrimestrales()));
        //se limpia la tabla
        tablaClientes.setRowCount(0);
        //si la lista de clientes no está vacía se agregan todos los clientes con sus atributos
        if (!listaClientes.isEmpty()) {
            String[] clientes = new String[2];
            for (int i = 0; i < listaClientes.size(); i++) {
                Cliente get = listaClientes.get(i);
                clientes[0] = String.valueOf(get.getIdCliente());
                clientes[1] = get.getNombre();
                tablaClientes.addRow(clientes);
            }
        }
        
    }

    /**
     * @return the loggedInVendedor
     */
    public Vendedor getLoggedInVendedor() {
        return loggedInVendedor;
    }

    /**
     * @param loggedInVendedor the loggedInVendedor to set
     */
    public void setLoggedInVendedor(Vendedor loggedInVendedor) {
        this.loggedInVendedor = loggedInVendedor;
    }

    /**
     * @return the listaClientes
     */
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * @return the panelVendedor
     */
    public PanelVendedor getPanelVendedor() {
        return panelVendedor;
    }

    /**
     * @param panelVendedor the panelVendedor to set
     */
    public void setPanelVendedor(PanelVendedor panelVendedor) {
        this.panelVendedor = panelVendedor;
    }
}
