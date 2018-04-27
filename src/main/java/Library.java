import javax.persistence.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Library extends JFrame implements ActionListener {
    public static CardLayout cardLayout = new CardLayout();
    public static JPanel mainComponent;
    private ImageIcon titleIcon = new ImageIcon("src\\main\\resources\\Icons\\library.png");

    Library() {
        super("Biblioteka");
        setIconImage(titleIcon.getImage());
        setSize(900, 500);
        setMinimumSize(new Dimension(900, 500));
        setLayout(new BorderLayout());
        OptionPanel optionPanel = new OptionPanel();
        add(optionPanel, BorderLayout.NORTH);
        mainComponent = new JPanel(cardLayout);
        BooksPanel booksPanel = new BooksPanel();
        mainComponent.add(booksPanel, "books");
        UsersPanel usersPanel = new UsersPanel();
        mainComponent.add(usersPanel,"users");
        add(mainComponent, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        /*DB_Users superUser = new DB_Users();
        superUser.setLogin("admin");
        superUser.setName("Administrator");
        superUser.setPassword("admin");
        entityPersist(superUser);*/
        Library library = new Library();
        //LoginFrame loginFrame = new LoginFrame();
    }

    public static void entityPersist(Object object) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static String[][] entityGetBooks() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String[][] results = null;
        try {
            TypedQuery<DB_Books> query = entityManager.createQuery("select b from books b", DB_Books.class);
            results = new String[query.getResultList().size()][6];
            int i = 0;
            for (DB_Books book : query.getResultList()) {
                String[] bookValues = new String[]{book.getName(), book.getType(), book.getAuthor(), book.getState().getName().toString(), rentToString(book.isRent()),Long.toString(book.getId())};
                for (int j = 0; j < 6; j++) {
                    results[i][j] = bookValues[j];
                }
                ++i;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        if (results == null) {
            return null;
        } else {
            return results;
        }
    }
    public static String rentToString(Boolean a ){
        if (a)return "Tak";
        else return "Nie";
    }
    public static DB_Books entityGetSingleBook(long id){
        DB_Books book=null;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<DB_Books> query = entityManager.createQuery("from books u where u.id = :id", DB_Books.class);
        query.setParameter("id", id);
        try {
            book = query.getSingleResult();
        } catch (Exception exc) {
            System.out.println("Błąd: "+exc);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return book;
    }

    public static void entityDeleteBook(long id) {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("delete from books where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.close();
            entityManagerFactory.close();
            BooksPanel.absModel.setData(Library.entityGetBooks());
        } catch (Exception e) {
            System.out.println("Blad library:" + e);
        } finally {
            BooksPanel.resultsTable.clearSelection();

        }
    }

    public void actionPerformed(ActionEvent e) {

    }
    //https://stackoverflow.com/questions/24844559/jtextfield-using-document-filter-to-filter-integers-and-periods
    public static JTextField createFilteredFieldNumberOnly(int maxCharacterr) {
        JTextField field = new JTextField();
        AbstractDocument document = (AbstractDocument) field.getDocument();
        final int maxCharacters = maxCharacterr ;
        document.setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length,
                                String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]?$")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            public void insertString(DocumentFilter.FilterBypass fb, int offs, String str,
                                     AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]?$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        return field;
    }
}
