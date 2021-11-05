package it.altran.harraga.utils;

import java.util.ArrayList;

public class Permission {

	ArrayList<sezioni> read;
	ArrayList<sezioni> write;

	public Permission(ArrayList<sezioni> read, ArrayList<sezioni> write) {
		this.read = read;
		this.write = write;

	}



	public ArrayList<sezioni> getRead() {
		return read;
	}
	public void setRead(ArrayList<sezioni> read) {
		this.read = read;
	}
	public ArrayList<sezioni> getWrite() {
		return write;
	}
	public void setWrite(ArrayList<sezioni> write) {
		this.write = write;
	}
	
	
	
	public enum sezioni {
		ANAGRAFICA, AMMINISTRATIVA, STORIA, ISTRUZIONE, LAVORO, COMPETENZE, SOCIALE, DESIDERI, PENALE, SANITARIA, PERMESSO_SOGGIORNO
	}
	

}
