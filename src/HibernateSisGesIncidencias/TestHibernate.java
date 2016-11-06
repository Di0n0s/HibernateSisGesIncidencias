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
        
//        //7C. Modificar el perfil de un empleado existente (FUNCIONA)
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
        
//        //7B. Validar la entrada de un empleado (suministrando usuario y contraseña) (FUNCIONA CON SUBEVENTO)
//        Empleados e = new Empleados("cnavarro", "mav164");
//        System.out.println("Validando usuario...");
//        if(miGestor.validarEmpleado(e)){
//            System.out.println("Usuario validado!");
//        } else{
//            System.out.println("El usuario NO existe");
//        }
        
//        //8E. Obtener las incidencias creadas por un empleado concreto.
//        Empleados e = new Empleados("amartinez");
//        System.out.println("Consultas de Incidencias del usuario "+e.getUsername());
//        List<Incidencias> incidencias = miGestor.getIncidenciaByOrigen(e);
//        for (Incidencias i : incidencias){
//            System.out.println(i);
//        }
        
//        //8A. Obtener un objeto incidencia a partir de su ID (FUNCIONA)
//        Incidencias i = new Incidencias (14);
//        System.out.println("Consultar la incidencia para la ID: "+i.getIdIncidencia());
//        Incidencias consultada = miGestor.getIncidenciaByID(i);
//        System.out.println(consultada);

//        //8B. Obtener la lista de todas las incidencias (FUNCIONA)
//        System.out.println("Consulta de TODAS las Incidencias");
//        List<Incidencias> incidencias = miGestor.getAllIncidencias();
//        for (Incidencias incidenciaActual : incidencias){
//            System.out.println(incidenciaActual);
//        }
        
//        //8C. Insertar una incidencia a partir de un objeto de clase Incidencia (FUNCIONA CON EVENTO)
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss:SS"); //Indicamos como queremos que se muestre la fecha y hora
//        Date ahoramismo = new Date(); //Creamos un new Date para la fecha y hora actual
//        String fechaHoraenTexto = sdf.format(ahoramismo); //Creamos un string indicando al sdf que mofique el formato del dato
//        System.out.println(fechaHoraenTexto); //Lo imprimimos por pantalla 
//        Empleados e1 = new Empleados("cnavarro", "pqb937", "Sergio Fernández Moreno", "+34684200587");
//        Empleados e2 = new Empleados("sfernandez");
//        Incidencias i = new Incidencias (e1, e2, fechaHoraenTexto, "Urgente", "El ranking me está volviendo loco");
//        System.out.println("Insertando incidencia...");
//        miGestor.insertarIncidencia(i);
//        System.out.println("Incidencia insertada!"); 
//        System.out.println(i);
        
//        //8D. Obtener las incidencias creadas por un empleado concreto (FUNCIONA CON SUBEVENTO)
//        Empleados e = new Empleados("amartinez");
//        System.out.println("Consultas de Incidencias PARA el usuario "+e.getUsername());
//        List<Incidencias> incidencias = miGestor.getAllIncidenciasparaunEmpleadoapartirdeEmpleado(e);
//        for (Incidencias incidenciaActual : incidencias){
//            System.out.println(incidenciaActual);
//        }
        
//        //9B. Obtener la fecha-hora del último inicio de sesión para un empleado concreto. (FUNCIONA)
//        Empleados e = new Empleados ("jperez");
//        System.out.println("Consultar el último login del empleado "+e.getUsername());
//        List<Historial> LoginUsuario = miGestor.getFechaHoraUltimoLogin(e);
//        System.out.println(LoginUsuario.get(0)); //Tras ordenar los login y meterlos en el ArrayList
//        //imprimo el último login que es la primera posicion del ArrayList.
      
        
//        //9C. Obtener el ranking de los empleados por cantidad de incidencias urgentes creadas (más incidencias primero)(NO FUNCIONA)
//        System.out.println("Listado de Ranking de Urgencias por Usuario");
//        List<Historial> RankingUrgencias = miGestor.getRankingUrgenciasGroupByUsername();
//        for (Historial urgenciaActual : RankingUrgencias){
//            System.out.println(urgenciaActual);
//        }
        
        //Cerramos
        miGestor.close();
        
    } 
    
}
