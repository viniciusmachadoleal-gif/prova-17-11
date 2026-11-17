package leilao.categorias;

import leilao.Categoria;
import leilao.comissao.ComissaoImoveis;
import leilao.comissao.RegraComissao;

public class Imoveis implements Categoria {
    @Override public String getNome() { return "Imóveis"; }
    @Override public RegraComissao regraComissaoPadrao() { return new ComissaoImoveis(); }
}
