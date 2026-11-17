package leilao.fatura;

// ConcreteDecorator
public class CertificadoAutenticidade extends ServicoAdicional {
    public CertificadoAutenticidade(Cobranca base) { super(base); }
    @Override protected String descricaoServico() { return "Certificado de autenticidade"; }
    @Override protected double custoServico() { return 300.00; }
}
