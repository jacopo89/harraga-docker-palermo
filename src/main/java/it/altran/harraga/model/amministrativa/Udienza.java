package it.altran.harraga.model.amministrativa;

public class Udienza {
	private long dataUdienza;
	
	public Udienza(){}
	
	public Udienza(long dataUdienza){
		this.dataUdienza = dataUdienza;
	}

	public long getDataUdienza() {
		return dataUdienza;
	}

	public void setDataUdienza(long dataUdienza) {
		this.dataUdienza = dataUdienza;
	}
}
