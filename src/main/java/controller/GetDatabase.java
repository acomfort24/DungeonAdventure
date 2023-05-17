package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import org.sqlite.SQLiteDataSource;

/**
 * This is a temporary class to be cut up and used in the dungeon app.
 * @author Chad Oehlschlaeger-Browne
 *
 */
public final class GetDatabase {
    /** Private Constructor. */
    private GetDatabase() { }

    /**
     * Query's the database given as a parameter and based on the entity/name
     * returns a hashmap of that row.
     * @author Chad Oehlschlaeger-Browne, Tom Capaul
     * @param theEntity The row in the table(Monster, Hero, Item)
     * @param theName The name of the the entity
     * @return map of all properties and their value of a row in the table
     */
    private static Map<String, String> getData(final SQLiteDataSource theDB,
                                               final String theEntity,
                                               final String theName) {
        //now query the database table for all its contents and display the results
        System.out.println("Selecting all rows from questions table: " + theEntity);
        //%% escapes a % and %s inserts a string
        final String query = String.format("SELECT * FROM %s WHERE name LIKE '%%%s'",
                theEntity, theName);
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
        } catch (final SQLException e) {
            e.printStackTrace();

            System.exit(0);
        }
        return resultMap;
    }
    /**
     * Connects to Dungeon_Adventure.db in order to return
     * a SQliteDataSource from it.
     * @return the SQLiteDataSource is opened
     */
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
        System.out.println(data);
        System.out.println("press enter to close program/window");
        final Scanner input = new Scanner(System.in);
        input.nextLine();
    }
}
