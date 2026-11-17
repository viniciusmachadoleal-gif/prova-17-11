package leilao.fatura;

import java.util.List;
import leilao.Lote;

// ConcreteComponent: a venda do lote, sem serviços adicionais.
public class VendaLote implements Cobranca {
    private final Lote lote;

    public VendaLote(Lote lote) {
        if (lote.getEstado() != Lote.Estado.VENDIDO) {
            throw new IllegalStateException("Só é possível faturar um lote vendido.");
        }
        this.lote = lote;
    }

    @Override public double getValorVenda() { return lote.getValorVenda(); }

    @Override public List<ItemFatura> getItens() {
        return List.of(new ItemFatura("Lote: " + lote.getNome() + " (valor de venda)", lote.getValorVenda()));
    }
}
