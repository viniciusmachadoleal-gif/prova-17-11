package leilao.fatura;

// ConcreteDecorator
public class EmbalagemEspecial extends ServicoAdicional {
    public EmbalagemEspecial(Cobranca base) { super(base); }
    @Override protected String descricaoServico() { return "Embalagem especial"; }
    @Override protected double custoServico() { return 180.00; }
}
