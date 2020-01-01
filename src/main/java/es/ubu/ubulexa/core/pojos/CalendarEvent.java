package es.ubu.ubulexa.core.pojos;

public class CalendarEvent {

  private String courseId;
  private String name;
  private String eventId;
  private Integer timestart;
  private String description;

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
