import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class UsersTableABSModel extends AbstractTableModel {
    String[] columnName = {"Imię", "Nazwisko","Pesel","Poziom uprawnień","Liczba wypożyczonych książek","id","id_book"};
    String[][] tableData ={{"","","","","","",""}};

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
        return false;
    }

    @Override
    public int getRowCount() {
        return tableData.length;
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    public String getColumnName(int col) {
        return columnName[col];
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {

        return tableData[rowIndex][columnIndex];
    }

    public void setData(String[][] data) {
        tableData = data;
        fireTableDataChanged();

    }
}
