import java.util.ArrayList;
import java.util.Collections;

public class BusquedaOrdenamiento {

    public static void ordenarPorNumeroDescendente(ArrayList<Pelicula> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (lista.get(j).getNumeroID() < lista.get(j + 1).getNumeroID()) {
                    Collections.swap(lista, j, j + 1);
                }
            }
        }
    }

    public static ArrayList<Pelicula> buscarTexto(ArrayList<Pelicula> lista, String texto) {
        ArrayList<Pelicula> resultados = new ArrayList<>();
        for (Pelicula p : lista) {
            if (p.getTitulo().contains(texto) || p.getDirector().contains(texto)) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    public static int busquedaBinaria(ArrayList<Pelicula> lista, int numero) {
        int inicio = 0;
        int fin = lista.size() - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int actual = lista.get(medio).getNumeroID();
            if (actual == numero) {
                return medio;
            }
            if (actual < numero) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }
        return -1;
    }

}