import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class DisplayView extends JFrame implements ItemListener{

    private List<TableData> tableData;

    public DisplayView(List<TableData> tableData) {
        this.tableData = tableData;
    }

    public void setInitRateField(List<TableData> tableData) {
        rateField.setText(tableData.get(0).getCentralBankRate());
    }

    private JTextField rateField = new JTextField();

    private JComboBox<String> codesList = new JComboBox<>();

    public void createDisplayView() {
        this.setTitle("Конвертер валют");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400,200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout());

        rateField.setEditable(false);

        container.add(rateField);
        container.add(codesList);

        codesList.addItemListener(this);

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
            rateField.setText(tableData.get(item).getCentralBankRate());
        }
    }

}
