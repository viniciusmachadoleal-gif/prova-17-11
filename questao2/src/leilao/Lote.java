package leilao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import leilao.comissao.RegraComissao;

// Observer: Lote é o ConcreteSubject. Strategy: Lote é o Context da regra de comissão.
public class Lote implements Sujeito {
    public enum Estado { ABERTO, VENDIDO, NAO_VENDIDO }

    private static final BigDecimal INCREMENTO_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final Categoria categoria;
    private final double precoInicial;
    private final double precoReserva;
    private final List<Observador> observadores = new ArrayList<>();
    private final List<Lance> lances = new ArrayList<>();
    private RegraComissao regraComissao;
    private Estado estado = Estado.ABERTO;
    private Observador comprador;
    private double valorVenda;
    private double comissaoCobrada;

    public Lote(String nome, Categoria categoria, double precoInicial, double precoReserva) {
        this.nome = Objects.requireNonNull(nome);
        this.categoria = Objects.requireNonNull(categoria);
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.regraComissao = categoria.regraComissaoPadrao();
    }

    // ---------- Lances (RF02) ----------
    public ResultadoLance darLance(Observador autor, double valor) {
        if (estado != Estado.ABERTO) {
            return ResultadoLance.recusado("o lote '" + nome + "' está encerrado e não aceita novos lances.");
        }
        BigDecimal minimo = BigDecimal.valueOf(getLanceAtual())
                .multiply(INCREMENTO_MINIMO).setScale(2, RoundingMode.CEILING);
        if (BigDecimal.valueOf(valor).compareTo(minimo) < 0) {
            return ResultadoLance.recusado("o lance deve ser pelo menos 5% maior que o lance atual ("
                    + Dinheiro.formatar(getLanceAtual()) + "); valor mínimo: "
                    + Dinheiro.formatar(minimo.doubleValue()) + ".");
        }
        Lance lance = new Lance(autor, valor);
        lances.add(lance);
        notificar(lance);
        return ResultadoLance.aceito("lance de " + Dinheiro.formatar(valor) + " registrado.");
    }

    // ---------- Observer (RF03) ----------
    @Override public void acompanhar(Observador observador) {
        if (!observadores.contains(observador)) observadores.add(observador);
    }
    @Override public void deixarDeAcompanhar(Observador observador) {
        observadores.remove(observador);
    }
    @Override public void notificar(Lance lance) {
        for (Observador observador : new ArrayList<>(observadores)) {
            if (observador != lance.getAutor()) observador.atualizar(this, lance);
        }
    }

    // ---------- Encerramento (RF05) ----------
    public void encerrar() {
        if (estado != Estado.ABERTO) throw new IllegalStateException("O lote já foi encerrado.");
        Lance maior = getMaiorLance();
        if (maior != null && maior.getValor() >= precoReserva) {
            estado = Estado.VENDIDO;
            comprador = maior.getAutor();
            valorVenda = maior.getValor();
            // A regra vigente neste momento é a que define a comissão (RF07).
            comissaoCobrada = regraComissao.calcular(valorVenda);
        } else {
            estado = Estado.NAO_VENDIDO;
        }
    }

    // ---------- Strategy (RF06/RF07) ----------
    public void setRegraComissao(RegraComissao novaRegra) {
        if (estado != Estado.ABERTO) {
            throw new IllegalStateException("A regra de comissão só pode ser trocada antes do encerramento.");
        }
        this.regraComissao = Objects.requireNonNull(novaRegra);
    }

    public Lance getMaiorLance() { return lances.isEmpty() ? null : lances.get(lances.size() - 1); }
    public double getLanceAtual() { return lances.isEmpty() ? precoInicial : getMaiorLance().getValor(); }
    public List<Lance> getLances() { return Collections.unmodifiableList(lances); }
    public String getNome() { return nome; }
    public Categoria getCategoria() { return categoria; }
    public double getPrecoInicial() { return precoInicial; }
    public double getPrecoReserva() { return precoReserva; }
    public Estado getEstado() { return estado; }
    public Observador getComprador() { return comprador; }
    public double getValorVenda() { return valorVenda; }
    public double getComissaoCobrada() { return comissaoCobrada; }
    public RegraComissao getRegraComissao() { return regraComissao; }
}
