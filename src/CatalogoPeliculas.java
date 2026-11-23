import java.util.ArrayList;

public class CatalogoPeliculas {
    public static final String[] generos = {"Drama", "Aventura", "Comedia", "Animación", "Documental"};
    public static final int aniomin = 1930;
    private ArrayList<Pelicula> peliculas = new ArrayList<>();

    public CatalogoPeliculas() {
        cargarPeliculasIniciales();
    }

    private void cargarPeliculasIniciales() {

        peliculas.add(new Pelicula(1001, "El Viaje de Chihiro", "Hayao Miyazaki", "Animación", 2001, 5));
        peliculas.add(new Pelicula(1002, "Toy Story", "John Lasseter", "Animación", 1995, 3));
        peliculas.add(new Pelicula(1003, "Parásitos", "Bong Joon-ho", "Drama", 2019, 7));
        peliculas.add(new Pelicula(1004, "El Padrino", "Francis Ford Coppola", "Drama", 1972, 9));
        peliculas.add(new Pelicula(1005, "Indiana Jones", "Steven Spielberg", "Aventura", 1981, 4));
        peliculas.add(new Pelicula(1006, "Jurassic Park", "Steven Spielberg", "Aventura", 1993, 6));
        peliculas.add(new Pelicula(1007, "Mi Vecino Totoro", "Hayao Miyazaki", "Animación", 1988, 5));
        peliculas.add(new Pelicula(1008, "La Vida es Bella", "Roberto Benigni", "Drama", 1997, 2));
        peliculas.add(new Pelicula(1009, "Bowling for Columbine", "Michael Moore", "Documental", 2002, 1));
        peliculas.add(new Pelicula(1010, "Cinema Paradiso", "Giuseppe Tornatore", "Drama", 1988, 8));
        peliculas.add(new Pelicula(1011, "Una Noche en el Museo", "Shawn Levy", "Comedia", 2006, 0));
        peliculas.add(new Pelicula(1012, "Dos Tontos Muy Tontos", "Peter Farrelly", "Comedia", 1994, 2));
        peliculas.add(new Pelicula(1013, "Free Solo", "Jimmy Chin", "Documental", 2018, 6));
        peliculas.add(new Pelicula(1014, "Coco", "Lee Unkrich", "Animación", 2017, 9));
        peliculas.add(new Pelicula(1015, "Mad Max: Furia en el Camino", "George Miller", "Aventura", 2015, 3));
        peliculas.add(new Pelicula(1016, "Super Size Me", "Morgan Spurlock", "Documental", 2004, 0));
        peliculas.add(new Pelicula(1017, "Ratatouille", "Brad Bird", "Animación", 2007, 5));
        peliculas.add(new Pelicula(1018, "El Gran Hotel Budapest", "Wes Anderson", "Comedia", 2014, 4));
        peliculas.add(new Pelicula(1019, "Roma", "Alfonso Cuarón", "Drama", 2018, 7));
        peliculas.add(new Pelicula(1020, "Mi Pobre Angelito", "Chris Columbus", "Comedia", 1990, 1));

        ordenarPorNumeroDescendente();
    }

    public int cantidad() {
        return peliculas.size();
    }

    public ArrayList<Pelicula> listar() {

        ArrayList<Pelicula> copia = new ArrayList<>();

        for (int i = 0; i < peliculas.size(); i++) {
            copia.add(peliculas.get(i));
        }

        return copia;
    }

    public int anioActual() {
        return 2026;
    }

    public void ordenarPorNumeroDescendente() {
        for (int i = 0; i < peliculas.size() - 1; i++) {

            for (int j = 0; j < peliculas.size() - 1 - i; j++) {

                if (peliculas.get(j).getNumeroID() <
                        peliculas.get(j + 1).getNumeroID()) {

                    Pelicula aux = peliculas.get(j);

                    peliculas.set(j, peliculas.get(j + 1));

                    peliculas.set(j + 1, aux);
                }
            }
        }
    }

    public int busquedaBinaria(int numero) {

        if (numero <= 0) {
            throw new IllegalArgumentException("El codigo debe ser positivo.");
        }

        int inicio = 0;
        int fin = peliculas.size() - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int actual = peliculas.get(medio).getNumeroID();

            if (actual == numero) {
                return medio;
            }

            if (actual > numero) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1;
    }

    public Pelicula obtenerPorNumero(int numero) {
        int indice = busquedaBinaria(numero);
        if (indice == -1) {
            return null;
        }

        return peliculas.get(indice);
    }

    public int buscarIndicePorTexto(String codigoTxt) {
        int codigo;

        try {
            codigo = Integer.parseInt(codigoTxt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El codigo debe ser un numero entero.");
        }
        return busquedaBinaria(codigo);
    }

    public void registrar(String codigoTxt, String titulo, String director, String genero, String anioTxt) {
        int codigo;
        int anio;

        try {
            codigo = Integer.parseInt(codigoTxt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El codigo debe ser un numero entero.");
        }
        if (codigo <= 0) {
            throw new IllegalArgumentException("El codigo debe ser positivo.");
        }

        try {
            anio = Integer.parseInt(anioTxt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El año debe ser un numero entero.");
        }

        validarDatos(titulo, director, genero, anio);

        if (busquedaBinaria(codigo) != -1) {
            throw new IllegalArgumentException("El codigo ya existe.");
        }

        Pelicula nueva = new Pelicula(
                codigo, titulo.trim(), director.trim(), genero, anio
        );

        peliculas.add(nueva);

        ordenarPorNumeroDescendente();
    }
    public void editar(int numero, String titulo, String director, String genero, String anioTxt) {
        int indice = busquedaBinaria(numero);

        if (indice == -1) {
            throw new IllegalArgumentException("La pelicula no existe.");
        }

        int anio;

        try {
            anio = Integer.parseInt(anioTxt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El año debe ser un numero entero.");
        }
        validarDatos(titulo, director, genero, anio);
        Pelicula p = peliculas.get(indice);
        p.setTitulo(titulo.trim());
        p.setDirector(director.trim());
        p.setGenero(genero);
        p.setAnio(anio);
    }
    public void eliminar(int numero) {
        int indice = busquedaBinaria(numero);

        if (indice == -1) {
            throw new IllegalArgumentException("La pelicula no existe.");
        }

        peliculas.remove(indice);
    }
    public int darMeGusta(int numero) {
        Pelicula p = obtenerPorNumero(numero);

        if (p == null) {
            throw new IllegalArgumentException("No hay una pelicula seleccionada.");
        }

        p.incrementarMeGusta();

        return p.getMeGusta();
    }
    public ArrayList<Pelicula> buscarPorTexto(String consulta) {

        if (consulta == null || consulta.trim().isEmpty()) {
            throw new IllegalArgumentException("Escriba un texto para buscar.");
        }

        ArrayList<Pelicula> resultados = new ArrayList<>();

        for (int i = 0; i < peliculas.size(); i++) {

            Pelicula p = peliculas.get(i);

            if (p.getTitulo().contains(consulta) ||
                    p.getDirector().contains(consulta)) {
                resultados.add(p);
            }
        }

        return resultados;
    }
    public ArrayList<Pelicula> ranking() {
        ArrayList<Pelicula> copia = listar();

        for (int i = 0; i < copia.size() - 1; i++) {

            for (int j = 0; j < copia.size() - 1 - i; j++) {

                if (copia.get(j).getMeGusta() < copia.get(j + 1).getMeGusta()) {

                    Pelicula aux = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, aux);
                }
            }
        }
        return copia;
    }
    private void validarDatos(String titulo, String director, String genero, int anio) {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El titulo es obligatorio.");
        }
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("El director es obligatorio.");
        }
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("Seleccione un genero.");
        }
        boolean generoValido = false;
        for (int i = 0; i < generos.length; i++) {
            if (generos[i].equals(genero)) {
                generoValido = true;
            }
        }
        if (!generoValido) {
            throw new IllegalArgumentException("Seleccione un genero valido.");
        }
        if (anio < aniomin || anio > anioActual()) {
            throw new IllegalArgumentException(
                    "El año debe estar entre 1930 y " + anioActual()
            );
        }
    }
}
