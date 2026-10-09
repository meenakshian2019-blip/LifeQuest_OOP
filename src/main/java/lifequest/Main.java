package lifequest;
import lifequest.auth.*;import lifequest.dashboard.DashboardPanel;import lifequest.model.User;import javax.swing.*;import java.awt.*;
public class Main extends JFrame {
 private User user;
 public Main(){setTitle("LifeQuest");setSize(1100,700);setMinimumSize(new Dimension(900,600));setLocationRelativeTo(null);setDefaultCloseOperation(EXIT_ON_CLOSE);showLogin();}
 public void showLogin(){setContentPane(new LoginPanel(this));revalidate();repaint();}
 public void showRegister(){setContentPane(new RegisterPanel(this));revalidate();repaint();}
 public void showApp(User u){user=u;setContentPane(new DashboardPanel(this,u));revalidate();repaint();}
 public void logout(){user=null;showLogin();}
 public static void main(String[] a){SwingUtilities.invokeLater(()->{try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}catch(Exception e){}new Main().setVisible(true);});}
}
