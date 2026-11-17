package leilao.interessados;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import leilao.Dinheiro;
import leilao.Lance;
import leilao.Lote;
import leilao.Observador;

// ConcreteObserver: registra todos os lances aceitos com data e hora.
public class Auditor implements Observador {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final List<String> registros = new ArrayList<>();

    @Override public String getNome() { return "Auditor"; }

    @Override public void atualizar(Lote lote, Lance lance) {
        registros.add(LocalDateTime.now().format(FORMATO) + " | Lote: " + lote.getNome()
                + " | Participante: " + lance.getAutor().getNome()
                + " | Valor: " + Dinheiro.formatar(lance.getValor()));
    }

    public void imprimirRegistros() {
        System.out.println("\n=== REGISTRO DO AUDITOR (lances aceitos) ===");
        for (String registro : registros) System.out.println(registro);
        System.out.println("Total de lances registrados: " + registros.size());
    }
}
