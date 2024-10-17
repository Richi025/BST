package escuelaing.edu.bst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BstApplication {

    public static void main(String[] args) {
        SpringApplication.run(BstApplication.class, args);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Insertar elementos en el árbol binario de búsqueda
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);

        System.out.println("Recorrido en orden:");
        for (Integer value : bst) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Prueba de búsqueda de elementos
        System.out.println("Buscar 40: " + bst.contains(40));  
        System.out.println("Buscar 90: " + bst.contains(90));  

        // Prueba de eliminación de un nodo sin hijos (hoja)
        System.out.println("Eliminar 20 (hoja):");
        bst.remove((Integer) 20); 
        for (Integer value : bst) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Prueba de eliminación de un nodo con un solo hijo
        System.out.println("Eliminar 30 (con un hijo):");
        bst.remove((Integer) 30); 
        for (Integer value : bst) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Prueba de eliminación de un nodo con dos hijos
        System.out.println("Eliminar 50 (con dos hijos):");
        bst.remove((Integer) 50); 
        for (Integer value : bst) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Imprimir el número de nodos restantes en el árbol
        System.out.println("Número de nodos restantes: " + bst.size());

        // Buscar el mínimo y el máximo valor en el árbol después de las eliminaciones
        System.out.println("Mínimo valor en el árbol: " + bst.get(0));  // En el recorrido en orden, el primero es el menor
        System.out.println("Máximo valor en el árbol: " + bst.get(bst.size() - 1));  // El último es el mayor

        // Prueba del método de limpiar el árbol
        System.out.println("Limpiar el árbol...");
        bst.clear();
        System.out.println("Árbol vacío: " + bst.isEmpty());  // Esperado: true

        // Insertar nuevos elementos para mostrar que se puede reutilizar después de limpiar
        bst.add(100);
        bst.add(90);
        bst.add(110);

        System.out.println("Nuevo recorrido después de limpiar e insertar nuevos valores:");
        for (Integer value : bst) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Altura del árbol (aquí contamos nodos como proxy de altura)
        System.out.println("Altura del árbol (número de nodos): " + bst.size());

        // Prueba del índice
        System.out.println("Índice del valor 100: " + bst.indexOf(100));  // Esperado: 1
        System.out.println("Índice del valor 110: " + bst.indexOf(110));  // Esperado: 2
    }
}
