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
        tablaClientes = new DefaultTableModel();
        tablaClientes.addColumn("ID cliente");
        tablaClientes.addColumn("Nombre cliente");
        panelVendedor.getjTableClienteID().setModel(tablaClientes);
    }

    
    
    public void initPanelVendedorController(){
        getPanelVendedor().getjBClienteAgregar().addActionListener(e -> agregarCliente());
        getPanelVendedor().getjBVentaAgregarVenta().addActionListener(e -> agregarVenta());
        getPanelVendedor().getjBLogout().addActionListener(e -> regresarLogin());
    }
    
    public void agregarCliente(){
        String nombreCliente =  panelVendedor.getjTClienteNombre().getText().trim();
        if (Validators.isNullEmptyOrBlank(nombreCliente)) {
            JOptionPane.showMessageDialog(null, "Por favor no dejes casillas sin llenar.");
        }
        else{
            loggedInVendedor.addCliente(nombreCliente, listaClientes);
        }
    }
    
    public void agregarVenta(){
        String iDString = panelVendedor.getjTVentaIDCliente().getText();
        if (Validators.isInt(iDString)) {
            int iD = Integer.valueOf(iDString);
            if (Validators.isIntInclusiveBetween(0, listaClientes.size()-1, iD)) {
                loggedInVendedor.addVenta(listaVentas, listaClientes, registroMensualClientes, iD);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor introduzca un ID de cliente valido.");
        }
    }
    
    public void regresarLogin(){
        panelVendedor.dispose();
        login.setVisible(true);
    }
    
    public void cargarPanel() {
        panelVendedor.getjLPromedioVentasMensuales().setText(String.valueOf(getLoggedInVendedor().promVentasMensuales()));
        panelVendedor.getjLPromedioVentasTrimestrales().setText(String.valueOf(getLoggedInVendedor().promVentasTrimestrales()));
        tablaClientes.setRowCount(0);
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
