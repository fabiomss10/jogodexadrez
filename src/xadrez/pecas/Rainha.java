package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez{

	public Rainha (Tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// RAINHA IR PRA CIMA
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// RAINHA IR PRA ESQUERDA
		
		p.setValores(posicao.getLinha(), posicao.getColuna() -1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		// RAINHA IR PARA A DIREITA
		
		p.setValores(posicao.getLinha(), posicao.getColuna() +1);
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		// RAINHA IR PRA BAIXO
		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		
		// RAINHA IR PRA NOROESTE
		
				p.setValores(posicao.getLinha() - 1, posicao.getColuna());
				
				while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() - 1, p.getColuna() -1);
				}
				if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				// RAINHA IR PRA NORDESTE
				
				p.setValores(posicao.getLinha() -1, posicao.getColuna() +1);
				
				while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() -1, p.getColuna() - 1);
				}
				if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				
				// RAINHA IR PARA A SUDESTE
				
				p.setValores(posicao.getLinha() +1, posicao.getColuna()  +1);
				
				while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1,  p.getColuna() + 1);
				}
				if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				
				// RAINHA IR PRA SUDOESTE
				
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
