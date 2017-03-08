package p03.c01;

import java.util.Random;

public class Puerta implements Runnable {
	String nombrePuerta;
	ParqueDosEntradas parque;
	int capacidadPuerta;

	public Puerta(ParqueDosEntradas parque, String puerta, int capacidadPuerta) {
		this.nombrePuerta = puerta;
		this.parque = parque;
		this.capacidadPuerta = capacidadPuerta;
	}

	@Override
	public void run() {
		Random rdm = new Random();

		for (int i = 0; i < capacidadPuerta; i++) {
			parque.entrar(nombrePuerta);
			try {
				Thread.sleep((long) (rdm.nextDouble() * 1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
