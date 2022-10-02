package service.crud;

import config.DatabaseManagerConnector;
import entities.dao.DeveloperDao;
import entities.dao.ProjectDao;
import entities.dto.ProjectDto;
import service.converter.ProjectConverter;

import java.sql.*;

public class ProjectService {
    ProjectConverter projectConverter = new ProjectConverter();
    private final DatabaseManagerConnector manager;
    private static final String INSERT = "INSERT INTO public.projects (name, description, start_date) VALUES(?,?,?);";
    private static final String UPDATE = "UPDATE public.projects SET name = ?, description = ?, start_date = ?  WHERE id = ?;";
    private static final String DELETE = "DELETE FROM public.projects WHERE id = ? AND name = ? AND description = ? AND start_date = ?;";
    private static final String FIND_BY_ID = "SELECT id, name, description, start_date FROM public.projects WHERE id = ?;";
    private static final String SELECT_BY_SALARY = "SELECT SUM(developers.salary) FROM developers_projects JOIN developers " +
            "ON developers.id = developers_projects.developer_id WHERE developers_projects.project_id = ?;";
    private static final String SELECT_PROJECTS = "SELECT projects.start_date, projects.name, COUNT(developers_projects.developer_id) " +
            "FROM projects JOIN developers_projects ON projects.id = developers_projects.project_id " +
            "GROUP BY projects.start_date, projects.name;";

    public ProjectService(DatabaseManagerConnector manager) {
        this.manager = manager;
    }

    public ProjectDto create(ProjectDto projectDto){
        ProjectDao entity = projectConverter.convertToDao(projectDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, entity.getStartDate());
            statement.execute();
            entity.setId(getGeneratedKey(statement.getGeneratedKeys()));
            System.out.println("Project successfully created!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Project not created");
        }
        return projectDto;
    }

    public ProjectDto update(ProjectDto projectDto){
        ProjectDao entity = projectConverter.convertToDao(projectDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, entity.getStartDate());
            statement.setInt(4, entity.getId());
            if (statement.executeUpdate() == 0){
                System.out.println("Project not updated!");
            } else {
                System.out.println("Project successfully updated!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Project not updated!");
        }
        return projectDto;
    }

    public void delete(ProjectDto projectDto){
        ProjectDao entity = projectConverter.convertToDao(projectDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getDescription());
            statement.setDate(4, entity.getStartDate());
            if (statement.executeUpdate() == 0){
                System.out.println("Project not deleted!");
            } else {
                System.out.println("Project successfully deleted!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Project not deleted!");
        }
    }
    public ProjectDto findById(Integer id){
        ProjectDao entity = new ProjectDao();
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("Project not found!");
            } else {
                while (result.next()) {
                    entity.setId(result.getInt("id"));
                    entity.setName(result.getString("name"));
                    entity.setDescription(result.getString("description"));
                    entity.setStartDate(result.getDate("start_date"));
                    System.out.println(projectConverter.convertToDto(entity).toString());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Project not found!");
        }
        return projectConverter.convertToDto(entity);
    }

    public void selectBySalary(Integer projectId){
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_SALARY)){
            statement.setInt(1, projectId);
            result = statement.executeQuery();
                while (result.next()) {
                    if (result.getInt(1) == 0) {
                        System.out.println("Project not found!");
                    } else {
                        System.out.println("Cost of project is " + result.getInt(1));
                    }
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Project not found!");
        }
    }
    public void selectAllProjects(){
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PROJECTS)){
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("No developers found!");
            } else {
                System.out.println("Projects: ");
                System.out.println("Start Date | Name | Amount of developers");
                while (result.next()) {
                    System.out.print(result.getDate(1) + " | ");
                    System.out.print(result.getString(2) + "| ");
                    System.out.print(result.getInt(3) + "\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("No developers found!");
        }
    }
    private Integer getGeneratedKey(ResultSet result){
        Integer key = null;
        try{
            while (result.next()){
                key = result.getInt(1);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Key not found!");
        }
        return key;
    }
}
