import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBookFrame extends JFrame implements ActionListener {
    private JButton yes, no;
    private GridBagConstraints gbc;
    private ImageIcon yesIcon = new ImageIcon("src\\main\\resources\\Icons\\yes.png");
    private ImageIcon cancelIcon = new ImageIcon("src\\main\\resources\\Icons\\cancel.png");
    private ImageIcon titleIcon = new ImageIcon("src\\main\\resources\\Icons\\delete.png");

    DeleteBookFrame() {
        super("Na pewno chcesz usunać?");
        setSize(320, 110);
        setResizable(false);
        setIconImage(titleIcon.getImage());
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        yes = new JButton("Tak",yesIcon);
        yes.addActionListener(this);
        no = new JButton("Nie",cancelIcon);
        no.addActionListener(this);
        add(yes, gbc);
        add(no, gbc);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == yes) {
            Library.entityDeleteBook(Long.valueOf(BooksPanel.selectedRow));
            dispose();

        }
        if (source == no) dispose();
    }
}
