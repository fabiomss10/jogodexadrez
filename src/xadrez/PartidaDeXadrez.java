package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turn;
	private Color currentPlayer;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassantVulneravel;

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

	public boolean getCheckMate() {
		return checkMate;
	}
	
	public PecaXadrez getEnPassantVulneravel() {
		return enPassantVulneravel;
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] matrizDePecas = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

		for (int l = 0; l < tabuleiro.getLinhas(); l++) {
			for (int c = 0; c < tabuleiro.getColunas(); c++) {
				matrizDePecas[l][c] = (PecaXadrez) tabuleiro.peca(l, c);
			}
		}
		return matrizDePecas;
	}

	public boolean[][] possiveisMovimentos(PosicaoXadrez procurarPosicao) {
		Posicao posicao = procurarPosicao.posicaoDeMatrizParaXadrez();
		validarSeExiste(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}

	private Peca fazerMovimento(Posicao procurar, Posicao alvo) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(procurar);
		p.incrementarContagemMovimento();
		Peca pecaCapturada = tabuleiro.removerPeca(alvo);
		tabuleiro.moverPeca(p, alvo);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		// roque pequeno

		if (p instanceof Rei && alvo.getColuna() == procurar.getColuna() + 2) {
			Posicao procurarT = new Posicao(procurar.getLinha(), procurar.getColuna() + 3);
			Posicao alvoT = new Posicao(procurar.getLinha(), procurar.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(procurarT);
			tabuleiro.moverPeca(torre, alvoT);
			torre.incrementarContagemMovimento();
		}

		// roque grande

		if (p instanceof Rei && alvo.getColuna() == procurar.getColuna() - 2) {
			Posicao procurarT = new Posicao(procurar.getLinha(), procurar.getColuna() - 4);
			Posicao alvoT = new Posicao(procurar.getLinha(), procurar.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(procurarT);
			tabuleiro.moverPeca(torre, alvoT);
			torre.incrementarContagemMovimento();
		}

		//en passant
		
		if(p instanceof Peao) {
			if(procurar.getColuna() != alvo.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if (p.getColor() == Color.WHITE) {
					posicaoPeao = new Posicao(alvo.getLinha() + 1, alvo.getColuna());
				}
				else {
					posicaoPeao = new Posicao(alvo.getLinha() - 1, alvo.getColuna());
				}
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);
			}
		}
		
		
		
		return pecaCapturada;
	}

	private void desfazerMovimento(Posicao procurar, Posicao alvo, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(alvo);
		p.decrementarContagemMovimento();
		tabuleiro.moverPeca(p, alvo);

		if (pecaCapturada != null) {
			tabuleiro.moverPeca(pecaCapturada, alvo);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}

		// roque pequeno

		if (p instanceof Rei && alvo.getColuna() == procurar.getColuna() + 2) {
			Posicao procurarT = new Posicao(procurar.getLinha(), procurar.getColuna() + 3);
			Posicao alvoT = new Posicao(procurar.getLinha(), procurar.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(alvoT);
			tabuleiro.moverPeca(torre, procurarT);
			torre.decrementarContagemMovimento();
		}

		// roque grande

		if (p instanceof Rei && alvo.getColuna() == procurar.getColuna() - 2) {
			Posicao procurarT = new Posicao(procurar.getLinha(), procurar.getColuna() - 4);
			Posicao alvoT = new Posicao(procurar.getLinha(), procurar.getColuna() - 1);
			PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(alvoT);
			tabuleiro.moverPeca(torre, procurarT);
			torre.decrementarContagemMovimento();
		}
		
		
		//en passant
		
				if(p instanceof Peao) {
					if(procurar.getColuna() != alvo.getColuna() && pecaCapturada == enPassantVulneravel) {
						PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(alvo);
						Posicao posicaoPeao;
						if (p.getColor() == Color.WHITE) {
							posicaoPeao = new Posicao(3, alvo.getColuna());
						}
						else {
							posicaoPeao = new Posicao(4, alvo.getColuna());
						}
						tabuleiro.moverPeca(peao, posicaoPeao);
						
					}
				}

	}

	public PecaXadrez MovimentarPeca(PosicaoXadrez procurarPosicao, PosicaoXadrez posicaoAlvo) {
		Posicao procurar = procurarPosicao.posicaoDeMatrizParaXadrez();
		Posicao alvo = posicaoAlvo.posicaoDeMatrizParaXadrez();
		validarSeExiste(procurar);
		validarDestino(procurar, alvo);
		Peca pecaCapturada = fazerMovimento(procurar, alvo);

		if (testeCheck(currentPlayer)) {
			desfazerMovimento(procurar, alvo, pecaCapturada);
			throw new ExcecoesXadrez("Voce nao pode se colocar em cheque");
		}

		PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(alvo);
		
		
		
		check = (testeCheck(oponente(currentPlayer))) ? true : false;

		if (testeCheckMate(oponente(currentPlayer))) {
			checkMate = true;
		} else {
			nextTurn();
		}

		//en passant
		if(pecaMovida instanceof Peao && (alvo.getLinha() == procurar.getLinha() - 2 || alvo.getLinha() == procurar.getLinha() + 2)) {
			enPassantVulneravel = pecaMovida;
		} else {
			enPassantVulneravel = null;
		}
		
		return (PecaXadrez) pecaCapturada;
	}

	private void validarSeExiste(Posicao posicao) {
		if (!tabuleiro.issoEumaPeca(posicao)) {
			throw new ExcecoesXadrez("Nao tem peca nessa posicao");
		}
		if (currentPlayer != ((PecaXadrez) tabuleiro.peca(posicao)).getColor()) {
			throw new ExcecoesXadrez("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new ExcecoesXadrez("Nao foi possivel mover a peca para a posicao escolhida");
		}
	}

	private void validarDestino(Posicao procurar, Posicao alvo) {
		if (!tabuleiro.peca(procurar).movimentoEpossivel(alvo)) {
			throw new ExcecoesXadrez("A peça escolhida nao pode se mover para a posicao de destino");
		}
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color oponente(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private PecaXadrez rei(Color color) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getColor() == color)
				.collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Nao existe um rei  " + color + " no tabuleiro");
	}

	private boolean testeCheck(Color color) {
		Posicao posicaoRei = rei(color).getPosicaoXadrez().posicaoDeMatrizParaXadrez();
		List<Peca> pecasOponentes = pecasNoTabuleiro.stream()
				.filter(x -> ((PecaXadrez) x).getColor() == oponente(color)).collect(Collectors.toList());
		for (Peca p : pecasOponentes) {
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;

	}

	
	
	
	private boolean testeCheckMate(Color color) {
		if (!testeCheck(color)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getColor() == color)
				.collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.possiveisMovimentos();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao procurar = ((PecaXadrez) p).getPosicaoXadrez().posicaoDeMatrizParaXadrez();
						Posicao alvo = new Posicao(i, j);
						Peca pecaCapturada = fazerMovimento(procurar, alvo);
						boolean testeCheck = testeCheck(color);
						desfazerMovimento(procurar, alvo, pecaCapturada);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;

	}

	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.moverPeca(peca, new PosicaoXadrez(coluna, linha).posicaoDeMatrizParaXadrez());
		pecasNoTabuleiro.add(peca);
	}

	private void setupInicial() {
		colocarNovaPeca('a', 1, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 1, new Bispo(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 1, new Rainha(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 1, new Rei(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('f', 1, new Bispo(tabuleiro, Color.WHITE));
		colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Color.WHITE));
		colocarNovaPeca('h', 1, new Torre(tabuleiro, Color.WHITE));
		colocarNovaPeca('a', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('b', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('c', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('d', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('e', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('f', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('g', 2, new Peao(tabuleiro, Color.WHITE, this));
		colocarNovaPeca('h', 2, new Peao(tabuleiro, Color.WHITE, this));

		colocarNovaPeca('a', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 8, new Rainha(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 8, new Rei(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('f', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Color.BLACK));
		colocarNovaPeca('h', 8, new Torre(tabuleiro, Color.BLACK));
		colocarNovaPeca('a', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('b', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('c', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('d', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('e', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('f', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('g', 7, new Peao(tabuleiro, Color.BLACK, this));
		colocarNovaPeca('h', 7, new Peao(tabuleiro, Color.BLACK, this));
	}
}
