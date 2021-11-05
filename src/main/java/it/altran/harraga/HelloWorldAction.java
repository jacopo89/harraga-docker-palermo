package it.altran.harraga;

public class HelloWorldAction{
	private String name;
	private String secondname;

	public String execute() throws Exception {
		System.out.println("as as asa ssdas ");
		
		return "success";
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	}