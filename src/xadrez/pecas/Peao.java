package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Color;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaDeXadrez partidaDeXadrez;

	public Peao(Tabuleiro tabuleiro, Color color, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, color);
		this.partidaDeXadrez = partidaDeXadrez;
	}

	@Override
	public boolean[][] possiveisMovimentos() {

		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0, 0);

		if (getColor() == Color.WHITE) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().issoEumaPeca(p2) && getContagemMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// en passant white

			if (posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && issoEumaPecaInimiga(esquerda)
						&& getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && issoEumaPecaInimiga(direita)
						&& getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVulneravel()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}

		} else {
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().issoEumaPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().issoEumaPeca(p2) && getContagemMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && issoEumaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// en passant black

			if (posicao.getLinha() == 4) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && issoEumaPecaInimiga(esquerda)
						&& getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && issoEumaPecaInimiga(direita)
						&& getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVulneravel()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
