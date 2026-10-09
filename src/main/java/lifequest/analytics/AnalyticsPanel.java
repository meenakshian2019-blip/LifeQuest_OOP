package lifequest.analytics;
import lifequest.database.QuestDAO;import lifequest.model.User;import lifequest.ui.Style;import javax.swing.*;import java.awt.*;
public class AnalyticsPanel extends JPanel {
 public AnalyticsPanel(User u){setBackground(Style.BACKGROUND);setLayout(new BorderLayout(15,15));setBorder(BorderFactory.createEmptyBorder(25,25,25,25));QuestDAO d=new QuestDAO();int c=d.completedCount(u.getId()),x=d.totalXp(u.getId());add(Style.title("Analytics"),BorderLayout.NORTH);JPanel cards=new JPanel(new GridLayout(2,2,12,12));cards.setOpaque(false);cards.add(card("Completed Quests",""+c));cards.add(card("XP Earned",""+x));cards.add(card("Current Level",""+u.getLevel()));cards.add(card("Coins",""+u.getCoins()));add(cards,BorderLayout.CENTER);JProgressBar b=new JProgressBar(0,100);b.setValue(Math.min(100,c*10));b.setStringPainted(true);b.setString("Completion activity");add(b,BorderLayout.SOUTH);}
 private JPanel card(String n,String v){JPanel p=Style.card();p.setLayout(new BorderLayout());p.add(Style.label(n),BorderLayout.NORTH);p.add(Style.title(v),BorderLayout.CENTER);return p;}
}
