package app;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();


		while(true) {
			Interface.clearScreen();
			
			Interface.printTabuleiro(partidaDeXadrez.getPecas());
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
			 
		}
	}
}