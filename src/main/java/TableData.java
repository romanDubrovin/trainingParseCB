public class TableData {

    private String code;
    private String currencyTitle;
    private String  centralBankRate;
    private String courseChange;

    public TableData(String code, String currencyTitle, String  centralBankRate, String courseChange) {
        this.code = code;
        this.currencyTitle = currencyTitle;
        this.centralBankRate = centralBankRate;
        this.courseChange = courseChange;
    }

    public String getCode() {
        return code;
    }

    public String getCurrencyTitle() {
        return currencyTitle;
    }

    public String  getCentralBankRate() {
        return centralBankRate;
    }

    public String getCourseChange() {
        return courseChange;
    }

    @Override
    public String toString() {
        return "TableData{" +
                "code='" + code + '\'' +
                ", currencyTitle='" + currencyTitle + '\'' +
                ", centralBankRate='" + centralBankRate + '\'' +
                ", courseChange='" + courseChange + '\'' +
                '}';
    }
}
