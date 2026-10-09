package lifequest.ui;
import java.awt.*;import javax.swing.*;import javax.swing.border.*;
public class Style {
 public static final Color BACKGROUND=new Color(18,20,28),CARD=new Color(29,32,43),SIDEBAR=new Color(13,15,22),TEXT=new Color(240,242,248),MUTED=new Color(165,170,185),ACCENT=new Color(95,125,255),SUCCESS=new Color(80,200,130);
 public static JLabel title(String s){JLabel l=new JLabel(s);l.setForeground(TEXT);l.setFont(new Font("Segoe UI",Font.BOLD,26));return l;}
 public static JLabel label(String s){JLabel l=new JLabel(s);l.setForeground(TEXT);l.setFont(new Font("Segoe UI",Font.PLAIN,14));return l;}
 public static JButton button(String s){JButton b=new JButton(s);b.setFocusPainted(false);b.setForeground(TEXT);b.setBackground(ACCENT);b.setBorder(new EmptyBorder(10,16,10,16));return b;}
 public static JPanel card(){JPanel p=new JPanel();p.setBackground(CARD);p.setBorder(new EmptyBorder(16,16,16,16));return p;}
 public static void text(JTextField f){f.setBackground(CARD);f.setForeground(TEXT);f.setCaretColor(TEXT);f.setBorder(new LineBorder(new Color(70,74,90)));}
 public static void combo(JComboBox<?> c){c.setBackground(CARD);c.setForeground(TEXT);}
 public static JScrollPane scroll(Component c){JScrollPane s=new JScrollPane(c);s.setBorder(null);s.getViewport().setBackground(BACKGROUND);return s;}
}
