package p03.c01.variasEntradas;

import java.util.Random;

public class Puerta implements Runnable {
	private int numeroPuerta;
	private ParqueVariasEntradas parque;
	private int capacidadPuerta;
	
	public Puerta(ParqueVariasEntradas parque, int puerta, int capacidadPuerta) {
		this.numeroPuerta = puerta;
		this.parque = parque;
		this.capacidadPuerta = capacidadPuerta;
	}

	@Override
	public void run() {
		Random rdm = new Random();

		for (int i = 0; i < capacidadPuerta; i++) {
			parque.entrar(numeroPuerta);
			try {
				Thread.sleep((long) (rdm.nextDouble() * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public int getNumeroPuerta(){
		return numeroPuerta;
	}

}
