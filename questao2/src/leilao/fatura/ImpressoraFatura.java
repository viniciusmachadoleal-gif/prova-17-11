package leilao.fatura;

import leilao.Dinheiro;

// RF09/RNF04: imprime qualquer Cobranca sem verificar quais serviços existem.
public final class ImpressoraFatura {
    private ImpressoraFatura() {}

    public static void imprimir(Cobranca cobranca) {
        System.out.println("\n=== FATURA DO COMPRADOR ===");
        for (ItemFatura item : cobranca.getItens()) {
            System.out.println(item.descricao() + ": " + Dinheiro.formatar(item.custo()));
        }
        System.out.println("TOTAL A PAGAR: " + Dinheiro.formatar(cobranca.getTotal()));
    }
}
