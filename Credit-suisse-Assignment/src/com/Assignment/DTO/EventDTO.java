package com.Assignment.DTO;

public class EventDTO {
	
	private String eventId;
	private String eventType;
	private String eventTimeStamp;
	private String hostName;
	private String eventState;
	
	
	public EventDTO(String eventId, String eventType, String eventTimeStamp, String hostName, String eventState) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.eventTimeStamp = eventTimeStamp;
		this.hostName = hostName;
		this.eventState = eventState;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventTimeStamp() {
		return eventTimeStamp;
	}
	public void setEventTimeStamp(String eventTimeStamp) {
		this.eventTimeStamp = eventTimeStamp;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getEventState() {
		return eventState;
	}
	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	
	
}
