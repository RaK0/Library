import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class AddUserFrame extends JFrame implements ActionListener{
    private JTextField forName, forSurName,forPesel;
    private JComboBox forAccessLevel;
    private JPasswordField forPassword;
    private JLabel error,systemError;
    private JButton submit,cancel;
    private ImageIcon titleIcon = new ImageIcon("src\\main\\resources\\Icons\\plus.png");
    AddUserFrame(){
        super("Dodaj użytkownika");
        setLayout(new GridLayout(0,2,5,10));
        setSize(280,320);
        setIconImage(titleIcon.getImage());
        JLabel name=new JLabel("Imię");
        add(name);
        forName=new JTextField();
        forName.setPreferredSize(new Dimension(100,100));
        add(forName);
        JLabel surname=new JLabel("Nazwisko");
        add(surname);
        forSurName =new JTextField();
        add(forSurName);
        JLabel pesel=new JLabel("Pesel");
        add(pesel);
        forPesel = Library.createFilteredFieldNumberOnly(11);
        add(forPesel);
        JLabel accessLevel = new JLabel("Poziom uprawnień");
        add(accessLevel);
        String[] accessLevelCombo ={"Klient","Pracownik","Administrator"};
        forAccessLevel = new JComboBox(accessLevelCombo);
        add(forAccessLevel);
        JLabel numberOfPages = new JLabel("Hasło");
        add(numberOfPages);
        forPassword = new JPasswordField();
        add(forPassword);
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
                DB_Users user = new DB_Users();
                user.setName(forName.getText().trim());
                user.setPassword(String.valueOf(forPassword.getPassword()));
                if (forPesel.getText().equals("")){forPesel.setText("0");}
                user.setPesel(Long.valueOf(forPesel.getText()));
                user.setSurname(forSurName.getText());
                user.setLogin(forName.getText().trim()+"."+forSurName.getText().trim());
                Library.entityPersist(user);
                dispose();
            }
            catch (Exception exp){
                error.setVisible(true);
                System.out.println(exp);
                systemError.setText(exp.toString());
                systemError.setVisible(true);

            }
        }
    }
}

