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

        // menu bar
        JMenuBar jMenuBar = new JMenuBar();
        menu = new JMenu("Trang Chủ");
        itemHome = new JMenuItem("Trang chủ");
        itemExit = new JMenuItem("Exit");
        menu.add(itemHome);
        menu.add(itemExit);
        menuLanguage = new JMenu("Ngôn ngữ");
        itemVi = new JMenuItem("Việt Nam");
        itemEn = new JMenuItem("Tiếng Anh");
        menuLanguage.add(itemVi);
        menuLanguage.add(itemEn);
        jMenuBar.add(menu);
        jMenuBar.add(menuLanguage);
        setJMenuBar(jMenuBar);
        // thêm cardlayout quản lí panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        //panel home
        jpnHome = new JPanel(new GridLayout(2,1));
        JPanel jpnHomeItem1=new JPanel(new BorderLayout());
        JPanel jpnHomeItem2=new JPanel();
        startButton = new JButton("Bắt đầu");
        ImageIcon imageIcon= new ImageIcon("images/hack.png");
        Image originalImage = imageIcon.getImage();

        // Điều chỉnh kích thước của hình ảnh
        int newWidth = 200; // Điều chỉnh kích thước rộng theo nhu cầu của bạn
        int newHeight = 200; // Điều chỉnh kích thước cao theo nhu cầu của bạn
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

// Tạo một ImageIcon từ hình ảnh đã điều chỉnh kích thước
        ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
        JLabel jl=new JLabel(resizedImageIcon,SwingConstants.CENTER);
        jpnHomeItem1.add(jl, BorderLayout.CENTER);
        jpnHomeItem2.add(startButton);
        jpnHome.add(jpnHomeItem1);
        jpnHome.add(jpnHomeItem2, BOTTOM_ALIGNMENT);

        jTabbedPane = new JTabbedPane(3);
        // tab1
        jTabbedPane.addTab("Giải Thuật Đối Xứng", createT());
        //tab2
        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("Nội dung của Tab 2"));
        jTabbedPane.addTab("Giải Thuật Bất Đối Xứng", tab2);

        // thêm panel vào cardLayout
        cardPanel.add(jpnHome, "HOME");
        cardPanel.add(jTabbedPane, "TABS");

        // thêm card vào frame
        add(cardPanel);

        //event
        startButton.addActionListener(this);
        itemHome.addActionListener(this);
        //setDefault

        ImageIcon iconTitle = new ImageIcon("images/Logo_HCMUAF.png");
        setIconImage(iconTitle.getImage());
        setTitle("ĐỒ ÁN GIỮA KỲ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
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
        if (e.getSource() == startButton) {
            cardLayout.show(cardPanel, "TABS");
        }
        if(e.getSource()== itemHome){
            cardLayout.show(cardPanel, "HOME");
        }

    }

    public static void main(String[] args) {
        new GUI_Frame();
    }
}
