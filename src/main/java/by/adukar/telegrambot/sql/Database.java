package by.adukar.telegrambot.sql;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Database {

    public static final String INSERT_INTO_USERS = "INSERT INTO users (id, name, LastName, Role) values('%s','%s', '%s', '%s');";
    public static final String INSERT_INTO_GOODS = "INSERT INTO goods (id, name, description, url, costs) values('%s','%s', '%s', '%s', '%s');";
    public static final String DELETE_FROM_GOODS = "DELETE FROM goods WHERE name = '%s';";
    public static final String DELETE_FROM_USERS = "DELETE FROM users WHERE name = '%s';";
    @SneakyThrows
    public List<String> getListOfGoodsName() {
        List<String> listOfId = new ArrayList<>();
        Statement statement = createStatementFromConnection(connect());
        ResultSet results = statement.executeQuery("select * from goods");
        while (results.next()) {
            listOfId.add(results.getString("name"));
        }
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
        return listOfId;
    }

    @SneakyThrows
    public void insertUser (long id, String name, String LastName, String Role) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(INSERT_INTO_USERS, id, name, LastName, Role), statement);
    }

    public void deleteUser (String name) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(DELETE_FROM_USERS, name), statement);
    }

    @SneakyThrows
    public void insertGoods (long id, String name, String description, String url, Integer costs) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(INSERT_INTO_GOODS, id, name, description, url, costs), statement);
    }

    @SneakyThrows
    public void deleteGoods (String name) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(DELETE_FROM_GOODS, name), statement);
    }

    @SneakyThrows
    public String getCurrentValue(String item, int id) {
        Statement statement = createStatementFromConnection(connect());
        return sendGetRequestOfId("select * from goods", statement, item, id);
    }

    @SneakyThrows
    public String sendGetRequestOfId(String sql, Statement statement, String item, int id) {
        String res = "";
        ResultSet results = statement.executeQuery(sql);
        while (results.next()) {
            if (results.getInt("id") == id) {
                res = results.getString(item);
            }
        }
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
        return res;
    }


    @SneakyThrows
    public Connection connect() {
        Connection c = null;
        c = getConnection("jdbc:postgresql://localhost:5432/chatbot", "postgres",
                "zyf2005");
        c.setAutoCommit(false);
        return c;
    }

    @SneakyThrows
    public Statement createStatementFromConnection(Connection connection) {
        Statement stmt = null;
        stmt = connection.createStatement();
        return stmt;
    }

    @SneakyThrows
    public void sendRequest(String sql, Statement statement) {
        statement.executeUpdate(sql);
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
    }
}
