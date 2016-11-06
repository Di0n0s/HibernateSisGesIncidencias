/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateSisGesIncidencias;

import HibernateSisGesIncidenciasPOJOs.Empleados;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author sfcar
 */
public class TestHibernate {
    
    public static void main(String[] args){
        //Inciamos el gestor con ORM
        SisGesIncidenciasORM miGestor = new SisGesIncidenciasORM();
        
        //Trabajamos con ORM
        
//        //7A. Insertar un empleado nuevo en la BBDD (FUNCIONA)
//        Empleados e = new Empleados("cnavarro", "nbt678", "Carlos Navarro Casamira", "+34621784523");
//        try{ //Cazamos el ConstraintViolationException para mostrarla por pantalla
//            System.out.println("Insertando empleado...");
//            miGestor.insertarEmpleado(e);
//            System.out.println("Empleado insertado!"); 
//        } catch (ConstraintViolationException ex){
//            System.out.println("El empleado no se ha podido insertar. Ya existe!");
//        }
        
//        //7C. Modificar el perfil de un empleado existente.
//        Empleados e = new Empleados("cnavarro", "nbt678", "Carlos Navarro Casamira", "+34621784523");
//        try{ //Cazamos el ConstraintViolationException para mostrarla por pantalla
//            System.out.println("Actualizando empleado...");
//            e.setPassword("mav164");
//            e.setNombreCompleto("Camilo Navarro Montesino");
//            e.setTelefono("+34687325815");
//            miGestor.actualizarEmpleado(e);
//            System.out.println("Empleado actualizado!");
//            System.out.println(e);
//        } catch (ConstraintViolationException ex){
//            System.out.println("El empleado no ha sido actualizado por el siguiente motivo: "+ex.getMessage());
//        }
        
        //7B. Validar la entrada de un empleado (suministrando usuario y contraseña)
        Empleados e = new Empleados("cnavarro", "mav164");
        System.out.println("Validando usuario...");
        if(miGestor.validarEmpleado(e)){
            System.out.println("Usuario validado!");
        } else{
            System.out.println("El usuario NO existe");
        }
        
        //Cerramos
        miGestor.close();
        
    } 
    
}
