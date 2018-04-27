import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel loginInfo;
    private JTextField forLogin, forPassword;
    private JButton loginButton, resetPasswordButton;
    private GridBagConstraints gbc;
    private ImageIcon titleIcon = new ImageIcon("src\\main\\resources\\Icons\\login.png");

    LoginFrame() {
        super("Logowanie");
        setSize(320, 210);
        setLayout(new GridBagLayout());
        setIconImage(titleIcon.getImage());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);

        loginInfo = new JLabel("Błędny login lub hasło!");
        setGbc(0, 0, 2);
        loginInfo.setVisible(false);
        add(loginInfo, gbc);

        JLabel loginField = new JLabel("Login:");
        setGbc(0, 1, 1);
        add(loginField, gbc);

        forLogin = new JTextField(10);
        setGbc(1, 1, 1);
        add(forLogin, gbc);

        JLabel passwordField = new JLabel("Hasło: ");
        setGbc(0, 2, 1);
        add(passwordField, gbc);

        forPassword = new JPasswordField(10);
        setGbc(1, 2, 1);
        add(forPassword, gbc);

        loginButton = new JButton("Zaloguj");
        setGbc(0, 3, 1);
        loginButton.addActionListener(this);
        add(loginButton, gbc);

        resetPasswordButton = new JButton("Zapomniałem hasła");
        setGbc(1, 3, 1);
        resetPasswordButton.addActionListener(this);
        add(resetPasswordButton, gbc);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setGbc(int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(loginButton)) {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<DB_Users> query = entityManager.createQuery("from users u where u.login = :login and u.password = :password", DB_Users.class);
            query.setParameter("login", forLogin.getText());
            query.setParameter("password", forPassword.getText());
            try {
                DB_Users user = query.getSingleResult();
                this.dispose();
                Library library = new Library();
            } catch (Exception exc) {
                loginInfo.setVisible(true);
                forLogin.setText(null);
                forPassword.setText(null);
            } finally {
                entityManager.close();
                entityManagerFactory.close();
            }
        }
    }
}