package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// BISPO IR PRA NOROESTE
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() -1);
		}
		if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// BISPO IR PRA NORDESTE
		
		p.setValores(posicao.getLinha() -1, posicao.getColuna() +1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		// BISPO IR PARA A SUDESTE
		
		p.setValores(posicao.getLinha() +1, posicao.getColuna()  +1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1,  p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		// BISPO  IR PRA SUDOESTE
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() -1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() -1);
		}
		if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		

	
	return mat;
	
	}
	

	
}
