package codingcompetition2019;

public class DisasterDescription {
    private String country;
    private String countryCode;
    private String year;
    private String category;
    private int num = 0;

    public DisasterDescription(String country, String countryCode, String year) {
        this.country = country;
        this.countryCode = countryCode;
        this.year = year;
    }

    public DisasterDescription(String category, String year,int num) {
        this.category = category;
        this.year = year;
        this.num = num;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReportedIncidentsNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    // TODO finish this class
}
