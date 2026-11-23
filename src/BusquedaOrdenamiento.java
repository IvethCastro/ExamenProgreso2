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
}