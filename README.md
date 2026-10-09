# LifeQuest

A simple Java Swing + JDBC + MySQL habit and skill tracking RPG for a college OOP project.

## Core flow
Login/Register -> Dashboard -> Create Quest -> Complete Quest -> Earn XP -> Level Up -> History/Analytics -> Profile/Settings

## XP
Easy = 20 | Medium = 50 | Hard = 80 | Epic = 120

## Stack
Java 17, Swing/AWT, JDBC, MySQL, Maven, Git/GitHub.

## Run
1. Install JDK 17, Maven and MySQL.
2. Open this folder in VS Code.
3. Run `database.sql` in MySQL Workbench.
4. Edit MySQL username/password in `src/main/java/lifequest/database/DB.java`.
5. Terminal:
   `mvn clean compile`
   `mvn exec:java`
6. Or run `lifequest.Main` from VS Code.

Authentication is intentionally basic for this college project and is not production security.

## Branches
member1-database, member2-auth, member3-dashboard, member4-quests, member5-ui-analytics
