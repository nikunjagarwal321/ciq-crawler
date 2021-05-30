package commerceIq.crawler.model.responseModels;

/**
  * Response DTO for Ratings Map
  */
public class RatingsMap {
    private String ratingsCount;
    private String Fivestar;
    private String Fourstar;
    private String Threestar;
    private String Twostar;
    private String Onestar;

    public RatingsMap() {
    }

    public RatingsMap(String ratingsCount, String Fivestar, String Fourstar, String Threestar, String Twostar, String Onestar) {
        this.ratingsCount = ratingsCount;
        this.Fivestar = Fivestar;
        this.Fourstar = Fourstar;
        this.Threestar = Threestar;
        this.Twostar = Twostar;
        this.Onestar = Onestar;
    }

    public String getRatingsCount() {
        return this.ratingsCount;
    }

    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getFivestar() {
        return this.Fivestar;
    }

    public void setFivestar(String Fivestar) {
        this.Fivestar = Fivestar;
    }

    public String getFourstar() {
        return this.Fourstar;
    }

    public void setFourstar(String Fourstar) {
        this.Fourstar = Fourstar;
    }

    public String getThreestar() {
        return this.Threestar;
    }

    public void setThreestar(String Threestar) {
        this.Threestar = Threestar;
    }

    public String getTwostar() {
        return this.Twostar;
    }

    public void setTwostar(String Twostar) {
        this.Twostar = Twostar;
    }

    public String getOnestar() {
        return this.Onestar;
    }

    public void setOnestar(String Onestar) {
        this.Onestar = Onestar;
    }

    @Override
    public String toString() {
        return "{" +
            " ratingsCount='" + getRatingsCount() + "'" +
            ", Fivestar='" + getFivestar() + "'" +
            ", Fourstar='" + getFourstar() + "'" +
            ", Threestar='" + getThreestar() + "'" +
            ", Twostar='" + getTwostar() + "'" +
            ", Onestar='" + getOnestar() + "'" +
            "}";
    }


}
