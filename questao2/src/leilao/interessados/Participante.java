package leilao.interessados;

import leilao.Dinheiro;
import leilao.Lance;
import leilao.Lote;
import leilao.Observador;
import leilao.ResultadoLance;

// ConcreteObserver: exibe o nome do lote e o novo valor.
public class Participante implements Observador {
    private final String nome;

    public Participante(String nome) { this.nome = nome; }

    public ResultadoLance darLance(Lote lote, double valor) {
        return lote.darLance(this, valor);
    }

    @Override public String getNome() { return nome; }

    @Override public void atualizar(Lote lote, Lance lance) {
        System.out.println("   [" + nome + "] Novo lance no lote '" + lote.getNome() + "': "
                + Dinheiro.formatar(lance.getValor()));
    }
}
