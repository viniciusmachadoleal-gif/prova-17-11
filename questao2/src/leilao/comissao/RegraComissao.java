package leilao.comissao;

// Strategy (GoF): algoritmo de cálculo da comissão, trocável em tempo de execução.
public interface RegraComissao {
    double calcular(double valorVenda);
    String getDescricao();
}
