package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Frame extends JFrame implements ActionListener {
    JMenu menu;
    JMenu menuLanguage;
    JMenuItem itemHome;
    JMenuItem itemExit;
    JMenuItem itemVi;
    JMenuItem itemEn;
    JButton startButton;
    CardLayout cardLayout;
    JPanel cardPanel;
    JPanel jpnHome;
    JTabbedPane jTabbedPane;

    public GUI_Frame() {
        JMenuBar jMenuBar = new JMenuBar();
        this.menu = new JMenu("Trang Chủ");
        this.itemHome = new JMenuItem("Trang chủ");
        this.itemExit = new JMenuItem("Exit");
        this.menu.add(this.itemHome);
        this.menu.add(this.itemExit);
        this.menuLanguage = new JMenu("Ngôn ngữ");
        this.itemVi = new JMenuItem("Việt Nam");
        this.itemEn = new JMenuItem("Tiếng Anh");
        this.menuLanguage.add(this.itemVi);
        this.menuLanguage.add(this.itemEn);
        jMenuBar.add(this.menu);
        jMenuBar.add(this.menuLanguage);
        this.setJMenuBar(jMenuBar);
        this.cardLayout = new CardLayout();
        this.cardPanel = new JPanel(this.cardLayout);
        this.jpnHome = new JPanel();
        this.startButton = new JButton("Bắt đầu");
        this.jpnHome.add(this.startButton);
        this.jTabbedPane = new JTabbedPane(3);
        this.jTabbedPane.addTab("Giải Thuật Đối Xứng", this.createT());
        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("Nội dung của Tab 2"));
        this.jTabbedPane.addTab("Giải Thuật Bất Đối Xứng", tab2);
        this.cardPanel.add(this.jpnHome, "HOME");
        this.cardPanel.add(this.jTabbedPane, "TABS");
        this.add(this.cardPanel);
        this.startButton.addActionListener(this::actionPerformed);
        this.setDefaultCloseOperation(3);
        this.setSize(400, 300);
        this.setVisible(true);
    }

    private JPanel createT() {
        JPanel jpn = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane1 = new JTabbedPane();
        tabbedPane1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        JPanel tab1 = new JPanel();
        tab1.add(new JLabel("Nội dung của Tab 1.1"));
        tabbedPane1.addTab("Tab1_1", tab1);
        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("Nội dung của Tab 1.2"));
        tabbedPane1.addTab("tab1_2", tab2);
        jpn.add(tabbedPane1, "Center");
        return jpn;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.startButton) {
            this.cardLayout.show(this.cardPanel, "TABS");
        }

    }

    public static void main(String[] args) {
        new GUI_Frame();
    }
}
