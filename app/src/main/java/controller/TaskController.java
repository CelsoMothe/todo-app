package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {
    
    public void save(Task task) {
        
        String sql = "INSERT INTO tasks ("
                + "idProject,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch(Exception e) {
            throw new RuntimeException("Erro ao salvar tarefa " + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update(Task task) {
        
        String sql = "UPDATE tasks SET "
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadline = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.getCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));            
            statement.setInt(9, task.getId());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar as tarefas" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void delete(int taskId){
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar a tarefa." + e.getMessage(),e);
        } finally {
           ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Task> tasks = new ArrayList<>();
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Task task = new Task(resultSet.getInt("id"),
                        resultSet.getInt("idProject"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("notes"),
                        resultSet.getBoolean("completed"),
                        resultSet.getDate("deadline"),
                        resultSet.getDate("createdAt"),
                        resultSet.getDate("updatedAt"));
                tasks.add(task);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar as tarefas " + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return tasks;
    }
}
