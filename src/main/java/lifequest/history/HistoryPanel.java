package lifequest.history;
import lifequest.database.QuestDAO;import lifequest.database.QuestDAO.Quest;import lifequest.model.User;import lifequest.ui.Style;import javax.swing.*;import javax.swing.table.DefaultTableModel;import java.awt.*;import java.text.*;import java.util.*;
public class HistoryPanel extends JPanel {
 private final User user;private final JComboBox<String> filter=new JComboBox<>(new String[]{"All","Today","This Week"});private final DefaultTableModel model;
 public HistoryPanel(User u){user=u;setBackground(Style.BACKGROUND);setLayout(new BorderLayout(12,12));setBorder(BorderFactory.createEmptyBorder(25,25,25,25));JPanel top=new JPanel(new BorderLayout());top.setOpaque(false);top.add(Style.title("Quest History"),BorderLayout.WEST);top.add(filter,BorderLayout.EAST);filter.addActionListener(e->refresh());model=new DefaultTableModel(new Object[]{"Quest","Category","Difficulty","XP","Completed At"},0);JTable t=new JTable(model);t.setRowHeight(32);add(top,BorderLayout.NORTH);add(Style.scroll(t),BorderLayout.CENTER);refresh();}
 private void refresh(){model.setRowCount(0);String f=(String)filter.getSelectedItem();for(Quest q:new QuestDAO().getHistory(user.getId(),f)){model.addRow(new Object[]{q.name,q.category,q.difficulty,q.xp,q.completedAt==null?"-":new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(q.completedAt)});}}
}
