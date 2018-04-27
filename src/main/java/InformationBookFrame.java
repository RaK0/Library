import javax.swing.*;
import java.awt.*;

public class InformationBookFrame extends JFrame  {
    private ImageIcon titleIcon = new ImageIcon("src\\main\\resources\\Icons\\information.png");
    InformationBookFrame(long id){
        super("Informacje");
        setIconImage(titleIcon.getImage());
        setSize(260,400);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0,2,5,10));
        DB_Books book = Library.entityGetSingleBook(id);
        JLabel author = new JLabel("Author");
        add(author);
        JLabel authorBook = new JLabel(book.getAuthor());
        add(authorBook);
        JLabel name = new JLabel("Nazwa");
        add(name);
        JLabel nameBook = new JLabel(book.getName());
        add(nameBook);
        JLabel category = new JLabel("Gatunek");
        add(category);
        JLabel caregoryBook = new JLabel(book.getType());
        add(caregoryBook);
        JLabel description = new JLabel("Opis");
        add(description);
        if (book.getDescription().equals("")) {
            JLabel descriptionBook = new JLabel("Brak");
            add(descriptionBook);
        }
        else {
            JLabel descriptionBook = new JLabel(String.valueOf(book.getNumberOfPages()));
            add(descriptionBook);
        }
        JLabel numberOfPage = new JLabel("Liczba stron");
        add(numberOfPage);
        if (book.getNumberOfPages()==0) {
            JLabel numberOfPageBook = new JLabel("Nieznana");
            add(numberOfPageBook);
        }
        else {
            JLabel numberOfPageBook = new JLabel(String.valueOf(book.getNumberOfPages()));
            add(numberOfPageBook);
        }
        JLabel releaseDate = new JLabel("Rok wydania");
        add(releaseDate);
        if (book.getReleaseDate()==0) {
            JLabel releaseDateBook = new JLabel("Nieznany");
            add(releaseDateBook);
        }
        else {
            JLabel releaseDateBook = new JLabel(String.valueOf(book.getNumberOfPages()));
            add(releaseDateBook);
        }
        JLabel state = new JLabel("Stan");
        add(state);
        JLabel stateBook = new JLabel(book.getState().getName());
        add(stateBook);


        setVisible(true);
    }
}
