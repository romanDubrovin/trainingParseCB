import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main  {

    final static String URL = "https://www.banki.ru/products/currency/cb/";

    public static void main(String[] args) throws IOException {

        List<TableData> tableData = new ArrayList<>();

        //try {
            Document document = Jsoup.connect(URL).get();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        Elements trTableElements = document.getElementsByAttributeValue("data-test", "currency-table-row");

        trTableElements.forEach(trTableElement -> {
            String codeElement = trTableElement.attr("data-currency-code");
            String currencyTitle = trTableElement.attr("data-currency-name");
            String centralBankRate = trTableElement.child(3).text();
            String courseChange = trTableElement.child(4).text();

            tableData.add(new TableData(codeElement, currencyTitle, centralBankRate, courseChange));
        });

        tableData.forEach(System.out::println);

        DisplayView displayView = new DisplayView(tableData);
        displayView.addItems(tableData);
        displayView.createDisplayView();
        displayView.setInitRateField(tableData);

    }

}
