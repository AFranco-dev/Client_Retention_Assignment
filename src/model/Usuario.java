/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author jafra
 */
public abstract class Usuario extends Datos{
    protected String username;
    protected String password;
    
    protected boolean Login(String username, String password) {
        return this.getUsername().equals(username) && this.getPassword().equals(password);
    }
    
    protected void addCliente(String nombre, ArrayList<Cliente> listaClientes){
        Cliente cliente = new Cliente(nombre);
        listaClientes.add(cliente);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
