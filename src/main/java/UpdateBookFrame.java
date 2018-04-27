import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UpdateBookFrame extends JFrame implements ActionListener {
    public static HashMap<String, Enum> hmap = new HashMap<String, Enum>();
    private JButton submit,cancel;
    private DB_Books book;
    private JTextField authorBook, nameBook, descriptionBook, categoryBook, numberOfPageBook, releaseDateBook;
    private JComboBox forState;
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    private ImageIcon titleIcon = new ImageIcon("src\\main\\resources\\Icons\\setting.png");

    UpdateBookFrame(long id) {
        super("Modyfikuj");
        setIconImage(titleIcon.getImage());
        setSize(260, 320);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 5, 10));
        book = entityManager.find(DB_Books.class, id);
        JLabel author = new JLabel("Author");
        add(author);
        authorBook = new JTextField(book.getAuthor());
        authorBook.setPreferredSize(new Dimension(100,100));
        add(authorBook);
        JLabel name = new JLabel("Nazwa");
        add(name);
        nameBook = new JTextField(book.getName());
        add(nameBook);
        JLabel category = new JLabel("Gatunek");
        add(category);
        categoryBook = new JTextField(book.getType());
        add(categoryBook);
        JLabel description = new JLabel("Opis");
        add(description);
        descriptionBook = new JTextField(String.valueOf(book.getDescription()));
        add(descriptionBook);
        JLabel numbeOfpage = new JLabel("Liczba stron");
        add(numbeOfpage);
        numberOfPageBook = new JTextField(String.valueOf(book.getNumberOfPages()));
        add(numberOfPageBook);
        JLabel releaseDate = new JLabel("Rok wydania");
        add(releaseDate);
        releaseDateBook = new JTextField(String.valueOf(book.getReleaseDate()));
        add(releaseDateBook);
        JLabel state = new JLabel("Stan");
        add(state);
        for (DB_Books.State states : DB_Books.State.values()) {
            hmap.put(states.getName(), states);
        }
        forState = new JComboBox();
        Set set = hmap.entrySet();
        for (Object aSet : set) {
            Map.Entry mentry = (Map.Entry) aSet;
            forState.addItem(mentry.getKey());
        }
        add(forState);
        submit = new JButton("Aktualizuj");
        submit.addActionListener(this);
        add(submit);
        cancel = new JButton("Anuluj");
        cancel.addActionListener(this);
        add(cancel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(submit)) {
            try {
                entityManager.getTransaction().begin();
                book.setAuthor(authorBook.getText());
                book.setName(nameBook.getText());
                book.setType(categoryBook.getText());
                book.setDescription(descriptionBook.getText());
                book.setNumberOfPages(Integer.valueOf(numberOfPageBook.getText()));
                book.setReleaseDate(Integer.valueOf(releaseDateBook.getText()));
                book.setState(DB_Books.State.valueOf(hmap.get(forState.getSelectedItem()).toString()));
                entityManager.getTransaction().commit();
                BooksPanel.absModel.setData(Library.entityGetBooks());

            } catch (Exception exp) {
                System.out.println("Błąd: " + exp);
            }
            finally {
                entityManager.close();
                entityManagerFactory.close();
                dispose();
            }

        }
        if (source.equals(cancel)){dispose();}
    }
}
