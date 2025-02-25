package Puzzle;

import java.util.*;

import java.util.*;

public class Puzzle {

    public int dimension = 3;  // Tablero (3x3)

    // Movimientos posibles Abajo, Izquierda, Arriba, Derecha
    int[] fila = {1, 0, -1, 0};
    int[] columna = {0, -1, 0, 1};

    // Búsqueda por Profundidad
    public void depthFirstSearch(int[][] inicial, int[][] objetivo, int x, int y) {
        Stack<Nodo> stack = new Stack<>();  // Pila para almacenar los nodos
        Set<String> visitados = new HashSet<>();  // Conjunto para evitar visitar estados repetidos
        Nodo raiz = new Nodo(inicial, x, y, x, y, 0, null);  // Estado inicial
        stack.push(raiz);  // Insertar la raíz en la pila
        visitados.add(Arrays.deepToString(inicial));  // Guardar 

        while (!stack.isEmpty()) {
            Nodo nodo = stack.pop();  // Sacar el nodo actual de la pila
            if (calcularCosto(nodo.matriz, objetivo) == 0) {
                imprimirCamino(nodo);  
                System.out.println("Solución encontrada en " + nodo.nivel + " movimientos!");
                return;
            }
            // Probar los 4 movimientos posibles
            for (int i = 0; i < 4; i++) { 
                int nuevoX = nodo.x + fila[i];
                int nuevoY = nodo.y + columna[i];
                if (esSeguro(nuevoX, nuevoY)) {  // Verificar si la nueva posición es válida
                    Nodo hijo = new Nodo(nodo.matriz, nodo.x, nodo.y, nuevoX, nuevoY, nodo.nivel + 1, nodo);
                    String estado = Arrays.deepToString(hijo.matriz);
                    if (!visitados.contains(estado)) {  
                        stack.push(hijo);  // Agregar el nuevo nodo a la pila
                        visitados.add(estado);  
                    }
                }
            }
        }
        System.out.println("No se encontró una solución.");
    }

    // Búsqueda por Anchura
    public void breadthFirstSearch(int[][] inicial, int[][] objetivo, int x, int y) {
        Queue<Nodo> queue = new LinkedList<>();  
        Set<String> visitados = new HashSet<>();  // Evitar estados repetidos
        Nodo raiz = new Nodo(inicial, x, y, x, y, 0, null);
        queue.add(raiz);
        visitados.add(Arrays.deepToString(inicial));

        while (!queue.isEmpty()) {
            Nodo nodo = queue.poll();  // Sacar el primer nodo de la cola
            if (calcularCosto(nodo.matriz, objetivo) == 0) {
                imprimirCamino(nodo);  
                System.out.println("Solución encontrada en " + nodo.nivel + " movimientos!");
                return;
            }
            for (int i = 0; i < 4; i++) {  // Probar los 4 movimientos posibles
                int nuevoX = nodo.x + fila[i];
                int nuevoY = nodo.y + columna[i];
                if (esSeguro(nuevoX, nuevoY)) {
                    Nodo hijo = new Nodo(nodo.matriz, nodo.x, nodo.y, nuevoX, nuevoY, nodo.nivel + 1, nodo);
                    String estado = Arrays.deepToString(hijo.matriz);
                    if (!visitados.contains(estado)) {
                        queue.add(hijo);  // Agregar el nuevo nodo a la cola
                        visitados.add(estado); 
                    }
                }
            }
        }
        System.out.println("No se encontró una solución.");
    }

    // Costo Uniforme
    public void uniformCostSearch(int[][] inicial, int[][] objetivo, int x, int y) {
        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.nivel));  
        Set<String> visitados = new HashSet<>();
        Nodo raiz = new Nodo(inicial, x, y, x, y, 0, null);
        pq.add(raiz);
        visitados.add(Arrays.deepToString(inicial));

        while (!pq.isEmpty()) {
            Nodo nodo = pq.poll();  // Sacar el nodo con menor costo
            if (calcularCosto(nodo.matriz, objetivo) == 0) {
                imprimirCamino(nodo); 
                System.out.println("Solución encontrada en " + nodo.nivel + " movimientos!");
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nuevoX = nodo.x + fila[i];
                int nuevoY = nodo.y + columna[i];
                if (esSeguro(nuevoX, nuevoY)) {
                    Nodo hijo = new Nodo(nodo.matriz, nodo.x, nodo.y, nuevoX, nuevoY, nodo.nivel + 1, nodo);
                    String estado = Arrays.deepToString(hijo.matriz);
                    if (!visitados.contains(estado)) {
                        pq.add(hijo);  // Agregar el nuevo nodo a la cola de prioridad
                        visitados.add(estado);  
                    }
                }
            }
        }
        System.out.println("No se encontró una solución.");
    }
    public boolean esSeguro(int x, int y) {
        return (x >= 0 && x < dimension && y >= 0 && y < dimension);
    }
    public int calcularCosto(int[][] inicial, int[][] objetivo) {
        int contador = 0;
        for (int i = 0; i < inicial.length; i++) {
            for (int j = 0; j < inicial.length; j++) {
                if (inicial[i][j] != 0 && inicial[i][j] != objetivo[i][j]) {
                    contador++;  
                }
            }
        }
        return contador;  
    }

    // Imprime del nodo raíz hasta el nodo actual
    public void imprimirCamino(Nodo raiz) {
        if (raiz == null) {
            return;
        }
        imprimirCamino(raiz.padre);  // Imprimir el camino en orden
        imprimirMatriz(raiz.matriz);  // Imprimir la matriz del nodo actual
        System.out.println();
    }

    // Imprimir la matriz actual
    public void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] inicial = {
            {1, 8, 2},  
            {0, 4, 3},
            {7, 6, 5}
        };
        int[][] objetivo = {
            {1, 2, 3}, 
            {4, 5, 6},
            {7, 8, 0}
        };
        int x = 1, y = 0;  // Posición inicial 

        Puzzle puzzle = new Puzzle();
        Scanner scanner = new Scanner(System.in);

        // Menu
        System.out.println("Seleccione el algoritmo de búsqueda:");
        System.out.println("1. DFS");
        System.out.println("2. BFS");
        System.out.println("3. Costo Uniforme");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1 -> puzzle.depthFirstSearch(inicial, objetivo, x, y);  // Ejecuta DFS
            case 2 -> puzzle.breadthFirstSearch(inicial, objetivo, x, y);  // Ejecuta BFS
            case 3 -> puzzle.uniformCostSearch(inicial, objetivo, x, y);  // Ejecuta Búsqueda de Costo Uniforme
            default -> System.out.println("Opción no válida");
        }
    }
}
