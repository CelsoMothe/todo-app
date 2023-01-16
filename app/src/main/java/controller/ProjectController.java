package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
    public void save(Project project) {
        
        String sql = "INSERT INTO projects ("
                + "name, "
                + "description, "
                + "createdAt, "
                + "updatedAt) "
                + "VALUES(?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar um projeto " + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update(Project project) {
        
        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ?"
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            statement.execute();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o projeto " + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void delete(int id) {
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o projeto " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Project> getProject() {
        
        String sql = "SELECT * FROM projects";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Project> projects = new ArrayList<>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                Project project = new Project(
                        resultSet.getInt("id"), 
                        resultSet.getString("name"), 
                        resultSet.getString("description"), 
                        resultSet.getDate("createdAt"), 
                        resultSet.getDate("createdAt"));
                projects.add(project);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar o projeto " + e.getMessage(),e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        return projects;
    }
}