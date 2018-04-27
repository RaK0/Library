import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OptionPanel extends JPanel implements ActionListener {
    private ImageIcon bookIcon = new ImageIcon("src\\main\\resources\\Icons\\book.png");
    private ImageIcon userIcon = new ImageIcon("src\\main\\resources\\Icons\\man.png");
    private ImageIcon analysisIcon = new ImageIcon("src\\main\\resources\\Icons\\analysis.png");
    private ImageIcon settingsIcon = new ImageIcon("src\\main\\resources\\Icons\\settings.png");
    private ImageIcon statisticIcon = new ImageIcon("src\\main\\resources\\Icons\\statistic.png");
    private ImageIcon ordersIcon = new ImageIcon("src\\main\\resources\\Icons\\orders.png");
    private JButton books, analysis, orders, statistics, users, settings;
    JButton[] menuButtons = {
            books = new JButton("Książki", bookIcon),
            analysis = new JButton("Analityk", analysisIcon),
            orders = new JButton("Zamówienia", ordersIcon),
            statistics = new JButton("Statystyka", statisticIcon),
            users = new JButton("Użytkownicy", userIcon),
            settings = new JButton("Ustawienia", settingsIcon),
    };

    OptionPanel() {
        super(new GridLayout());
        for (JButton button : menuButtons) {
            button.addActionListener(this);
            add(button);
        }
        //jButton.setBorderPainted(false);
        //jButton.setContentAreaFilled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(books)) {
            Library.cardLayout.show(Library.mainComponent, "books");
        }
        if (source.equals(users)) {
            Library.cardLayout.show(Library.mainComponent, "users");
        }
    }

}
