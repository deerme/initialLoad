package com.euge.initialload.demon;


public class migracion implements Runnable{
	private work miTrabajo;
	
	private static boolean lanzada=false;
	public static boolean getEstado() {
		return lanzada;
	}
	private static long duracion;
	public static long getRunningTime() {
		return duracion/1000;
	}
	
	public migracion(work trabajo) {
		this.miTrabajo=trabajo;
	}
	
	@Override
	public void run() {
		if(lanzada)return;
		lanzada=true;
		//Updates status and stops the chronograph
		long start=System.currentTimeMillis();
		
		while(lanzada) {
			
			if(!miTrabajo.procesaUnitWork())break;

		};
		duracion=System.currentTimeMillis()-start;
		lanzada=false;
	}

}
