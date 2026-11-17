package leilao.fatura;

// ConcreteDecorator
public class EntregaExpressa extends ServicoAdicional {
    public EntregaExpressa(Cobranca base) { super(base); }
    @Override protected String descricaoServico() { return "Entrega expressa"; }
    @Override protected double custoServico() { return 450.00; }
}
