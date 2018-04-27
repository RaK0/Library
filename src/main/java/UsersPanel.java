import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersPanel extends JPanel implements ActionListener {
    private JComboBox viewBox;
    private JButton submit, addUser, deleteUser, modifyUser, aboutUser;
    private GridBagConstraints gbc = new GridBagConstraints();
    public static UsersTableABSModel absModel = new UsersTableABSModel();
    public static JTable resultsTable;
    public static String selectedRow;

    UsersPanel() {
        super(new BorderLayout());
        JPanel contFilter = new JPanel(new BorderLayout());
        JPanel filter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filter.setBackground(Color.decode("#ccccff"));
        JLabel view = new JLabel("Pokaż:");
        filter.add(view);
        String[] viewBoxItems = {"Klientów", "Każdego", "Pracowników", "Administratorów"};
        viewBox = new JComboBox(viewBoxItems);
        filter.add(viewBox);
        JLabel name = new JLabel("Imię");
        filter.add(name);
        JTextField nameField = new JTextField(10);
        filter.add(nameField);
        JLabel surname = new JLabel("Nazwisko");
        filter.add(surname);
        JTextField surnameField = new JTextField(10);
        filter.add(surnameField);
        JLabel pesel = new JLabel("Pesel");
        filter.add(pesel);
        JTextField peselField = new JTextField(10);
        filter.add(peselField);
        JPanel filter2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filter2.setBackground(Color.decode("#ccccff"));
        JButton submit = new JButton("Szukaj");
        filter2.add(submit);
        contFilter.add(filter, BorderLayout.CENTER);
        contFilter.add(filter2,BorderLayout.EAST);
        add(contFilter, BorderLayout.NORTH);
        JPanel modification = new JPanel(new GridBagLayout());
        modification.setBackground(Color.decode("#ccccff"));
        gbc.insets = new Insets(10, 10, 10, 10);
        ImageIcon plusIcon = new ImageIcon("src\\main\\resources\\Icons\\plus.png");
        addUser = new JButton("Dodaj", plusIcon);
        addUser.setPreferredSize(new Dimension(130, 40));
        addUser.addActionListener(this);
        setGbc(0, 0, 1);
        modification.add(addUser, gbc);
        ImageIcon deleteIcon = new ImageIcon("src\\main\\resources\\Icons\\delete.png");
        deleteUser = new JButton("Usuń", deleteIcon);
        deleteUser.setPreferredSize(new Dimension(130, 40));
        deleteUser.addActionListener(this);
        setGbc(0, 1, 1);
        modification.add(deleteUser, gbc);
        ImageIcon informationIcon = new ImageIcon("src\\main\\resources\\Icons\\information.png");
        aboutUser = new JButton("Informacje", informationIcon);
        aboutUser.setPreferredSize(new Dimension(130, 40));
        aboutUser.addActionListener(this);
        setGbc(0, 2, 1);
        modification.add(aboutUser, gbc);
        ImageIcon settingIcon = new ImageIcon("src\\main\\resources\\Icons\\setting.png");
        modifyUser = new JButton("Modyfikuj", settingIcon);
        modifyUser.setPreferredSize(new Dimension(130, 40));
        modifyUser.addActionListener(this);
        setGbc(0, 3, 1);
        modification.add(modifyUser, gbc);
        add(modification, BorderLayout.EAST);
        JPanel results = new JPanel(new GridLayout());
        absModel = new UsersTableABSModel();
        resultsTable = new JTable();
        resultsTable.setModel(absModel);
        resultsTable.removeColumn(resultsTable.getColumnModel().getColumn(6));
        resultsTable.removeColumn(resultsTable.getColumnModel().getColumn(5));
        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsTable.setFocusable(false);
        JScrollPane resultsScroll = new JScrollPane(resultsTable);
        results.add(resultsScroll);
        add(results,BorderLayout.CENTER);
        setVisible(true);
    }
    private void setGbc(int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(addUser)){AddUserFrame addUserFrame=new AddUserFrame();}
    }
}
