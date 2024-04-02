package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;
 
public class PartidaDeXadrez {
		
	private int turn;
	private Color currentPlayer;
	private Tabuleiro tabuleiro;
	private boolean check;
	
	
	
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		setupInicial();
	}
	
	public int getTurn() {
		return turn;
	}
	
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
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


	public boolean[][] possiveisMovimentos(PosicaoXadrez procurarPosicao){
		Posicao posicao = procurarPosicao.posicaoDeMatrizParaXadrez();
		validarSeExiste(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	
	
	private Peca fazerMovimento(Posicao procurar, Posicao alvo) {
		Peca p = tabuleiro.removerPeca(procurar);
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.moverPeca(p, alvo);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	
	private void desfazerMovimento(Posicao procurar, Posicao alvo, Peca pecaCapturada) {
		Peca p = tabuleiro.removerPeca(alvo);
		tabuleiro.moverPeca(p, alvo);
		
		if(pecaCapturada != null) {
			tabuleiro.moverPeca(pecaCapturada, alvo);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	
	
	public PecaXadrez MovimentarPeca(PosicaoXadrez procurarPosicao, PosicaoXadrez posicaoAlvo) {
		Posicao procurar = procurarPosicao.posicaoDeMatrizParaXadrez();
		Posicao alvo = posicaoAlvo.posicaoDeMatrizParaXadrez();
		validarSeExiste(procurar);
		validarDestino(procurar, alvo);
		Peca pecaCapturada = fazerMovimento(procurar, alvo);
		
		if(testeCheck(currentPlayer)) {
			desfazerMovimento(procurar, alvo, pecaCapturada);
			throw new ExcecoesXadrez("Voce nao pode se colocar em cheque");
		}
		
		check = (testeCheck(oponente(currentPlayer))) ? true : false;
		
		
		proximaVez();
		return (PecaXadrez)pecaCapturada;
	}

	private void validarSeExiste(Posicao posicao) {
		if (!tabuleiro.issoEumaPeca(posicao)) {
			throw new ExcecoesXadrez("Nao tem peca nessa posicao");
		}
		if (currentPlayer != ((PecaXadrez)tabuleiro.peca(posicao)).getColor()) {
			throw new ExcecoesXadrez("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new ExcecoesXadrez("Nao foi possivel mover a peca para a posicao escolhida");
		}
	}
	
	private void validarDestino(Posicao procurar, Posicao alvo) {
		if(!tabuleiro.peca(procurar).movimentoEpossivel(alvo)) {
			throw new ExcecoesXadrez("A peça escolhida nao pode se mover para a posicao de destino");
		}
	}

	private void proximaVez() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE ;
	}
	
	
	private Color oponente(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private PecaXadrez rei(Color color) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
			throw new IllegalStateException("Nao existe um rei  " + color + " no tabuleiro");
	}
	
	
	private boolean testeCheck(Color color) {
		Posicao posicaoRei = rei(color).getPosicaoXadrez().posicaoDeMatrizParaXadrez();
		List<Peca> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == oponente(color)).collect(Collectors.toList());
		for(Peca p : pecasOponentes) {
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	
	
	}
	
	
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.moverPeca(peca, new PosicaoXadrez(coluna, linha).posicaoDeMatrizParaXadrez());
		pecasNoTabuleiro.add(peca);
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
