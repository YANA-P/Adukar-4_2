package by.adukar.telegrambot.sql;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Database {


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
