package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static QueryRunner runner = new QueryRunner();

    private static Connection getConnection() throws SQLException {
        var datasource = System.getProperty("datasource.url");
        var user = System.getProperty("datasource.username");
        var pass = System.getProperty("datasource.password");
        return DriverManager.getConnection(datasource, user, pass);
    }

    public static int getPaidAmountCard() {
        var sql = "SELECT amount FROM payment_entity";
        try {
            var connection = getConnection();
            var paid = runner.query(connection, sql, new ScalarHandler<Integer>());
            return paid;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getTransactionIDCard() {
        var sql = "SELECT transaction_id FROM payment_entity";
        try {
            var connection = getConnection();
            var transactionID = runner.query(connection, sql, new ScalarHandler<String>());
            return transactionID;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getStatusCard() {
        var sql = "SELECT status FROM payment_entity";
        try {
            var connection = getConnection();
            var status = runner.query(connection, sql, new ScalarHandler<String>());
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTransactionIDOrderCard() {
        var sql = "SELECT payment_id FROM order_entity";
        try {
            var connection = getConnection();
            var transactionID = runner.query(connection, sql, new ScalarHandler<String>());
            return transactionID;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getStatusCredit() {
        var sql = "SELECT status FROM credit_request_entity";
        try {
            var connection = getConnection();
            var status = runner.query(connection, sql, new ScalarHandler<String>());
            return status;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTransactionIDCredit() {
        var sql = "SELECT id FROM credit_request_entity";
        try {
            var connection = getConnection();
            var transactionID = runner.query(connection, sql, new ScalarHandler<String>());
            return transactionID;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTransactionIDOrderCredit() {
        var sql = "SELECT credit_id FROM order_entity";
        try {
            var connection = getConnection();
            var transactionID = runner.query(connection, sql, new ScalarHandler<String>());
            return transactionID;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void cleanDB() throws SQLException {
        var connection = getConnection();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
    }
}
