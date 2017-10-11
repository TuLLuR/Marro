package com.weffle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private String address;
    private String username;
    private String password;
    private Connection connection;
    private Statement statement;

    public Database(String address, String username, String password) {
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(address, username, password);
            statement = connection.createStatement();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAddress(String host, int port, String scheme) {
        return String.format("jdbc:mysql://%s:%d/%s", host, port, scheme);
    }

    public static String getAddress(String host, int port, String scheme, String... args) {
        StringBuilder builder = new StringBuilder(getAddress(host, port, scheme));
        if (args.length > 0)
            builder.append('?');
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]);
            if (i < args.length - 1)
                builder.append('&');
        }
        return builder.toString();
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
