import com.manoelcampos.retornoboleto.LeituraRetornoBancoBrasil;
import com.manoelcampos.retornoboleto.ProcessarBoletos;

/**
 * Classe para ver o funcionamento da leitura de boletos.
 *
 * @author Manoel Campos da Silva Filho
 */
public class Principal {
    public static void main(String[] args) {
        final ProcessarBoletos processador = new ProcessarBoletos(LeituraRetornoBancoBrasil::processarLinha);

        String nomeArquivo = Principal.class.getResource("banco-brasil-1.csv").getPath();
        System.out.println("Lendo arquivo " + nomeArquivo + "\n");

        processador.processar(nomeArquivo);
    }
}
