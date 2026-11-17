package leilao.comissao;

// ConcreteStrategy
public class ComissaoEletronicos implements RegraComissao {
    @Override public double calcular(double valorVenda) { return Math.max(valorVenda * 0.12, 50.00); }
    @Override public String getDescricao() { return "Eletrônicos: 12% do valor, mínimo de R$ 50,00"; }
}
