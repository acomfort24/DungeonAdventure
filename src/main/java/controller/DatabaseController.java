package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.sqlite.SQLiteDataSource;

/**
 * Controller class to manipulate data from an SQLite database to be used
 * in enemy and player models.
 *
 * @author Chad Oehlschlaeger-Browne
 *
 * @version 1.0
 */
public final class DatabaseController {
    /** Private Constructor. */
    private DatabaseController() { }

    /**
     * Query's the database given as a parameter and based on the entity/name
     * returns a hashmap of that row.
     * @author Chad Oehlschlaeger-Browne, Tom Capaul
     * @param theEntity The row in the table(Monster, Hero, Item)
     * @param theName The name of the entity
     * @return map of all properties and their value of a row in the table
     */
    public static Map<String, String> getData(final SQLiteDataSource theDS,
                                              final String theEntity, final String theName) {

        //%% escapes a % and %s inserts a string
        final String query = String.format("SELECT * FROM %s WHERE name LIKE '%%%s'",
                theEntity, theName);
        final ConcurrentHashMap<String, String> resultMap = new ConcurrentHashMap<>();
        try (Connection conn = theDS.getConnection();
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
     * Retrieves all SQL data from the database.
     *
     * @return a map containing all the SQL data, organized by entity type and entity name
     * @throws Exception if there is an error retrieving the SQL data
     */
    public static Map<String, Map<String, String>> getAllSqlData() throws Exception {
        final Map<String, Map<String, String>> sqlData = new HashMap<>();
        final SQLiteDataSource ds;
        //establish connection (creates db file if it does not exist :-)
        ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:Dungeon_Adventure.db");
        sqlData.put("Warrior", getData(ds, "Hero", "Warrior"));
        sqlData.put("Wizard", getData(ds, "Hero", "Wizard"));
        sqlData.put("Thief", getData(ds, "Hero", "Thief"));
        sqlData.put("Gremlin", getData(ds, "Monster", "Gremlin"));
        sqlData.put("Orc", getData(ds, "Monster", "Orc"));
        sqlData.put("Skeleton", getData(ds, "Monster", "Skeleton"));
        ds.getConnection().close();
        return sqlData;
    }
}
