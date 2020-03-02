package com.manoelcampos.retornoboleto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Processa arquivos de retorno de boletos bancários utilizando a implementação
 * de alguma estratégia ({@link LeituraRetorno}). Esta é uma classe que chamamos
 * de Estrategista, por poder utilizar diferentes estratégias de acordo com as
 * necessidades, podendo mudar a estratégia a ser utilizada até em tempo de
 * execução.
 *
 * @author Manoel Campos da Silva Filho
 */
public abstract class ProcessarBoletos {
    public final void processar(String nomeArquivo) {
        System.out.println("Boletos");
        System.out.println("----------------------------------------------------------------------------------");

        List<Boleto> boletos = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(nomeArquivo));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] vetor = line.split(";");
                Boleto boleto = processarLinha(vetor);
                boletos.add(boleto);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }

    protected abstract Boleto processarLinha(String[] vetor);
}
