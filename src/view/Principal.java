package view;

import java.util.concurrent.Semaphore;

import controller.ThreadProcesso;

public class Principal {
	public static void main(String[] args) {

		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);

		for (int idProcesso = 0; idProcesso < 20; idProcesso++) {
			Thread tProcesso = new ThreadProcesso(idProcesso, semaforo);
			tProcesso.start();
		}
	}
}
