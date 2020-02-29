import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DisplayView extends JFrame{

    JComboBox<String> codesList = new JComboBox<>();



    public void createDisplayView() {
        this.setTitle("Конвертер валют");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400,200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Container container = this.getContentPane();
        container.add(codesList);
    }

    public void addItems(List<TableData> tableData) {
        for (TableData tableItemCode : tableData) {
            codesList.addItem(tableItemCode.getCode());
        }
    }
}
