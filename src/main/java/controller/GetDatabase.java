package controller;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class GetDatabase {
    public static void main(String[] args) {
        SQLiteDataSource ds = startConnection();
        HashMap data = getData(ds, "Hero", "Warrior");

        System.out.println("press enter to close program/window");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }
    private static HashMap getData(SQLiteDataSource theDB, String theEntity, String theType) {
        //now query the database table for all its contents and display the results
        System.out.println( "Selecting all rows from questions table: " + theEntity);
        String query = String.format("SELECT * FROM %s WHERE name LIKE '%%%s'", theEntity, theType);
        HashMap resultMap = new HashMap();
        try ( Connection conn = theDB.getConnection();
              Statement stmt = conn.createStatement() ) {
            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i=1; i <= columnCount; i++) {
                    resultMap.put(rsmd.getColumnName(i), rs.getString(i));
                }
            }
            System.out.println(resultMap);
        } catch ( SQLException e ) {
            e.printStackTrace();

            System.exit( 0 );
        }
        return resultMap;
    }

    private static SQLiteDataSource startConnection() {
        SQLiteDataSource ds = null;
        //establish connection (creates db file if it does not exist :-)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:Dungeon_Adventure.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println( "Opened database successfully" );
        return ds;
    }
}
