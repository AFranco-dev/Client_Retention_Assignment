/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Admin;
import model.Vendedor;
import view.Login;
import view.PanelAdmin;
import view.PanelVendedor;
/**
 *
 * @author jafra
 */
public class LoginController {
    ArrayList<Vendedor> vendedores;
    Admin admin;
    PanelAdmin panelAdmin;
    PanelVendedor panelVendedor;
    Login login;
    PanelVendedorController panelVendedorController;
    PanelAdminController panelAdminController;

    public LoginController(
            ArrayList<Vendedor> vendedores, Admin admin, 
            PanelAdmin panelAdmin, PanelVendedor panelVendedor, 
            Login login, PanelVendedorController panelVendedorController,
            PanelAdminController panelAdminController) {
        this.vendedores = vendedores;
        this.admin = admin;
        this.panelAdmin = panelAdmin;
        this.panelVendedor = panelVendedor;
        this.login = login;
        this.panelVendedorController = panelVendedorController;
        this.panelAdminController = panelAdminController;
    }

    public void initLoguinGUIControlador(){
        this.login.getJBLogin().addActionListener(e -> this.checkLoginCredentials());
    }
    
    public void checkLoginCredentials(){
        String username, password;
        username = login.getJTUsername().getText();
        password = login.getJTPassword().getText();
        boolean checkLogin = false;
        Vendedor loggedVendedor = null;
        if (login.getJRBVendedor().isSelected()) {
            for (int i = 0; i < vendedores.size(); i++) {
                Vendedor get = vendedores.get(i);
                if (get.Login(username, password)) {
                    checkLogin = true;
                    loggedVendedor = get;
                }
            }
        }
        else if(login.getJRDAdmin().isSelected()) {
            if (admin.Login(username, password)) {
                checkLogin = true;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Por favor selecciona el tipo de usuario.");
        }
        if (checkLogin == true) {
            this.login.dispose();
            if (login.getJRBVendedor().isSelected()) {
                panelVendedorController.setLoggedInVendedor(loggedVendedor);
                panelVendedor.setVisible(true);
                panelVendedorController.cargarPanel();
            }
            else if(login.getJRDAdmin().isSelected()) {
                panelAdmin.setVisible(true);
                panelAdminController.cargarPanel();
            }
            login.getJTPassword().setText("");
            login.getJTUsername().setText("");
        }
        else{
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos.");
        }
    }
}
