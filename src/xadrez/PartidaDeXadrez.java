package xadrez;

import tabuleiro.Tabuleiro;

public class PartidaDeXadrez {
	
	private Tabuleiro tabuleiro;

	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
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
}
