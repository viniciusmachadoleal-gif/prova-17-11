package leilao.fatura;

import java.util.ArrayList;
import java.util.List;

// Decorator abstrato: embrulha outra Cobranca e acrescenta o próprio item à fatura.
public abstract class ServicoAdicional implements Cobranca {
    private final Cobranca base;

    protected ServicoAdicional(Cobranca base) { this.base = base; }

    protected abstract String descricaoServico();
    protected abstract double custoServico();

    @Override public double getValorVenda() { return base.getValorVenda(); }

    @Override public List<ItemFatura> getItens() {
        List<ItemFatura> itens = new ArrayList<>(base.getItens());
        itens.add(new ItemFatura(descricaoServico(), custoServico()));
        return itens;
    }
}
