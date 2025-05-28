import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.Gpr;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class EvaluadorDePeliculas {

    public static void main(String[] args) {
    	
    	
        // Cargar desde el archivo FCL
        String nombreArchivo = "src/movie.fcl";
        FIS fis = FIS.load(nombreArchivo, true);
        if (fis == null) {
            System.err.println("No se puede cargar el archivo: '" + nombreArchivo + "'");
            return;
        }

        // Mostrar el conjunto de reglas
        FunctionBlock functionBlock = fis.getFunctionBlock(null);
        JFuzzyChart.get().chart(functionBlock);

        // Establecer las entradas
        functionBlock.setVariable("Calidad_de_trama", 7.5);
        functionBlock.setVariable("Actuacion", 8.0);

        // Evaluar
        functionBlock.evaluate();

        // Mostrar el gráfico de la variable de salida
        Variable calificacion = functionBlock.getVariable("Calificacion_de_pelicula");
        JFuzzyChart.get().chart(calificacion, calificacion.getDefuzzifier(), true);

        // Imprimir el conjunto de reglas
        System.out.println(functionBlock);
        System.out.println("Calificación de la película: " + calificacion.getValue());
    }
}
