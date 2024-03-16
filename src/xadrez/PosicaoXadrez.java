package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcecoesXadrez("Erro. Valores validos sao de A-H e 1-8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	
	
	public char getColuna() {
		return coluna;
	}


	public int getLinha() {
		return linha;
	}

	
	protected Posicao posicaoDeMatrizParaXadrex() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez posicaoDeXadrezParaMatriz(Posicao posicao) {
		return new PosicaoXadrez((char)('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha; //As aspas servem para forçar o toString a ser uma String
	}
}
