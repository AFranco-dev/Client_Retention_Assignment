/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

/**
 *
 * @author jafra
 * 
 * Estas son clases para poder validar la entrada de los datos en 
 * la interfaz
 */
public class Validators {
    public static boolean isNullEmptyOrBlank(String s){
        if (s==null || s.trim().isEmpty()) {
            return true;
        }
        return false;
    }
    
    public static boolean isDouble(String number){
        boolean check;
        try {
            Double.parseDouble(number);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }
    
    public static boolean isInt(String number){
        boolean check;
        try {
            Integer.parseInt(number);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }
    
    public static boolean isIntInclusiveBetween(int a, int b, int check){
        if (check >= a && check <= b) {
            return true;
        } else {
            return false;
        }
    }
}
