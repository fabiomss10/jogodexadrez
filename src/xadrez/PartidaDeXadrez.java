package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

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
	
	private void setupInicial() {
		tabuleiro.moverPeca(new Torre(tabuleiro, Color.WHITE), new Posicao(2, 1));
		tabuleiro.moverPeca(new Rei(tabuleiro, Color.BLACK), new Posicao(2, 4));
		tabuleiro.moverPeca(new Rei(tabuleiro, Color.BLACK), new Posicao(7, 4));
	}
}
