package com.manoelcampos.retornoboleto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Realiza a leitura de arquivos de retorno de boletos bancários no formato do
 * Banco do Brasil. Esta classe usa o padrão Strategy, representando a
 * implementação da estratégia {@link LeituraRetorno}.
 *
 * @author Manoel Campos da Silva Filho
 */
public class LeituraRetornoBancoBrasil extends ProcessarBoletos {

    @Override
    protected Boleto processarLinha(String[] vetor) {
        Boleto boleto = new Boleto();
        boleto.setId(Integer.parseInt(vetor[0]));
        boleto.setCodBanco(vetor[1]);

        boleto.setDataVencimento(LocalDate.parse(vetor[2], FORMATO_DATA));
        boleto.setDataPagamento(LocalDate.parse(vetor[3], FORMATO_DATA).atTime(0, 0, 0));

        boleto.setCpfCliente(vetor[4]);
        boleto.setValor(Double.parseDouble(vetor[5]));
        boleto.setMulta(Double.parseDouble(vetor[6]));
        boleto.setJuros(Double.parseDouble(vetor[7]));

        return boleto;
    }
}
