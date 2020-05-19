package controller;

import java.util.concurrent.Semaphore;

public class ThreadProcesso extends Thread {

	private int idProcesso;
	private Semaphore semaforo;
	private static int posicaoChegada;
	private static int posicaoSaida;

	public ThreadProcesso(int idProcesso, Semaphore semaforo) {
		this.idProcesso = idProcesso;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		
		try {
			ProcessoOrdem();
			semaforo.acquire();
			ProcessoRodando();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			ProcessoFinalizou();
		}
	}

	private void ProcessoOrdem() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("#" + idProcesso + " SOLICITOU RECURSO");
	}

	private void ProcessoRodando() {
		System.out.println("\n#" + idProcesso + " ESTÁ RODANDO\n");
		int tempo = (int) Math.floor(Math.random() * (120000 - 4000)) + 4000;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void ProcessoFinalizou() {
		posicaoSaida++;
		System.out.println("#" + idProcesso + " foi o " + posicaoSaida + "o. a sair");
	}
}
