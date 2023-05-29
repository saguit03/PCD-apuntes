package pcd.util;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;


public class Ventana extends JFrame {
    private static final long serialVersionUID = 1L;
    JScrollPane scrollPane;
    JTextArea textArea;
    int x, y;

    public Ventana(String title, int _x, int _y) {
        x = _x;
        y = _y;

        BorderLayout bl = new BorderLayout();
        this.getContentPane().setLayout(bl);
        scrollPane = new JScrollPane();
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        textArea = new JTextArea();
        textArea.setMargin(new Insets(7, 7, 7, 7));
        scrollPane.setViewportView(textArea);
        setLocation(x, y);
        setSize(300, 300);
        setTitle(title);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void addText(String str) {
        textArea.append("\n " + str);
    }

    public void addTextAndRemove(String str) {
        textArea.repaint();
        textArea.append("\n " + str);
    }

    public void addFloat(Float f) {
        textArea.append("\n " + f);
    }
}