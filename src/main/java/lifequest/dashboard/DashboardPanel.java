package lifequest.dashboard;

import lifequest.Main;
import lifequest.analytics.AnalyticsPanel;
import lifequest.history.HistoryPanel;
import lifequest.model.User;
import lifequest.profile.ProfilePanel;
import lifequest.quests.QuestPanel;
import lifequest.settings.SettingsPanel;
import lifequest.ui.MascotPanel;
import lifequest.ui.Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private final Main main;
    private final User user;
    private JButton selectedButton;
    private final JPanel content = new JPanel(new BorderLayout());

    public DashboardPanel(Main m, User u) {
        main = m; user = u;
        setLayout(new BorderLayout());
        setBackground(Style.BACKGROUND);
        add(sidebar(), BorderLayout.WEST);
        content.setBackground(Style.BACKGROUND);
        add(content, BorderLayout.CENTER);
        dashboard();
    }

    private JPanel sidebar() {
        JPanel s = new JPanel();
        s.setBackground(Style.SIDEBAR);
        s.setPreferredSize(new Dimension(205, 0));
        s.setLayout(new BoxLayout(s, BoxLayout.Y_AXIS));
        s.setBorder(new EmptyBorder(24, 13, 20, 13));
        JLabel brand = Style.title("✦ LifeQuest");
        brand.setForeground(new Color(190, 165, 255));
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);
        s.add(brand);
        JLabel tagline = Style.label("  Small steps. Big dreams.");
        tagline.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        tagline.setForeground(Style.MUTED);
        tagline.setAlignmentX(Component.LEFT_ALIGNMENT);
        s.add(tagline);
        s.add(Box.createVerticalStrut(32));
        nav(s, "⌂   Dashboard", e -> dashboard(), true);
        nav(s, "✧   Quests", e -> show(new QuestPanel(main, user)), false);
        nav(s, "◷   History", e -> show(new HistoryPanel(user)), false);
        nav(s, "▥   Analytics", e -> show(new AnalyticsPanel(user)), false);
        nav(s, "♙   Profile", e -> show(new ProfilePanel(user)), false);
        nav(s, "⚙   Settings", e -> show(new SettingsPanel(main, user)), false);
        s.add(Box.createVerticalGlue());
        JLabel quote = Style.label("“Progress, not\n perfection.”");
        quote.setForeground(Style.MUTED);
        quote.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        quote.setAlignmentX(Component.LEFT_ALIGNMENT);
        s.add(quote);
        s.add(Box.createVerticalStrut(15));
        nav(s, "↪   Logout", e -> main.logout(), false);
        return s;
    }

    /*private void nav(JPanel s, String text, java.awt.event.ActionListener action, boolean selected) {
        JButton b = new JButton(text);
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        b.setMaximumSize(new Dimension(185, 42));
        b.setPreferredSize(new Dimension(180, 42));
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setForeground(selected ? Color.WHITE : Style.MUTED);
        b.setBackground(selected ? new Color(76, 48, 160) : Style.SIDEBAR);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(10, 12, 10, 8));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(action);
        s.add(b);
        s.add(Box.createVerticalStrut(7));
    } */


    private void nav(JPanel s, String text,
                 java.awt.event.ActionListener action,
                 boolean selected) {

    JButton b = new JButton(text);

    b.setAlignmentX(Component.LEFT_ALIGNMENT);
    b.setMaximumSize(new Dimension(185, 42));
    b.setPreferredSize(new Dimension(180, 42));
    b.setHorizontalAlignment(SwingConstants.LEFT);
    b.setFont(new Font("Segoe UI", Font.BOLD, 13));
    b.setFocusPainted(false);
    b.setBorder(new EmptyBorder(10, 12, 10, 8));

    if (selected) {
        selectedButton = b;
    }

    updateNavColor(b, selected);

    b.addActionListener(e -> {
        if (selectedButton != null) {
            updateNavColor(selectedButton, false);
        }

        selectedButton = b;
        updateNavColor(b, true);

        action.actionPerformed(e);
    });

    s.add(b);
    s.add(Box.createVerticalStrut(7));
}

    private void updateNavColor(JButton b, boolean selected) {
    b.setOpaque(true);
    b.setContentAreaFilled(true);
    b.setBorderPainted(false);

    if (selected) {
        b.setForeground(Color.WHITE);
        b.setBackground(Style.ACCENT);
    } else {
        b.setForeground(Style.MUTED);
        b.setBackground(Style.SIDEBAR);
    }
}

    private void show(JPanel panel) {
        content.removeAll(); content.add(panel, BorderLayout.CENTER);
        content.revalidate(); content.repaint();
    }

    private void dashboard() {
        content.removeAll();
        JPanel page = new JPanel(new BorderLayout(18, 18));
        page.setBackground(Style.BACKGROUND);
        page.setBorder(new EmptyBorder(28, 30, 28, 30));

        JPanel heading = new JPanel(new BorderLayout(10, 4));
        heading.setOpaque(false);
        JPanel greeting = new JPanel(); greeting.setOpaque(false); greeting.setLayout(new BoxLayout(greeting, BoxLayout.Y_AXIS));
        JLabel welcome = Style.title("Welcome back, " + user.getUsername() + "!");
        JLabel subtitle = Style.label("Another day, another quest. Keep building your streak.");
        subtitle.setForeground(Style.MUTED);
        greeting.add(welcome); greeting.add(Box.createVerticalStrut(5)); greeting.add(subtitle);
        heading.add(greeting, BorderLayout.CENTER);
        page.add(heading, BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(16, 16)); body.setOpaque(false);
        JPanel stats = new JPanel(new GridLayout(1, 4, 12, 12)); stats.setOpaque(false);
        stats.add(statCard("✦  LEVEL", String.valueOf(user.getLevel()), "Your current rank", Style.ACCENT));
        stats.add(statCard("⚡  TOTAL XP", String.valueOf(user.getXp()), "Keep earning XP", Style.ACCENT_BLUE));
        stats.add(statCard("◈  COINS", String.valueOf(user.getCoins()), "Rewards collected", new Color(255, 190, 92)));
        stats.add(statCard("◎  FOCUS", String.valueOf(user.getFocus()), "Your focus stat", Style.SUCCESS));
        body.add(stats, BorderLayout.NORTH);

        JPanel lower = new JPanel(new GridLayout(1, 2, 16, 16)); lower.setOpaque(false);
        JPanel progress = Style.card(); progress.setLayout(new BoxLayout(progress, BoxLayout.Y_AXIS));
        JLabel progressTitle = Style.title("Your journey"); progressTitle.setFont(new Font("Segoe UI", Font.BOLD, 20)); progressTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel level = Style.label("Level " + user.getLevel() + "  •  Adventurer"); level.setForeground(Style.MUTED); level.setAlignmentX(Component.LEFT_ALIGNMENT);
        JProgressBar xp = new JProgressBar(0, 100); xp.setValue(Math.floorMod(user.getXp(), 100)); xp.setStringPainted(true); xp.setString(Math.floorMod(user.getXp(), 100) + " / 100 XP to next level");
        xp.setForeground(Style.ACCENT); xp.setBackground(new Color(29, 37, 69)); xp.setBorderPainted(false); xp.setPreferredSize(new Dimension(300, 22)); xp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));
        progress.add(progressTitle); progress.add(Box.createVerticalStrut(8)); progress.add(level); progress.add(Box.createVerticalStrut(18)); progress.add(xp); progress.add(Box.createVerticalStrut(22));
        JLabel statsTitle = Style.title("Character stats"); statsTitle.setFont(new Font("Segoe UI", Font.BOLD, 18)); statsTitle.setAlignmentX(Component.LEFT_ALIGNMENT); progress.add(statsTitle); progress.add(Box.createVerticalStrut(12));
        addStatLine(progress, "Health", user.getHealth(), new Color(255, 105, 145));
        addStatLine(progress, "Focus", user.getFocus(), Style.ACCENT_BLUE);
        addStatLine(progress, "Discipline", user.getDiscipline(), Style.SUCCESS);
        addStatLine(progress, "Strength", user.getStrength(), new Color(255, 190, 92));

        JPanel companion = Style.card(); companion.setLayout(new BorderLayout(8, 8));
        JPanel mascotText = new JPanel(); mascotText.setOpaque(false); mascotText.setLayout(new BoxLayout(mascotText, BoxLayout.Y_AXIS));
        JLabel meet = Style.title("Meet Lumi"); meet.setFont(new Font("Segoe UI", Font.BOLD, 22)); meet.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel buddy = Style.label("Your little quest companion"); buddy.setForeground(Style.MUTED); buddy.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextArea message = new JTextArea("Every small step counts.\nPick a quest, do your best,\nand let's level up together!");
        message.setEditable(false); message.setFocusable(false); message.setOpaque(false); message.setForeground(Style.TEXT); message.setFont(new Font("Segoe UI", Font.PLAIN, 13)); message.setLineWrap(true); message.setWrapStyleWord(true);
        //JButton quests = Style.button("✧  View your quests"); quests.setAlignmentX(Component.LEFT_ALIGNMENT); quests.addActionListener(e -> show(new QuestPanel(main, user)));
        JButton quests = Style.button("✧  View your quests");
quests.setAlignmentX(Component.LEFT_ALIGNMENT);
quests.setForeground(Color.BLACK);
quests.setBackground(Style.ACCENT);
quests.setOpaque(true);
quests.setContentAreaFilled(true);
quests.addActionListener(e -> show(new QuestPanel(main, user)));
        mascotText.add(meet); mascotText.add(Box.createVerticalStrut(5)); mascotText.add(buddy); mascotText.add(Box.createVerticalStrut(13)); mascotText.add(message); mascotText.add(Box.createVerticalStrut(15)); mascotText.add(quests);
        companion.add(new MascotPanel(), BorderLayout.CENTER); companion.add(mascotText, BorderLayout.SOUTH);
        lower.add(progress); lower.add(companion);
        body.add(lower, BorderLayout.CENTER);
        page.add(body, BorderLayout.CENTER);
        content.add(page, BorderLayout.CENTER); content.revalidate(); content.repaint();
    }

    private JPanel statCard(String name, String value, String note, Color accent) {
        JPanel p = Style.card(); p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel n = Style.label(name); n.setForeground(Style.MUTED); n.setFont(new Font("Segoe UI", Font.BOLD, 11));
        JLabel v = Style.title(value); v.setForeground(accent); v.setFont(new Font("Segoe UI", Font.BOLD, 28));
        JLabel d = Style.label(note); d.setForeground(Style.MUTED); d.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        p.add(n); p.add(Box.createVerticalStrut(9)); p.add(v); p.add(Box.createVerticalStrut(4)); p.add(d); return p;
    }

    private void addStatLine(JPanel parent, String name, int value, Color color) {
        JPanel row = new JPanel(new BorderLayout(10, 0)); row.setOpaque(false); row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        JLabel label = Style.label(name); label.setForeground(Style.MUTED); label.setPreferredSize(new Dimension(85, 20));
        JProgressBar bar = new JProgressBar(0, 100); bar.setValue(Math.max(0, Math.min(100, value))); bar.setBorderPainted(false); bar.setBackground(new Color(30, 38, 67)); bar.setForeground(color); bar.setStringPainted(false);
        JLabel number = Style.label(String.valueOf(value)); number.setForeground(Style.TEXT); number.setPreferredSize(new Dimension(30, 20));
        row.add(label, BorderLayout.WEST); row.add(bar, BorderLayout.CENTER); row.add(number, BorderLayout.EAST);
        parent.add(row); parent.add(Box.createVerticalStrut(8));
    }
}
