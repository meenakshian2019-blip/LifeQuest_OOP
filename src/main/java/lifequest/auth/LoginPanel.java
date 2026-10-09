package lifequest.auth;
import lifequest.Main;import lifequest.database.UserDAO;import lifequest.model.User;import lifequest.ui.Style;import javax.swing.*;import java.awt.*;
public class LoginPanel extends JPanel {
 private final Main main;private final JTextField username=new JTextField();private final JPasswordField password=new JPasswordField();
 public LoginPanel(Main m){main=m;setBackground(Style.BACKGROUND);setLayout(new GridBagLayout());JPanel c=Style.card();c.setLayout(new GridBagLayout());c.setPreferredSize(new Dimension(430,390));GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(8,12,8,12);g.fill=GridBagConstraints.HORIZONTAL;g.gridx=0;
  g.gridy=0;c.add(Style.title("LifeQuest"),g);g.gridy=1;c.add(Style.label("Turn everyday tasks into quests."),g);g.gridy=2;c.add(Style.label("Username"),g);g.gridy=3;Style.text(username);c.add(username,g);g.gridy=4;c.add(Style.label("Password"),g);g.gridy=5;c.add(password,g);JButton l=Style.button("Login"),r=new JButton("Create account");g.gridy=6;c.add(l,g);g.gridy=7;c.add(r,g);l.addActionListener(e->login());r.addActionListener(e->main.showRegister());add(c);}
 private void login(){String u=username.getText().trim(),p=new String(password.getPassword());if(u.isEmpty()||p.isEmpty()){JOptionPane.showMessageDialog(this,"Enter username and password.");return;}User x=new UserDAO().login(u,p);if(x!=null)main.showApp(x);else JOptionPane.showMessageDialog(this,"Login failed. Check details and database connection.");}
}
