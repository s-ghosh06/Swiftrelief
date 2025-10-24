🧭 SwiftRelief - Setup Guide

🛠 REQUIREMENTS

Java 17 or newer

JavaFX SDK installed and configured in your IDE

MySQL Server installed and running

MySQL Workbench or Command Line access

🗄 DATABASE SETUP

1️⃣ Open MySQL Command Line or MySQL Workbench.
2️⃣ Create a new database:

CREATE DATABASE swiftrelief_db;


3️⃣ Import the provided database file:

mysql -u root -p swiftrelief_db < swiftrelief_db.sql


(Enter your MySQL password when prompted)

4️⃣ Verify import:

USE swiftrelief_db;
SHOW TABLES;

⚙️ UPDATE DATABASE CONNECTION

Open this file:
src/main/java/com/swiftrelief/DBUtil.java

Check or modify these lines:

private static final String URL = "jdbc:mysql://localhost:3306/swiftrelief_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
private static final String USER = "root"; // your MySQL username
private static final String PASSWORD = "YOUR_PASSWORD"; // your MySQL password

▶️ RUNNING THE APPLICATION

1️⃣ Open the project in IntelliJ / Eclipse.
2️⃣ Build and run Main.java.
3️⃣ Login or create a new admin user if needed.
4️⃣ Enjoy using SwiftRelief! 🎉

📦 FILES INCLUDED

src/ → Java source files

target/ → Build output

pom.xml → Maven dependencies

swiftrelief_db.sql → Database backup

README_SETUP.txt → Setup instructions

💡 NOTE

If the table is empty, ensure you imported the correct swiftrelief_db.sql file.
Do not rename tables or columns.

✅ End of Guide