import java.time.Year;
import java.util.ArrayList;

public class GestorPeliculas {
    public static final String[] GENEROS = {"Drama", "Aventura", "Comedia", "Animación", "Documental"};
    public static final int ANIO_MINIMO = 1930;

    private final ArrayList<Pelicula> peliculas = new ArrayList<>();
    private boolean cargaRealizada = false;

    public void precargarPeliculas() {
        if (cargaRealizada) {
            return;
        }
        cargaRealizada = true;
        peliculas.add(new Pelicula(2007, "Toy Story", "John Lasseter", "Animación", 1995, 12));
        peliculas.add(new Pelicula(2013, "El Rey León", "Roger Allers", "Animación", 1994, 9));
        peliculas.add(new Pelicula(2002, "Parásitos", "Bong Joon-ho", "Drama", 2019, 15));
        peliculas.add(new Pelicula(2019, "Interestelar", "Christopher Nolan", "Aventura", 2014, 11));
        peliculas.add(new Pelicula(2005, "El origen", "Christopher Nolan", "Aventura", 2010, 8));
        peliculas.add(new Pelicula(2011, "Coco", "Lee Unkrich", "Animación", 2017, 9));
        peliculas.add(new Pelicula(2016, "La vida es bella", "Roberto Benigni", "Comedia", 1997, 7));
        peliculas.add(new Pelicula(2001, "El Padrino", "Francis Ford Coppola", "Drama", 1972, 14));
        peliculas.add(new Pelicula(2018, "Tiempos modernos", "Charles Chaplin", "Comedia", 1936, 5));
        peliculas.add(new Pelicula(2004, "Bowling for Columbine", "Michael Moore", "Documental", 2002, 4));
        peliculas.add(new Pelicula(2015, "La marcha de los pingüinos", "Luc Jacquet", "Documental", 2005, 6));
        peliculas.add(new Pelicula(2008, "En busca del arca perdida", "Steven Spielberg", "Aventura", 1981, 10));
        peliculas.add(new Pelicula(2012, "Jurassic Park", "Steven Spielberg", "Aventura", 1993, 10));
        peliculas.add(new Pelicula(2003, "Cuando Harry conoció a Sally", "Rob Reiner", "Comedia", 1989, 3));
        peliculas.add(new Pelicula(2020, "El viaje de Chihiro", "Hayao Miyazaki", "Animación", 2001, 13));
        peliculas.add(new Pelicula(2006, "Shrek", "Andrew Adamson", "Animación", 2001, 2));
        peliculas.add(new Pelicula(2010, "Cadena perpetua", "Frank Darabont", "Drama", 1994, 12));
        peliculas.add(new Pelicula(2017, "Ciudadano Kane", "Orson Welles", "Drama", 1941, 1));
        peliculas.add(new Pelicula(2014, "El gran dictador", "Charles Chaplin", "Comedia", 1940, 0));
        peliculas.add(new Pelicula(2009, "Apollo 11", "Todd Douglas Miller", "Documental", 2019, 3));
        BusquedaOrdenamiento.ordenarPorNumeroDescendente(peliculas);
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public int cantidad() {
        return peliculas.size();
    }

    public void registrar(String codigoTxt, String titulo, String director, String genero, String anioTxt) {
        int numero = validarNumero(codigoTxt);
        String tituloValido = validarTexto(titulo, "El título");
        String directorValido = validarTexto(director, "El director");
        String generoValido = validarGenero(genero);
        int anio = validarAnio(anioTxt);
        if (existeNumero(numero)) {
            throw new IllegalArgumentException("Ya existe una película con el código " + numero + ".");
        }
        insertarOrdenado(new Pelicula(numero, tituloValido, directorValido, generoValido, anio));
    }

    private boolean existeNumero(int numero) {
        for (Pelicula p : peliculas) {
            if (p.getNumeroID() == numero) {
                return true;
            }
        }
        return false;
    }

    private void insertarOrdenado(Pelicula nueva) {
        int posicion = 0;
        while (posicion < peliculas.size() && peliculas.get(posicion).getNumeroID() > nueva.getNumeroID()) {
            posicion++;
        }
        peliculas.add(posicion, nueva);
    }

    public static int validarNumero(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El código es obligatorio.");
        }
        int numero;
        try {
            numero = Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El código debe ser un número entero.");
        }
        if (numero <= 0) {
            throw new IllegalArgumentException("El código debe ser positivo.");
        }
        return numero;
    }

    public static String validarTexto(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
        return texto.trim();
    }

    public static String validarGenero(String genero) {
        if (genero == null) {
            throw new IllegalArgumentException("Seleccione un género.");
        }
        for (String g : GENEROS) {
            if (g.equals(genero)) {
                return g;
            }
        }
        throw new IllegalArgumentException("El género no es válido.");
    }

    public static int validarAnio(String texto) {
        int actual = Year.now().getValue();
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El año es obligatorio.");
        }
        int anio;
        try {
            anio = Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El año debe ser un número entero.");
        }
        if (anio < ANIO_MINIMO || anio > actual) {
            throw new IllegalArgumentException("El año debe estar entre " + ANIO_MINIMO + " y " + actual + ".");
        }
        return anio;
    }
}
