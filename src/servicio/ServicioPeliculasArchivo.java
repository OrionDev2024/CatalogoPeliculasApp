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
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            //Agregamos la pelicula (toString)
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agrego al archivo: " + pelicula);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al agregar películoa" + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            //Abrimos el archivo para lectura línea a línea.
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var peliculaBuscar = pelicula.getNombre();
            while (lineaTexto != null){
                //Buscamos sin importar mayusculas/minusculas
                if (peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrada = true;
                    break;
                }
                //Leemos la siguiente línea antes de la siguiente iteración
                lineaTexto = entrada.readLine();
                indice++;
            }//fin de while
            //Imprimimos los resultados de la busqueda
            if (encontrada){
                System.out.println("Película " + lineaTexto + " encontrada - líena " + indice);
            }else {
                System.out.println("No se ha encontrado la película: " + pelicula.getNombre());
                entrada.close();
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al buscar en el archivo: " + e.getMessage());
        }
    }
}
