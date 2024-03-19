 package xadrez;

import tabuleiro.Peca;
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
	
	
	private Peca fazerMovimento(Posicao procurar, Posicao alvo) {
		Peca p = tabuleiro.removerPeca(procurar);
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.moverPeca(p, alvo);
		return pecaCapturada;
	}
	public PecaXadrez MovimentarPeca(PosicaoXadrez procurarPosicao, PosicaoXadrez posicaoAlvo) {
		Posicao procurar = procurarPosicao.posicaoDeMatrizParaXadrex();
		Posicao alvo = posicaoAlvo.posicaoDeMatrizParaXadrex();
		validarSeExiste(procurar);
		Peca pecaCapturada = fazerMovimento(procurar, alvo);
		return (PecaXadrez)pecaCapturada;
	}
	
	private void validarSeExiste(Posicao posicao) {
		if (!tabuleiro.issoEumaPeca(posicao)) {
			throw new ExcecoesXadrez("Nao tem peca nessa posicao");
		}
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.moverPeca(peca, new PosicaoXadrez(coluna, linha).posicaoDeMatrizParaXadrex());
	}
	
	private void setupInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Color.WHITE));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Color.BLACK));
	}
}
