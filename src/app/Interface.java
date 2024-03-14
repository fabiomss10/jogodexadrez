package app;

import xadrez.PecaXadrez;

public class Interface {
	
	public static void printTabuleiro(PecaXadrez[][] pecas) {

		for (int l=0; l<pecas.length; l++) {
			
			System.out.print((8-l) + " ");
			
			for (int c = 0; c < pecas.length; c++) {
			
				printPeca(pecas[l][c]);
			}
			
				System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	

	private static void printPeca(PecaXadrez peca) {
		if (peca == null) {
			System.out.print("-");
		}
		else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}
}
