package leilao.comissao;

// ConcreteStrategy
public class ComissaoImoveis implements RegraComissao {
    @Override public double calcular(double valorVenda) { return valorVenda * 0.03 + 1500.00; }
    @Override public String getDescricao() { return "Imóveis: 3% do valor mais R$ 1.500,00 fixos"; }
}
