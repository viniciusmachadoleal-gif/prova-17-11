package leilao;

import leilao.comissao.RegraComissao;

// Cada categoria informa o nome e a regra de comissão padrão (Strategy inicial do lote).
// Nova categoria = nova classe que implementa esta interface (sem if/switch no cálculo).
public interface Categoria {
    String getNome();
    RegraComissao regraComissaoPadrao();
}
