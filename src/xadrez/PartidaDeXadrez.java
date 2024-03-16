 package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;
import xadrez.PosicaoXadrez;
 
public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;

	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] matrizDePecas = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for(int l = 0; l < tabuleiro.getLinhas(); l++) {
			for(int c = 0; c < tabuleiro.getColunas(); c++) {
				matrizDePecas[l][c] = (PecaXadrez) tabuleiro.peca(l, c);
			}
		}
		return matrizDePecas;
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.moverPeca(peca, new PosicaoXadrez(coluna, linha).posicaoDeMatrizParaXadrex());
	}
	
	private void setupInicial() {
		colocarNovaPeca('b', 6, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Color.WHITE));
	}
}
