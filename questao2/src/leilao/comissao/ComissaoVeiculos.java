package leilao.comissao;

// ConcreteStrategy
public class ComissaoVeiculos implements RegraComissao {
    @Override public double calcular(double valorVenda) { return Math.min(valorVenda * 0.08, 5000.00); }
    @Override public String getDescricao() { return "Veículos: 8% do valor, limitada a R$ 5.000,00"; }
}
