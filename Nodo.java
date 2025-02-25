package Puzzle;

public class Nodo {

    // Referencia al nodo padre en el árbol de búsqueda
    public Nodo padre;
    
    // Matriz que representa el estado actual del tablero
    public int[][] matriz;
    
    // Coordenadas de la posición actual
    public int x, y;
    public int costo;
    
    // Número de movimientos realizados desde el estado inicial
    public int nivel;

    // Constructor de la clase Nodo
    public Nodo(int[][] matrix, int x, int y, int newX, int newY, int nivel, Nodo padre) {
        this.padre = padre;
        
        this.matriz = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            this.matriz[i] = matrix[i].clone();  
        }
        // Intercambio de valores en la matriz
        this.matriz[x][y]       = this.matriz[x][y] + this.matriz[newX][newY];  // Sumar ambos valores
        this.matriz[newX][newY] = this.matriz[x][y] - this.matriz[newX][newY];  // Resta para obtener el valor inicial en (x, y)
        this.matriz[x][y]       = this.matriz[x][y] - this.matriz[newX][newY];  // Resta para completar el intercambio

        // Inicializamos el costo 
        this.costo = Integer.MAX_VALUE;

        // Guardamos el nivel del nodo
        this.nivel = nivel;

        // Actualizamos las coordenadas
        this.x = newX;
        this.y = newY;
    }

}
