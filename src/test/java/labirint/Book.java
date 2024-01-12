package labirint;

public class Book {
  private String series;
  private int weight;
  private boolean onSklad;

  public Book(String series, int weight, boolean onSklad) {
    this.series = series;
    this.weight = weight;
    this.onSklad = onSklad;
  }

  public String getSeries() {
    return series;
  }

  public int getWeight() {
    return weight;
  }

  public boolean isOnSklad() {
    return onSklad;
  }


}
