package org.culturaandroid.flisol;

public class Horario {
	private String hora;
	private String tema;
	private String encargado;
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getEncargado() {
		return encargado;
	}
	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}
	
	public Horario(String hora, String tema, String encargado) {
		this.hora = hora;
		this.tema = tema;
		this.encargado = encargado;
	}
}
