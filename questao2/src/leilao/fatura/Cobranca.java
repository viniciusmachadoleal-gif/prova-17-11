package leilao.fatura;

import java.util.List;

// Decorator (GoF): Component. A venda pura e a venda com N serviços são tratadas igualmente.
public interface Cobranca {
    double getValorVenda();
    List<ItemFatura> getItens();

    default double getTotal() {
        double total = 0;
        for (ItemFatura item : getItens()) total += item.custo();
        return total;
    }
}
