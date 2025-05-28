package ArbolBinario;

public class App 
{
	public static void main(String[] args) {
        app();
    }

    static void app() {
        String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        Arbol arbol = new Arbol();
        
        //metodo para verificar que este vacio
        verificarSiEstaVacio(arbol);
        
        // Inserciones
        for (String numero : numeros) {
            arbol.insertar(numero);
            System.out.println("Se insertó el nodo con el valor " + numero + ".");
        }
        verificarSiEstaVacio(arbol);
        
        // Imprimir el recorrido
        System.out.println("Recorrido preorden:");
        imprimirPreorden(arbol);
        
        System.out.println("\nRecorrido inorden:");
        imprimirInorden(arbol);

        System.out.println("\nRecorrido postorden:");
        imprimirPostorden(arbol);
    }

    static void verificarSiEstaVacio(Arbol arbol) {
        System.out.println(arbol.vacio() ? "El árbol está vacío." : "El árbol no está vacío.");
    }

    static void imprimirPreorden(Arbol arbol) {
        arbol.preorden();
    }

    static void imprimirInorden(Arbol arbol) {
        arbol.inorden();
    }
    static void imprimirPostorden(Arbol arbol) {
        arbol.postorden();
    }


}
