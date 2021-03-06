import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class DisplayView extends JFrame implements ItemListener {

    private List<TableData> tableData;
    private final static int LIMIT_DIGIT = 6;

    public DisplayView(List<TableData> tableData) {
        this.tableData = tableData;
    }

    public void setInitRateField(List<TableData> tableData) {
        rateLabel.setText(tableData.get(0).getCentralBankRate());
        currencyTitleLabel.setText(tableData.get(0).getCurrencyTitle());
        courseChangeLabel.setText("   " + tableData.get(0).getCourseChange() + "   ");
        copyCurrencyTitleField.setText(String.valueOf(codesList.getSelectedItem()));
        if (tableData.get(0).getCourseChange().contains("+")) {
            courseChangeLabel.setForeground(Color.getHSBColor(0.35f, 1, 0.6f));
        } else {
            courseChangeLabel.setForeground(Color.RED);
        }
    }

    private JLabel rateLabel = new JLabel();
    private JLabel currencyTitleLabel = new JLabel();
    private JLabel courseChangeLabel = new JLabel();

    private JTextField amountToConvert = new JTextField(20);

    private JTextField result = new JTextField(20);

    private JComboBox<String> codesList = new JComboBox<>();

    private JLabel rusLabel = new JLabel();
    private JLabel copyCurrencyTitleField = new JLabel();

    PlainDocument plainDocument;

    public void createDisplayView() {
        this.setTitle("Конвертер валют");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());

        GridBagConstraints rateLabelGridBag = new GridBagConstraints();
        rateLabelGridBag.gridheight = 1;
        rateLabelGridBag.gridwidth = 1;
        rateLabelGridBag.gridx = 0;
        rateLabelGridBag.gridy = 1;
        rateLabelGridBag.weightx = 0;
        rateLabelGridBag.weighty = 0;

        GridBagConstraints codesListGridBag = new GridBagConstraints();
        codesListGridBag.gridheight = 1;
        codesListGridBag.gridwidth = 1;
        codesListGridBag.gridx = 2;
        codesListGridBag.gridy = 1;
        codesListGridBag.weightx = 0;
        codesListGridBag.weighty = 0;

        GridBagConstraints currencyTitleGridBag = new GridBagConstraints();
        currencyTitleGridBag.gridheight = 1;
        currencyTitleGridBag.gridwidth = 0;
        currencyTitleGridBag.gridx = 0;
        currencyTitleGridBag.gridy = 0;
        currencyTitleGridBag.weightx = 0;
        currencyTitleGridBag.weighty = 0;

        GridBagConstraints courseGridBag = new GridBagConstraints();
        courseGridBag.gridheight = 1;
        courseGridBag.gridwidth = 1;
        courseGridBag.gridx = 1;
        courseGridBag.gridy = 1;
        courseGridBag.weightx = 0;
        courseGridBag.weighty = 0;

        GridBagConstraints amountToConvertGridBag = new GridBagConstraints();
        amountToConvertGridBag.gridheight = 1;
        amountToConvertGridBag.gridwidth = 2;
        amountToConvertGridBag.gridx = 0;
        amountToConvertGridBag.gridy = 2;
        amountToConvertGridBag.weightx = 0;
        amountToConvertGridBag.weighty = 0;

        GridBagConstraints resultGridBag = new GridBagConstraints();
        resultGridBag.gridheight = 1;
        resultGridBag.gridwidth = 2;
        resultGridBag.gridx = 0;
        resultGridBag.gridy = 3;
        resultGridBag.weightx = 0;
        resultGridBag.weighty = 0;

        GridBagConstraints rusLabelGridBag = new GridBagConstraints();
        rusLabelGridBag.gridheight = 1;
        rusLabelGridBag.gridwidth = 2;
        rusLabelGridBag.gridx = 2;
        rusLabelGridBag.gridy = 2;
        rusLabelGridBag.weightx = 0;
        rusLabelGridBag.weighty = 0;

        GridBagConstraints copyCurrencyTitleBag = new GridBagConstraints();
        copyCurrencyTitleBag.gridheight = 1;
        copyCurrencyTitleBag.gridwidth = 2;
        copyCurrencyTitleBag.gridx = 2;
        copyCurrencyTitleBag.gridy = 3;
        copyCurrencyTitleBag.weightx = 0;
        copyCurrencyTitleBag.weighty = 0;

        container.add(rateLabel, rateLabelGridBag);
        container.add(codesList, codesListGridBag);
        container.add(currencyTitleLabel, currencyTitleGridBag);
        container.add(courseChangeLabel, courseGridBag);
        container.add(amountToConvert, amountToConvertGridBag);
        container.add(result, resultGridBag);
        container.add(rusLabel, rusLabelGridBag);
        container.add(copyCurrencyTitleField, copyCurrencyTitleBag);

        plainDocument = (PlainDocument) amountToConvert.getDocument();
        plainDocument.setDocumentFilter(new DigitFilter("\\d+"));

        result.setEditable(false);
        rusLabel.setText("RUS");

        codesList.addItemListener(this);

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                getResult(plainDocument);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                getResult(plainDocument);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                getResult(plainDocument);
            }
        };

        amountToConvert.getDocument().addDocumentListener(listener);

    }

    public void addItems(List<TableData> tableData) {
        for (TableData tableItemCode : tableData) {
            codesList.addItem(tableItemCode.getCode());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getStateChange() == ItemEvent.SELECTED) {
            int item = codesList.getSelectedIndex();
            rateLabel.setText(tableData.get(item).getCentralBankRate());
            currencyTitleLabel.setText(tableData.get(item).getCurrencyTitle());
            courseChangeLabel.setText("   " + tableData.get(item).getCourseChange() + "   ");
            copyCurrencyTitleField.setText(String.valueOf(codesList.getSelectedItem()));
            if (tableData.get(item).getCourseChange().contains("+")) {
                courseChangeLabel.setForeground(Color.getHSBColor(0.35f, 1, 0.6f));
            } else {
                courseChangeLabel.setForeground(Color.RED);
            }
            getResult(plainDocument);
        }

    }

    public void getResult(PlainDocument plainDocument) {
        String amount = amountToConvert.getText();
        if (amount.equals("")) {
            result.setText("");
            return;
        }
        if (amount.matches("\\d{0," + LIMIT_DIGIT + "}")) {
            String mutable = rateLabel.getText();

            int unitIndex = codesList.getSelectedIndex();

            double resultAmount = (Double.parseDouble(amount) * Double.parseDouble(mutable)) /
                    Double.parseDouble(tableData.get(unitIndex).getUnitIndex());

            resultAmount = Math.round(resultAmount * 100);

            result.setText(String.valueOf(resultAmount / 100));
            plainDocument.setDocumentFilter(new DigitFilter("\\d+"));
        } else {
            plainDocument.setDocumentFilter(new DigitFilter(""));
        }
    }

}
