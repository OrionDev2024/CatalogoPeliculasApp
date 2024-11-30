package presentacion;

import dominio.Pelicula;
import servicio.IServicioPeliculas;
import servicio.ServicioPeliculasLista;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CatalogoPeliculasApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        //agregamos la implementacion del servicio
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();
        while (!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(consola, servicioPeliculas);
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
        }//fin de ciclo while
    }//fin de main

    private static void mostrarMenu(){
        System.out.println("""
                *** Catálogo de Películas ***
                1. Agregar Película.
                2. Listar Películas.
                3. Buscar Película.
                4. Salir.
                """);
    }// fin funcion mostrarmenu
    private static boolean ejecutarOpciones(Scanner consola, IServicioPeliculas servicioPeliculas){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 ->{
                System.out.println("Introduce el nombre de la película: ");
                var nombrePelicula = consola.nextLine();
                servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));
            }
            case 2 -> servicioPeliculas.listarPeliculas();
            case 3 -> {
                System.out.println("Introduce la película a buscar: ");
                var buscar = consola.nextLine();
                servicioPeliculas.buscarPelicula(new Pelicula(buscar));
            }
            case 4 -> {
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opción no reconocida: " + opcion);
        }
        return salir;
    }//fin funcion ejecutarOperaciones
}