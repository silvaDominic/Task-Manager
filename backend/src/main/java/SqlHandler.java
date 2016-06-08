import java.sql.*;

/**
 * Created by reclaimer on 6/6/16.
 */
public class SqlHandler implements TaskManager {
    private String dbURL;
    private String username;
    private String password;
    private static final String changeTask = "UPDATE tasks SET task_name = (?) WHERE task_id = (?)";
    private static final String createTask = "INSERT INTO tasks (task_name) VALUES (?)";
    private static final String deleteTask = "DELETE FROM tasks WHERE task_id = (?)";

    public SqlHandler(String dbURL, String username, String password){
        this.dbURL = dbURL;
        this.username = username;
        this.password = password;
    }

    @Override
    public void changeTaskName(String newName, int id) {
        try (Connection conn = DriverManager.getConnection(this.dbURL, this.username, this.password)) {
            PreparedStatement statement = conn.prepareStatement(changeTask, id);
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Successfully changed name of task in DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(int id) {
        try (Connection conn = DriverManager.getConnection(this.dbURL, this.username, this.password)) {
            PreparedStatement statement = conn.prepareStatement(deleteTask, id);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Successfully deleted task from DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createTask(String newTask, int id) {
        try (Connection conn = DriverManager.getConnection(this.dbURL, this.username, this.password)) {
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(createTask, id);
                statement.setString(1, newTask);
                statement.executeUpdate();
                System.out.println("Successfully added task to DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}