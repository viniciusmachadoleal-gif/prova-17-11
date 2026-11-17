import leilao.*;
import leilao.categorias.*;
import leilao.comissao.ComissaoEletronicos;
import leilao.fatura.*;
import leilao.interessados.*;

public class Main {
    private static void lance(Participante p, Lote lote, double valor) {
        System.out.println("\n" + p.getNome() + " tenta dar lance de " + Dinheiro.formatar(valor)
                + " no lote '" + lote.getNome() + "'");
        ResultadoLance r = p.darLance(lote, valor);
        System.out.println("   -> " + (r.aceito() ? "ACEITO: " : "RECUSADO: ") + r.mensagem());
    }

    private static void encerrar(Lote lote) {
        lote.encerrar();
        System.out.println("\n>>> Lote '" + lote.getNome() + "' encerrado: " + (lote.getEstado() == Lote.Estado.VENDIDO
                ? "VENDIDO para " + lote.getComprador().getNome() + " por " + Dinheiro.formatar(lote.getValorVenda())
                : "NÃO VENDIDO (reserva de " + Dinheiro.formatar(lote.getPrecoReserva()) + " não atingida)"));
    }

    public static void main(String[] args) {
        // Lotes de categorias distintas
        Lote quadro = new Lote("Pintura Abstrata", new Arte(), 10_000.00, 12_000.00);
        Lote celular = new Lote("Smartphone Premium", new Eletronicos(), 3_000.00, 4_000.00);

        // Participantes, painel e auditor
        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        for (Observador o : new Observador[] {ana, bruno, carla, painel, auditor}) quadro.acompanhar(o);
        for (Observador o : new Observador[] {bruno, carla, painel, auditor}) celular.acompanhar(o);

        System.out.println("##### LOTE 1: " + quadro.getNome() + " (" + quadro.getCategoria().getNome() + ") #####");
        lance(ana, quadro, 10_500.00);    // aceito (exatamente 5%)
        lance(bruno, quadro, 10_600.00);  // recusado (< 5% acima)
        lance(bruno, quadro, 11_025.00);  // aceito
        System.out.println("\nCarla deixa de acompanhar o lote '" + quadro.getNome() + "'.");
        quadro.deixarDeAcompanhar(carla);
        lance(ana, quadro, 12_000.00);    // aceito (Carla não é notificada)

        System.out.println("\n##### CAMPANHA: regra de Eletrônicos aplicada ao lote de Arte #####");
        System.out.println("Regra antes: " + quadro.getRegraComissao().getDescricao());
        quadro.setRegraComissao(new ComissaoEletronicos());
        System.out.println("Regra agora: " + quadro.getRegraComissao().getDescricao());

        System.out.println("\n##### LOTE 2: " + celular.getNome() + " (" + celular.getCategoria().getNome() + ") #####");
        lance(carla, celular, 3_150.00);  // aceito
        lance(bruno, celular, 3_400.00);  // aceito
        lance(carla, celular, 3_500.00);  // recusado

        encerrar(quadro);   // vendido
        encerrar(celular);  // não vendido
        lance(carla, celular, 5_000.00);  // recusado: lote encerrado

        AcertoVendedor.imprimir(quadro);

        // Fatura do comprador com serviços combinados (embalagem contratada duas vezes)
        Cobranca fatura = new VendaLote(quadro);
        fatura = new SeguroTransporte(fatura);
        fatura = new CertificadoAutenticidade(fatura);
        fatura = new EmbalagemEspecial(fatura);
        fatura = new EmbalagemEspecial(fatura);
        fatura = new EntregaExpressa(fatura);
        ImpressoraFatura.imprimir(fatura);

        // Mesma impressora, venda sem nenhum serviço adicional
        ImpressoraFatura.imprimir(new VendaLote(quadro));

        auditor.imprimirRegistros();
    }
}
