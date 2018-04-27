import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BooksPanel extends JPanel implements ActionListener {
    private JComboBox viewBox, categoryBox, authorBox, stateBox;
    private JButton submit, addBook, deleteBook, modifyBook, rentBook, giveBackBook, aboutBook;
    private GridBagConstraints gbc = new GridBagConstraints();
    public static BooksTableABSModel absModel = new BooksTableABSModel();
    public static JTable resultsTable;
    public static String selectedRow;


    BooksPanel() {
        super(new BorderLayout());
        JPanel filter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filter.setBackground(Color.decode("#ccccff"));
        JLabel view = new JLabel("Pokaż");
        filter.add(view);
        String[] viewBoxItems = {"Wszystkie", "Dostępne", "Wypożyczone", "W zamówieniu"};
        viewBox = new JComboBox(viewBoxItems);
        filter.add(viewBox);
        JLabel category = new JLabel("Gatunek");
        filter.add(category);
        String[] categoryBoxItems = {"Wszystkie", "Komedia", "Fantasy", "Horror", "Kryminał", "Dramat", "Dziennik", "Poradnik", "Psychologiczny", "Dokumentalny", "Romans"};
        categoryBox = new JComboBox(categoryBoxItems);
        filter.add(categoryBox);
        JLabel author = new JLabel("Autor");
        filter.add(author);
        /*TODO pobieranie listy autorów z aktualnie dodanych książek */
        String[] authorBoxitems = {"Każdy"};
        authorBox = new JComboBox(authorBoxitems);
        filter.add(authorBox);
        JLabel state = new JLabel("Stan");
        filter.add(state);
        String[] stateBoxItems = {"Każdy", "Bardzo dobry", "Dobry", "Średni", "Słaby"};
        stateBox = new JComboBox(stateBoxItems);
        filter.add(stateBox);
        JLabel name = new JLabel("Nazwa");
        filter.add(name);
        JTextField nameField = new JTextField(10);
        filter.add(nameField);
        submit = new JButton("Szukaj");
        filter.add(submit);
        JPanel results = new JPanel(new GridLayout());
        absModel = new BooksTableABSModel();
        resultsTable = new JTable();
        resultsTable.setModel(absModel);
        resultsTable.removeColumn(resultsTable.getColumnModel().getColumn(5));
        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsTable.setFocusable(false);
        JScrollPane resultsScroll = new JScrollPane(resultsTable);
        results.add(resultsScroll);
        JPanel modification = new JPanel(new GridBagLayout());
        modification.setBackground(Color.decode("#ccccff"));
        gbc.insets = new Insets(10, 10, 10, 10);
        ImageIcon plusIcon = new ImageIcon("src\\main\\resources\\Icons\\plus.png");
        addBook = new JButton("Dodaj", plusIcon);
        addBook.setPreferredSize(new Dimension(130, 40));
        addBook.addActionListener(this);
        setGbc(0, 0, 1);
        modification.add(addBook, gbc);
        ImageIcon deleteIcon = new ImageIcon("src\\main\\resources\\Icons\\delete.png");
        deleteBook = new JButton("Usuń", deleteIcon);
        deleteBook.setPreferredSize(new Dimension(130, 40));
        deleteBook.addActionListener(this);
        setGbc(0, 1, 1);
        modification.add(deleteBook, gbc);
        ImageIcon rentIcon = new ImageIcon("src\\main\\resources\\Icons\\rent.png");
        rentBook = new JButton("Wypożycz", rentIcon);
        rentBook.setPreferredSize(new Dimension(130, 40));
        rentBook.addActionListener(this);
        setGbc(0, 2, 1);
        modification.add(rentBook, gbc);
        ImageIcon backIcon = new ImageIcon("src\\main\\resources\\Icons\\back.png");
        giveBackBook = new JButton("Oddaj", backIcon);
        giveBackBook.setPreferredSize(new Dimension(130, 40));
        giveBackBook.addActionListener(this);
        setGbc(0, 3, 1);
        modification.add(giveBackBook, gbc);
        ImageIcon informationIcon = new ImageIcon("src\\main\\resources\\Icons\\information.png");
        aboutBook = new JButton("Informacje", informationIcon);
        aboutBook.setPreferredSize(new Dimension(130, 40));
        aboutBook.addActionListener(this);
        setGbc(0, 4, 1);
        modification.add(aboutBook, gbc);
        ImageIcon settingIcon = new ImageIcon("src\\main\\resources\\Icons\\setting.png");
        modifyBook = new JButton("Modyfikuj", settingIcon);
        modifyBook.setPreferredSize(new Dimension(130, 40));
        modifyBook.addActionListener(this);
        setGbc(0, 5, 1);
        modification.add(modifyBook, gbc);

        add(filter, BorderLayout.NORTH);
        add(results, BorderLayout.CENTER);
        add(modification, BorderLayout.EAST);
    }

    private void setGbc(int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
    }

    public void setRentBook(long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            DB_Books db_books = entityManager.find(DB_Books.class, id);
            entityManager.getTransaction().begin();
            db_books.setRent(true);
            entityManager.getTransaction().commit();
            BooksPanel.absModel.setData(Library.entityGetBooks());
        } catch (Exception exc) {
            System.out.println("Błąd: " + exc);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void setBackBook(long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            DB_Books db_books = entityManager.find(DB_Books.class, id);
            entityManager.getTransaction().begin();
            db_books.setRent(false);
            entityManager.getTransaction().commit();
            BooksPanel.absModel.setData(Library.entityGetBooks());
        } catch (Exception exc) {
            System.out.println("Błąd: " + exc);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(addBook)) {
            AddBookFrame addBookFrame = new AddBookFrame();
        }
        if (source.equals(deleteBook)) {
            try {
                selectedRow = resultsTable.getModel().getValueAt(resultsTable.getSelectedRow(), 5).toString();
                DeleteBookFrame deleteBookFrame = new DeleteBookFrame();
            } catch (Exception exp) {
                System.out.println("Nic nie wybrano");
            }

        }
        if (source.equals(aboutBook)) {
            try {
                selectedRow = resultsTable.getModel().getValueAt(resultsTable.getSelectedRow(), 5).toString();
                InformationBookFrame informationBookFrame = new InformationBookFrame(Long.valueOf(selectedRow));
            } catch (Exception exp) {
                System.out.println("Nic nie wybrano");
            }

        }
        if (source.equals(rentBook)) {
            try {
                selectedRow = resultsTable.getModel().getValueAt(resultsTable.getSelectedRow(), 5).toString();
                setRentBook(Long.valueOf(selectedRow));
            } catch (Exception exp) {
                System.out.println("Nic nie wybrano");
            }

        }
        if (source.equals(giveBackBook)) {
            try {
                selectedRow = resultsTable.getModel().getValueAt(resultsTable.getSelectedRow(), 5).toString();
                setBackBook(Long.valueOf(selectedRow));
            } catch (Exception exp) {
                System.out.println("Nic nie wybrano");
            }

        }
        if (source.equals(modifyBook)){
            try {
                selectedRow = resultsTable.getModel().getValueAt(resultsTable.getSelectedRow(), 5).toString();
                UpdateBookFrame updateBookFrame = new UpdateBookFrame(Long.valueOf(selectedRow));
            } catch (Exception exp) {
                System.out.println("Nic nie wybrano");
            }
        }
    }
}

