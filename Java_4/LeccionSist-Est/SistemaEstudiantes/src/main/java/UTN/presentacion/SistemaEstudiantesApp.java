package UTN.presentacion;

import UTN.datos.EstudianteDAO;
import UTN.dominio.Estudiante;

import java.sql.SQLOutput;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in); // Para leer datos de la consola
        // Se crea una instacia de la clase Servicio, esto lo hacemos fuera del ciclo
        var estudianteDao = new EstudianteDAO();// Esta instancia debe hacerse una sola vez
        while (!salir){
            try {
                mostrarMenu();// Mostramos el menu
                //Este sera el metodo ejecutarOpciones que devolvera un booleano
                salir = ejecutarOpcion(consola, estudianteDao);// Este arroja una excepcion
            } catch (Exception e) {
                System.out.println("Ocurrio un error al ejecutar la opcion: " + e.getMessage());
            }
        }// Fin while
    } // Fin metodo main

    private static void mostrarMenu(){
        System.out.print("""
                *******Sistema de Estudiantes*******
                1. Listar estudiantes
                2. Buscar estudiante
                3. Agregar estudiante
                4. Modificar estudiante
                5. Eliminar estudiante
                6. Salir
                Ingrese una opcion:
                """);
    } // Fin metodo mostrarMenu

    // Metodo para ejecutar las opciones del menu, va a devolver un booleano ya que la opcion 5 es salir
        //puede modificar el valor de la varible salir, si es verdadero sale del ciclo while
    private static boolean ejecutarOpcion(Scanner consola, EstudianteDAO estudianteDao) throws Exception {
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("Listado de estudiantes...");
                //nos muestra la informacion de los estudiantes, solo recupera info y regresa a la lista
                var estudiantes = estudianteDao.listarEstudiantes(); //Recibe el listado
                //vamos a iterar cada obejto de tipo estudiante
                estudiantes.forEach(System.out::println); //Imprime cada estudiante (lista)
            }// Fin caso 1
            case 2 -> {// Buscar estudiante por id
                System.out.println("Ingrese el id del estudiante a buscar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDao.buscarEstudiantePorId(estudiante);
                if (encontrado)
                    System.out.println("Estudiante encontrado: " + estudiante);
                else
                    System.out.println("No se ha encontrado estudiante: " + estudiante);
            }// Fin caso 2
            case 3 -> {// Agregar estudiante
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();
                //crea el objeto estudiante sin id
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDao.agregarEstudiante(estudiante);
                if (agregado)
                    System.out.println("Estudiante agregado: " + estudiante);
                else
                    System.out.println("No se ha podido agregar estudiante: " + estudiante);
            }// Fin caso 3
            case 4 -> {// Modificar estudiante
                System.out.println("Modificar estudiante...");
                //Aqui lo primero es especificar el id del estudiante(objeto) a modificar
                System.out.println("Ingrese el id del estudiante a modificar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Email: ");
                var email = consola.nextLine();
                //crea el objeto estudiante a modificar
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDao.modificarEstudiante(estudiante);
                if (modificado)
                    System.out.println("Estudiante modificado: " + estudiante);
                else
                    System.out.println("No se ha podido modificar estudiante: " + estudiante);
            }// Fin caso 4
            case 5 ->{// Eliminar estudiante
                System.out.println("Eliminar estudiante...");
                //Aqui lo primero es especificar el id del estudiante(objeto) a eliminar
                System.out.println("Ingrese el id del estudiante a eliminar:");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                //crea el objeto estudiante a eliminar
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDao.eliminarEstudiante(estudiante);
                if (eliminado)
                    System.out.println("Estudiante eliminado: " + estudiante);
                else
                    System.out.println("No se ha podido eliminar estudiante: " + estudiante);
            }// Fin caso 5
            case 6 ->{// Salir
                System.out.println("Saliendo del sistema...");
                salir = true;
            }// Fin caso 6
            default -> System.out.println("Opcion no valida, ingrese otra opcion");
        }// Fin switch
        return salir;
    } // Fin metodo ejecutarOpcion

} // Fin clase Main