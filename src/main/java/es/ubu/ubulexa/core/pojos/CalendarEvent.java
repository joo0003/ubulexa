package es.ubu.ubulexa.core.pojos;

public class CalendarEvent {

  private String eventId;

  private String courseId;
  private String name;
  private Integer timestart;
  private String description;

  private String eventDate;
  private String eventTitle;

  public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public String getEventTitle() {
    return eventTitle;
  }

  public void setEventTitle(String eventTitle) {
    this.eventTitle = eventTitle;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public Integer getTimestart() {
    return timestart;
  }

  public void setTimestart(Integer timestart) {
    this.timestart = timestart;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
