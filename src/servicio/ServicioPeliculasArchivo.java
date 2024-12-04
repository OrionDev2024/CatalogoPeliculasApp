package servicio;

import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas{

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo(){
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //si ya existe el archivo, NO se vuelve a crear
            if(archivo.exists()){
                System.out.println("El archivo ya existe!");
            }else {
                //si no existe, se crea vacío
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al abrir el archivo: " + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        //volvemos a abrir el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            System.out.println("Listado de películas");
            //Abrimos el archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            //Leemos línea a línea el archivo
            String linea;
            linea = entrada.readLine();
            //leemos todas la líneas
            while (linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                //Antes de terminar el ciclo volvemos a leer la siguiente linea
                linea = entrada.readLine();
            }//fin de while
            entrada.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error al leer el archivo" + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {

    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {

    }
}
