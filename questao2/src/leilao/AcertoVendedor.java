package leilao;

// RF10: acerto com o vendedor (valor de venda, comissão e líquido a repassar).
public final class AcertoVendedor {
    private AcertoVendedor() {}

    public static void imprimir(Lote lote) {
        if (lote.getEstado() != Lote.Estado.VENDIDO) {
            throw new IllegalStateException("Só há acerto para lote vendido.");
        }
        double venda = lote.getValorVenda();
        double comissao = lote.getComissaoCobrada();
        System.out.println("\n=== ACERTO DO VENDEDOR - Lote '" + lote.getNome() + "' ===");
        System.out.println("Valor de venda: " + Dinheiro.formatar(venda));
        System.out.println("Comissão cobrada (" + lote.getRegraComissao().getDescricao() + "): "
                + Dinheiro.formatar(comissao));
        System.out.println("Valor líquido a repassar: " + Dinheiro.formatar(venda - comissao));
    }
}
