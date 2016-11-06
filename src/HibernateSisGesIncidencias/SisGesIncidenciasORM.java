 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateSisGesIncidencias;

import HibernateSisGesIncidenciasPOJOs.Empleados;
import HibernateSisGesIncidenciasPOJOs.Historial;
import HibernateSisGesIncidenciasPOJOs.Incidencias;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
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
    
    //7B. Validar la entrada de un empleado (suministrando usuario y contraseña) (CON SUBEVENTO)
    public boolean validarEmpleado(Empleados e){
        Query consulta = sesion.createQuery("select e from Empleados e where username = '"+e.getUsername()+"' AND password = '"+e.getPassword()+"'");
        //Empleados elEmpleado = (Empleados) sesion.get(Empleados.class, e.getUsername());
        //Mira en la tabla empleados si existe un empleado con el username que le estamos pasando
        if (consulta != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
            Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
            String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
            transaccion = sesion.beginTransaction();
            Historial h = new Historial();
            h.setFechaHora(fechaHoraenTexto);
            h.setEmpleados(e);
            h.setTipoEvento("I");
            sesion.save(h);
            transaccion.commit();
            System.out.println("--Se ha insertado en el historial ESTA validación del usuario: '"+e.getUsername()+"'--");
            return true;
            
        } else {
            return false;
        }
        
    }
    
    //8A. Obtener un objeto incidencia a partir de su ID
    public Incidencias getIncidenciaByID (Incidencias i){
        Incidencias laIncidencia = (Incidencias) sesion.get(Incidencias.class, i.getIdIncidencia());
        return laIncidencia;
    }
    
    //8B. Obtener la lista de todas las incidencias.
    public List<Incidencias> getAllIncidencias(){
        Query consulta = sesion.createQuery("select i from Incidencias i");
        return consulta.list();
    }
    
    //8C. Insertar una incidencia a partir de un objeto de clase Incidencia (INCLUIDO SUBEVENTO)
    public void insertarIncidencia (Incidencias i){
        try{ //Cazamos la runtime exception para no verla cuando ejecutemos el proyecto
            transaccion = sesion.beginTransaction();//Siempre se trabaja con transacciones
            sesion.save(i);//Insertamos la incidencia
            transaccion.commit();; //Hacemos el commit
            //Si tipo es urgente meterlo en historial
            if (i.getTipo()=="Urgente"){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
                Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
                String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
                transaccion = sesion.beginTransaction();
                Historial h = new Historial();
                h.setFechaHora(fechaHoraenTexto);
                h.setEmpleados(i.getEmpleadosByOrigen());
                h.setTipoEvento("U");
                sesion.save(h);
                transaccion.commit();
                System.out.println("Se ha insertado en el historial ESTA incidencia URGENTE");
            }
        } catch (ConstraintViolationException ex){ //Indicamos el único exception que nos puede dar
            transaccion.rollback();
            throw ex; //La lanzamos y la cogemos de nuevo en el main
            
        }
        
    }
    
    //8D. Obtener las incidencias PARA un empleado a partir de un objeto de clase Empleado (INCLUIDO SUBEVENTO)
     public List<Incidencias> getAllIncidenciasparaunEmpleadoapartirdeEmpleado (Empleados e){
            Query consulta = sesion.createQuery("select i from Incidencias i where empleadosByDestino.username = '"+e.getUsername()+"'");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
            Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
            String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
            transaccion = sesion.beginTransaction();
            Historial h = new Historial();
            h.setFechaHora(fechaHoraenTexto);
            h.setEmpleados(e);
            h.setTipoEvento("C");
            sesion.save(h);
            transaccion.commit();
            System.out.println("--Se ha insertado en el historial ESTA consulta de las Incidencias PARA '"+e.getUsername()+"'--");
         return consulta.list();
         
     }
    
    
    //8E. Obtener las incidencias creadas por un empleado concreto.
    public List<Incidencias> getIncidenciaByOrigen (Empleados e){
        Query consulta = sesion.createQuery("select i from Incidencias i where empleadosByOrigen.username = '"+e.getUsername()+"'"); 
        return consulta.list();
    }
    
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
    
    //9B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto.
    public List<Historial> getFechaHoraUltimoLogin (Empleados e) {
        Query consulta = sesion.createQuery("SELECT h FROM Historial h WHERE tipo_evento = 'I' AND username='"+e.getUsername()+"' ORDER BY fechaHora DESC");
        return consulta.list();
    }    
  
    //9C. Obtener el ranking de los empleados por cantidad de incidencias urgentes creadas (más incidencias primero).
    public List<Historial> getRankingUrgenciasGroupByUsername (){
        Query consulta = sesion.createQuery("select empleados.username, COUNT(h.idIncidencia) FROM Historial h WHERE tipo_evento = 'U' group by empleados.username");
        return consulta.list();
    }
    
    public void close(){
        sesion.close(); //Cierra la sesión
        HibernateUtil.destroy();/*Llama al método destroy para de cerrar el servicio
        de conexiones*/
        
    }
    
}
