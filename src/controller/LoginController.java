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
    //todos los elemtnos con los que interactúa el login
    ArrayList<Vendedor> vendedores;
    Admin admin;
    PanelAdmin panelAdmin;
    PanelVendedor panelVendedor;
    Login login;
    PanelVendedorController panelVendedorController;
    PanelAdminController panelAdminController;
    //constructor del controlador del login GUI
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
    //metodo para inicializar el controaldor del login
    public void initLoguinGUIControlador(){
        //se agrega un actionListener en el boton del login 
        this.login.getJBLogin().addActionListener(e -> this.checkLoginCredentials());
    }
    //metodo que verifica que las credenciales ingresadas coincidan con las credenciales
    //de la lista de vendedores o del admin apra pasar a la siguiente GUI
    public void checkLoginCredentials(){
        
        String username, password;
        //se crean las variables del username y password con los input de los campos respectivos
        username = login.getJTUsername().getText();
        password = login.getJTPassword().getText();
        //se crea variable para chequear si se logró el login
        boolean checkLogin = false;
        //se crea un vendedor null para guardar el vendedor en caso el login sea exitoso
        Vendedor loggedVendedor = null;
        //se chequa el usuario como vendedor al seleccionar el radio button de vendedor
        if (login.getJRBVendedor().isSelected()) {
            for (int i = 0; i < vendedores.size(); i++) {
                Vendedor get = vendedores.get(i);
                //si el vendedor es el indicado el login es exitoso y se setea al vendedor como el vendedor que entró
                if (get.Login(username, password)) {
                    checkLogin = true;
                    loggedVendedor = get;
                }
            }
        }
        //se chequea si el usuario admin tiene las credenciales correcrtas en todo caso entra a la pantalla de admin
        else if(login.getJRDAdmin().isSelected()) {
            if (admin.Login(username, password)) {
                checkLogin = true;
            }
        }
        //si no hay radio button seleccionado se llama una alerta
        else {
            JOptionPane.showMessageDialog(null, "Por favor selecciona el tipo de usuario.");
        }
        //si el login fue exitoso se cambia a la pantalla respectiva del usuario que hizo login
        if (checkLogin == true) {
            this.login.dispose();
            if (login.getJRBVendedor().isSelected()) {
//                se setea el vendedor que ha hecho login en el panel del vendedor apra mostrar las metricas adecuadas
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
