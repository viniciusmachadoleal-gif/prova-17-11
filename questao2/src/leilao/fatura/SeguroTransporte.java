package leilao.fatura;

// ConcreteDecorator
public class SeguroTransporte extends ServicoAdicional {
    public SeguroTransporte(Cobranca base) { super(base); }
    @Override protected String descricaoServico() { return "Seguro de transporte (1,5% do valor de venda)"; }
    @Override protected double custoServico() { return getValorVenda() * 0.015; }
}
