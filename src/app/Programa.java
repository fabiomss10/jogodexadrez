package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecoesXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaXadrez> capturado = new ArrayList<>();
		


		while(true) {
			try {
			Interface.clearScreen();
			Interface.printPartida(partidaDeXadrez, capturado);
			System.out.println(" ");
			System.out.println("Procurar: ");
			PosicaoXadrez procurar = Interface.lerPosicao(sc);
			
			boolean[][] possiveisMovimentos = partidaDeXadrez.possiveisMovimentos(procurar);
			Interface.clearScreen();
			Interface.printTabuleiro(partidaDeXadrez.getPecas(), possiveisMovimentos);
			
			
			System.out.println("");
			System.out.println("Alvo: ");
			PosicaoXadrez alvo = Interface.lerPosicao(sc);

			PecaXadrez pecaCapturada = partidaDeXadrez.MovimentarPeca(procurar, alvo);
			
			if(pecaCapturada != null) {
				capturado.add(pecaCapturada);
			}
			}
			catch(ExcecoesXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
			}
		}
	}
