package leilao.interessados;

import java.util.HashMap;
import java.util.Map;
import leilao.Dinheiro;
import leilao.Lance;
import leilao.Lote;
import leilao.Observador;

// ConcreteObserver: exibe sempre o maior lance e o total de lances recebidos.
public class PainelLeiloeiro implements Observador {
    private static final class Resumo {
        double maiorLance;
        int totalLances;
    }
    private final Map<Lote, Resumo> resumos = new HashMap<>();

    @Override public String getNome() { return "Painel do leiloeiro"; }

    @Override public void atualizar(Lote lote, Lance lance) {
        Resumo resumo = resumos.computeIfAbsent(lote, l -> new Resumo());
        resumo.totalLances++;
        resumo.maiorLance = Math.max(resumo.maiorLance, lance.getValor());
        System.out.println("   [PAINEL] Lote '" + lote.getNome() + "' | maior lance: "
                + Dinheiro.formatar(resumo.maiorLance) + " | total de lances: " + resumo.totalLances);
    }
}
