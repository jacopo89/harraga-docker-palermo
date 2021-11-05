package it.altran.harraga.model.desideri;

public class DesideriHistory {

	private long socialCardId;
	private String username;
	private long timestamp;
	private String type;
	
	public DesideriHistory() {
		
	}

	public DesideriHistory(long socialCardId, String username, long timestamp) {
		super();
		this.socialCardId = socialCardId;
		this.username = username;
		this.timestamp = timestamp;
	}

	public long getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(long socialCardId) {
		this.socialCardId = socialCardId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
	
	
}
