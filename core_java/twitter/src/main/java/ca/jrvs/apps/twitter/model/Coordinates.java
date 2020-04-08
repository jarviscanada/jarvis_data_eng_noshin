package ca.jrvs.apps.twitter.model;

public class Coordinates {

  private float[] longLat;
  private String type;

  public float[] getLongLat() {
    return longLat;
  }

  public void setLongLat(float[] coordinates) {
    this.longLat = coordinates;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
