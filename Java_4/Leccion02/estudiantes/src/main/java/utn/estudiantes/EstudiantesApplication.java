package utn.estudiantes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.estudiantes.servicio.EstudianteServicio;
import utn.estudiantes.modelo.Estudiantes2022;

import java.util.List;
import java.util.Scanner;
@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {
	@Autowired
	private EstudianteServicio estudianteServicio;
	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);

	String nl = System.lineSeparator();
	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");
		//Levantar la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicación Finalizada!");
	}
	@Override
	public void run(String... args) throws Exception {
		logger.info(nl+"Ejecutando el metodo run de Spring Boot..."+nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}//fin while


	}
	public void mostrarMenu(){
		//logger.info(nl);
		logger.info("""
        ******* Sistema de Estudiantes *******
     	1) Listar Estudiantes
     	2) Buscar Estudiante
     	3) Agregar Estudiante
     	4) Modificar Estudiante
     	5) Eliminar Estudiante
     	6) Salir
	 	Elija una opción: """);


	}

	private boolean ejecutarOpciones(Scanner consola){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1 ->{
				logger.info("Listando Estudiantes: "+nl);
				List<Estudiantes2022> estudiantes = estudianteServicio.listarEstudiantes();
				estudiantes.forEach(estudiante -> logger.info(estudiante.toString()+nl));
			}

			case 2 ->{//buscar estudiante
				logger.info("Ingrese el ID del estudiante a buscar: ");
				var idEstidiante = Integer.parseInt(consola.nextLine());
				Estudiantes2022 estudiante = estudianteServicio.buscarEstudiantePorID(idEstidiante);
				if (estudiante != null)
					logger.info("Estudiante encontrado: "+ estudiante +nl);
				else
					logger.info("Estudiante no encontrado: " + idEstidiante +nl);
			}
			case 3 ->{//Agregar estudiante
				logger.info("Agregar estudiante: "+nl);
				logger.info("Ingrese el nombre del estudiante: ");
				var nombre = consola.nextLine();
				logger.info("Ingrese el apellido del estudiante: ");
				var apellido = consola.nextLine();
				logger.info("Ingrese el telefono del estudiante: ");
				var telefono = consola.nextLine();
				logger.info("Ingrese el email del estudiante: ");
				var email = consola.nextLine();
				//creamos el objeto estudiante
				var estudiante = new Estudiantes2022();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteServicio.guardarEstudiante(estudiante);
				logger.info("Estudiante guardado con exito: "+ estudiante +nl);
			}
			case 4 ->{//Modificar estudiante
				logger.info("Modificar estudiante: "+nl);
				logger.info("Ingrese el ID del estudiante a modificar: ");
				var idEstidiante = Integer.parseInt(consola.nextLine());
				//buscamos el estudiante a modificar
				Estudiantes2022 estudiante = estudianteServicio.buscarEstudiantePorID(idEstidiante);
				if (estudiante != null){
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Telefono: ");
					var telefono = consola.nextLine();
					logger.info("Email: ");
					var email = consola.nextLine();
					//modificamos el objeto estudiante
					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteServicio.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado: "+ estudiante +nl);
				}
				else
					logger.info("Estudiante no encontrado: " +idEstidiante +nl);
			}
			case 5->{//Eliminar estudiante
				logger.info("Eliminar estudiante: "+nl);
				logger.info("Ingrese el ID del estudiante a eliminar: ");
				var idEstidiante = Integer.parseInt(consola.nextLine());
				//Buscamos el id del estudiante a eliminar
				var estudiante = estudianteServicio.buscarEstudiantePorID(idEstidiante);
				if (estudiante != null){
					estudianteServicio.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado: "+ estudiante +nl);
				}
				else
					logger.info("Estudiante no encontrado: " +idEstidiante +nl);

			}
			case 6 ->{
				logger.info("Hasta pronto!!"+nl+nl);
				salir = true;
			}
			default -> logger.info("Opción no valida, intente nuevamente: "+opcion+nl);
		}//fin switch
		return salir;
	}
}
