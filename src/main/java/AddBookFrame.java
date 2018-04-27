import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AddBookFrame extends JFrame implements ActionListener{
    private JTextField forName,forType,forAuthor,forReleaseDate,forNumberOfPages;
    private JComboBox forState;
    private JLabel error,systemError;
    private JButton submit,cancel;
    private JTextArea forDescriptions;
    public static HashMap<String, Enum> hmap = new HashMap<String, Enum>();
    private ImageIcon titleIcon = new ImageIcon("src\\main\\resources\\Icons\\plus.png");
    AddBookFrame(){
        super("Dodaj książke");
        setLayout(new GridLayout(0,2,5,10));
        setSize(300,360);
        setIconImage(titleIcon.getImage());
        JLabel name=new JLabel("Nazwa książki");
        add(name);
        forName=new JTextField(3);
        forName.setPreferredSize(new Dimension(100,100));
        add(forName);
        JLabel type=new JLabel("Gatunek");
        add(type);
        forType=new JTextField(3);
        add(forType);
        JLabel author=new JLabel("Author");
        add(author);
        forAuthor=new JTextField(3);
        add(forAuthor);
        JLabel releaseDate = new JLabel("Rok publikacji");
        add(releaseDate);
        forReleaseDate = Library.createFilteredFieldNumberOnly(4);
        add(forReleaseDate);
        JLabel numberOfPages = new JLabel("Liczba stron");
        add(numberOfPages);
        forNumberOfPages = Library.createFilteredFieldNumberOnly(7);
        add(forNumberOfPages);
        JLabel state = new JLabel("Stan");
        add(state);
        for(DB_Books.State states:DB_Books.State.values()){
            hmap.put(states.getName(),states);
        }
        forState = new JComboBox();
        Set set = hmap.entrySet();
        for (Object aSet : set) {
            Map.Entry mentry = (Map.Entry) aSet;
            forState.addItem(mentry.getKey());
        }
        add(forState);
        JLabel descriptions = new JLabel("Opis");
        add(descriptions);
        forDescriptions = new JTextArea(3,3);
        forDescriptions.setEditable(true);
        JScrollPane jScrollPane = new JScrollPane(forDescriptions);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jScrollPane);
        submit = new JButton("Dodaj");
        submit.addActionListener(this);
        add(submit);
        cancel = new JButton("Anuluj");
        cancel.addActionListener(this);
        add(cancel);
        error = new JLabel("Błąd w danych");
        error.setVisible(false);
        add(error);
        systemError = new JLabel();
        systemError.setVisible(false);
        add(systemError);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==cancel) {
            dispose();
        }
        if(source==submit){
            try{
                DB_Books db_books = new DB_Books();
                db_books.setName(forName.getText());
                forAuthor.setText(forAuthor.getText().trim());
                if(forAuthor.getText().equals("")){forAuthor.setText("Nieznany");}
                db_books.setAuthor(forAuthor.getText());
                if(forReleaseDate.getText().equals("")){forReleaseDate.setText("0");}
                db_books.setReleaseDate(Integer.parseInt(forReleaseDate.getText().trim()));
                if(forNumberOfPages.getText().equals("")){forNumberOfPages.setText("0");}
                db_books.setNumberOfPages(Integer.parseInt(forNumberOfPages.getText().trim()));
                db_books.setDescription(forDescriptions.getText());
                db_books.setType(forType.getText());
                db_books.setState(DB_Books.State.valueOf(hmap.get(forState.getSelectedItem()).toString()));
                db_books.setRent(false);
                Library.entityPersist(db_books);
                BooksPanel.absModel.setData(Library.entityGetBooks());
                dispose();
            }
            catch (Exception exp){
                error.setVisible(true);
                systemError.setText(exp.toString());
                systemError.setVisible(true);

            }
        }
        }
    }

