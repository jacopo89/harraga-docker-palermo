package it.altran.harraga.model;

public class SectionHistory {

	private Long socialCardId;
	private String username;
	private Long timestamp;
	
	public SectionHistory() {
		
	}

	public SectionHistory(long socialCardId, String username, Long timestamp) {
		super();
		this.socialCardId = socialCardId;
		this.username = username;
		this.timestamp = timestamp;
	}

	public Long getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(Long socialCardId) {
		this.socialCardId = socialCardId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	
	
	
	
	
}
