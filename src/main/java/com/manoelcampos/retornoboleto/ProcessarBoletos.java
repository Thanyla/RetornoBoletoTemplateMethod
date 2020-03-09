package com.manoelcampos.retornoboleto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProcessarBoletos {

    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private Function<String[], Boleto> processarLinha;

    public ProcessarBoletos(Function<String[], Boleto> processarLinha) {
        this.processarLinha = processarLinha;
    }

    public void setProcessarLinha(Function<String[], Boleto> processarLinha) {
        this.processarLinha = processarLinha;
    }

    public final void processar(String nomeArquivo) {
        System.out.println("Boletos");
        System.out.println("----------------------------------------------------------------------------------");

        List<Boleto> boletos = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(nomeArquivo));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] vetor = line.split(";");
                Boleto boleto = processarLinha.apply(vetor);
                boletos.add(boleto);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }
}
