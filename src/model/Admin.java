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

/**
 *
 * @author jafra
 */
public class Admin extends Usuario {
    //Son las caracteristicas propias del administrador
    private int idAdministrador;
    private static int cantAdministradores = 0;
    //Este es el constructor del administrador, toma de referencia el nombre
    //usuario y contraseña
    public Admin(String nombre, String username, String password) {
        this.idAdministrador = cantAdministradores;
        this.cantAdministradores++;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
    }
    //Este es un metodo del admin, toma como parametro un ArrayList de la clase RegistroMensualCliente
    //para calcular la retención del mes actual
    public double retencionMensualUltima(ArrayList<RegistroMensualClientes> listaClientesPorMes) {
        //Se revisa si está vacía, en caso se regresa 0
        if (listaClientesPorMes.isEmpty()) {
            return 0;
        }
        //se setea la fecha actual
        YearMonth lastMonth = YearMonth.now();
        //se revisa con la fecha actual la lista 
        for (int i = 0; i < listaClientesPorMes.size(); i++) {
            RegistroMensualClientes get = listaClientesPorMes.get(i);
//            En caso en la lista haya un elemento que contenga la fecha actual
//            Se adquiere la retencion mensual del mes actual
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.retencionDeClientes();
            }
        }
        //Si no se encuentra nada con el mes actual se regresa 0
        return 0;
    }
    
    public double retencionTrimestralUltima(ArrayList<Cliente> list) {
//        Se chequea si la lista esta vacia en caso se regresa 0
        if (list.isEmpty()) {
            return 0;
        }
//      Se chequea con el metodo clientes inicio trimestre si 
//        si se inicio el mes ya con clientes, en caso de no regresa 0
        if (clientesInicioTrimestre(list) == 0) {
            return 0;
        }
//        se calcula la retencion de clientes con los metodos de la clase admin
//                respectivos para cada uno de los elementos de la formula
        return (clientesFinTrimestre(list)-clientesNuevosTrimestre(list))/clientesInicioTrimestre(list);
    }
    
    //metodo para calcular los clientes al inicio del trimestre
    public double clientesInicioTrimestre(ArrayList<Cliente> list){
//        Se inicializa la varibale del return con 0
        double clientes = 0;
//        se recorre la lsita para revisar los clientes que sí han comprado en 
//                el ultimo trimestre con el metodo de la clase Cliente
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
            if (get.comproPenultimoTrimestre()) {
                //n cso sí haya comprado se aumenta la cantidad de clientes
                clientes++;
            }
        }
        //regresa lka canbtoidad de clietnes finales
        return clientes;
    }
    
    
    //metodo para calcular la cantidad de clientes al final del trimestre actual
    public double clientesFinTrimestre(ArrayList<Cliente> list){
        //se inciialliza con cero
        double clientes = 0;
//        Se recorre la lista de clientes
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
//            si el cliente compro en el ultimo trimestre, se aumenta el contador
            if (get.comproUltimoTrimestre()) {
                clientes++;
            }
        }
//        se regresa el numero de clientes que contaron en el ultimo trimestres
        return clientes;
    }
    
    //metodo para calcular el numero de clientes nuevos en el trimestre actual
    public double clientesNuevosTrimestre(ArrayList<Cliente> list){
        //se inicializa a cero el contador
        double clientes = 0;
        for (int i = 0; i < list.size(); i++) {
            Cliente get = list.get(i);
            //si el cliente compro en el ultimo trimestre pero no en el 
//            penultimo, se aumenta el contador
            if (!get.comproPenultimoTrimestre() && get.comproUltimoTrimestre()) {
                clientes++;
            }
        }
        //se regresa la cantidad de clientes nuevos contados
        return clientes;
    }
    
    //metood cpara calcular el numero de clientes retenidos en el trimestre actual
    public double clientesRetenidosTrimestre(ArrayList<Cliente> list){
//        Se restan los clientes a fin de trimestre menos los nuevos
        return clientesFinTrimestre(list)-clientesNuevosTrimestre(list);
    }
    //metodo para calcular la retencion mensual promedio
    public double retencionMensualPromedio(ArrayList<RegistroMensualClientes> list) {
//        Si la lista esta vacia se regresa cero
        if (list.isEmpty()) {
            return 0;
        }
//        Si la lista tiene solamente un elemento se regresa la retencion de clientes
//                de ese elemento
        if (list.size()==1) {
            return list.get(0).retencionDeClientes();
        }
//        Se inicializa el sumador a cero
        double suma =0;
        for (int i = 0; i < list.size(); i++) {
//            Se suma la retencion de clientes del mes seleccionado al sumador
            RegistroMensualClientes get = list.get(i);
            suma+=get.retencionDeClientes();
        }
//        se regresa la suma entre la cantidad de meses lo que equivale a la retencion mensual promedio
        return suma/list.size();
    }
    
//    Metodo para calcular los clientes a inicio de periodo
    public int clientesInicioPeriodo (ArrayList<RegistroMensualClientes> list) {
//        Si la lista está vacía se regersa cero
        if (list.isEmpty()) {
            return 0;
        }
//        Se setea la fecha actual
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
//            Si la fecha del registro equivale a la fecha actual se 
//                    regresa el tamaño del arraylist de los clientes del 
//                            mes pasado lo cual equivale a los clientes a  
//            inicio de periodo
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.getClientesDelMesPasado().size();
            }
        }
        return 0;
    }
    
    //metodo para obtener los clientes nuevos del periodo
    public int clientesNuevos (ArrayList<RegistroMensualClientes> list) {
//        Si la lista está vacía se regresa cero
        if (list.isEmpty()) {
            return 0;
        }
//        se seta la feacha actual
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
//            Si la fecha actual es la misa que la del registro, se llama al
//                    metodo del registro que regresa los clientes nuevos y rse regresa el resultado
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.clientesNuevos();
            }
        }
        return 0;
    }
    
    //metodo para obtener la cantidad de clientes al final del periodo
    public int clientesFinPeriodo (ArrayList<RegistroMensualClientes> list) {
//        Si la lsita esta vacia se regresa cero
        if (list.isEmpty()) {
            return 0;
        }
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
            //si la fecha del registro es la misma que la de la fecha actual
            //Se regresa la cantidad del Arraylist de clientes del mes del registro
            //lo cual equivale a los clientes a fin de mes
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.getClientesDelMes().size();
            }
        }
        return 0;
    }
    
    //metodo que regresa los clientes retenidos en el periodo actual
    public int clientesRetenidosPeriodo (ArrayList<RegistroMensualClientes> list) {
        if (list.isEmpty()) {
            return 0;
        }
        YearMonth lastMonth = YearMonth.now();
        for (int i = 0; i < list.size(); i++) {
            RegistroMensualClientes get = list.get(i);
//            Si la fecha del registro concuerda con la fecha actual se regresa la 
//                    resta del los clientes del mes - clientes nuevos lo que equivale 
//                            a los clientes retenidos
            if (get.getMesDeCompra().equals(lastMonth)) {
                return get.getClientesDelMes().size()-get.clientesNuevos();
            }
        }
        return 0;
    }
    
    public double ventasMensualesPromedio (ArrayList<Venta> list) {
        if (list.isEmpty()) {
            return 0;
        }
        //se setean las fecha de la primera y ultima compra
        YearMonth primerVenta = YearMonth.from(list.get(0).getFecha());
        YearMonth ultimaVenta = YearMonth.from(list.get(list.size() - 1).getFecha());
//        Se calcula la cantidad de meses entre la primera y ultima compra
        double cantMeses = Double.valueOf(ChronoUnit.MONTHS.between(primerVenta, ultimaVenta) + 1);
//        Se calcula la cantidad de ventas
        double cantVentas = list.size();
//        Se obtiene el promedio de ventas mediante la division de la cantidad de meses
//                y la cantidad de ventas
        return cantVentas / cantMeses;
    }
    
    public void addVendedor (ArrayList<Vendedor> list, String nombre, String usuario, String contra){
//        Se crea y agrega un nuevo vendedor a la base de datos
        Vendedor v = new Vendedor(nombre, usuario, contra);
        list.add(v);
    }
}
