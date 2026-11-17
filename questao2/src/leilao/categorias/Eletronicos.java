package leilao.categorias;

import leilao.Categoria;
import leilao.comissao.ComissaoEletronicos;
import leilao.comissao.RegraComissao;

public class Eletronicos implements Categoria {
    @Override public String getNome() { return "Eletrônicos"; }
    @Override public RegraComissao regraComissaoPadrao() { return new ComissaoEletronicos(); }
}
