/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateSisGesIncidencias;

import HibernateSisGesIncidenciasPOJOs.Empleados;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author sfcar
 */
public class SisGesIncidenciasORM {
    
    private Session sesion; //Inciamos una vez y cerramos una vez al acabar todo
    private Transaction transaccion; //Empezar y acabar en cada método
    
    public SisGesIncidenciasORM(){
        //Iniciar sesión
        sesion = HibernateUtil.getSessionFactory().openSession();
    }
    
    //7A. Insertar un empleado nuevo en la B.D.
    public void insertarEmpleado (Empleados e){
        try{ //Cazamos la runtime exception para no verla cuando ejecutemos el proyecto
            transaccion = sesion.beginTransaction();//Siempre se trabaja con transacciones
            sesion.save(e);//Insertamos el empleado
            transaccion.commit();; //Hacemos el commit  
        } catch (ConstraintViolationException ex){ //Indicamos el único exception que nos puede dar
            transaccion.rollback();
            throw ex; //La lanzamos y la cogemos de nuevo en el main
            
        }
        
    }
    
    //7C. Modificar el perfil de un empleado existente.
    public void actualizarEmpleado (Empleados e){
        transaccion = sesion.beginTransaction();
        sesion.update(e);
        transaccion.commit();
    }
    
    //7B. Validar la entrada de un empleado (suministrando usuario y contraseña)
    public boolean validarEmpleado(Empleados e){
        Empleados elEmpleado = (Empleados) sesion.get(Empleados.class, e.getUsername());
       
        //Mira en la tabla empleados si existe un empleado con el username que le estamos pasando
        if (elEmpleado != null){
            return true;
        } else {
            return false;
        }
        
    }
    
    public List<Incidencias>
    
    //7D. Modificar contraseña de un empleado existente
    public void actualizarPassword (Empleados e){
        actualizarEmpleado(e); //Llamo al método
    }
    
    //7E. Eliminar Empleado
    public void eliminarEmpleado (Empleados e){
        transaccion = sesion.beginTransaction();
        sesion.delete(e);
        transaccion.commit();
    }
    
    
    public void close(){
        sesion.close(); //Cierra la sesión
        HibernateUtil.destroy();/*Llama al método destroy para de cerrar el servicio
        de conexiones*/
        
    }
    
}
