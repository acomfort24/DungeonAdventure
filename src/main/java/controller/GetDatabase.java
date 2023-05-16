package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.sqlite.SQLiteDataSource;

public final class GetDatabase {
    /** Private Constructor. */
    private GetDatabase() { }
    private static Map<String, String> getData(final SQLiteDataSource theDB,
                                               final String theEntity,
                                               final String theType) {
        //now query the database table for all its contents and display the results
        System.out.println("Selecting all rows from questions table: " + theEntity);
        final String query = String.format("SELECT * FROM %s WHERE name LIKE '%%%s'",
                theEntity, theType);
        final ConcurrentHashMap<String, String> resultMap = new ConcurrentHashMap<>();
        try (Connection conn = theDB.getConnection();
              Statement stmt = conn.createStatement()) {
            final ResultSet rs = stmt.executeQuery(query);

            final ResultSetMetaData rsmd = rs.getMetaData();
            final int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    resultMap.put(rsmd.getColumnName(i), rs.getString(i));
                }
            }
            System.out.println(resultMap);
        } catch (final SQLException e) {
            e.printStackTrace();

            System.exit(0);
        }
        return resultMap;
    }

    private static SQLiteDataSource startConnection() {
        SQLiteDataSource ds = null;
        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:Dungeon_Adventure.db");
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return ds;
    }
    
    public static void main(final String[] theArgs) {
        final SQLiteDataSource ds = startConnection();
        final Map<String, String> data = getData(ds, "Hero", "Warrior");
        
        System.out.println("press enter to close program/window");
        final Scanner input = new Scanner(System.in);
        input.nextLine();
    }
}
