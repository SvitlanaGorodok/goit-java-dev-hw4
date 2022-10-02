package service.crud;

import config.DatabaseManagerConnector;
import entities.dao.DeveloperDao;
import entities.dto.DeveloperDto;
import service.converter.DeveloperConverter;

import java.sql.*;

public class DeveloperService {
    DeveloperConverter developerConverter = new DeveloperConverter();
    private final DatabaseManagerConnector manager;
    private static final String INSERT = "INSERT INTO public.developers (first_name, last_name, age, salary) VALUES(?,?,?,?);";
    private static final String UPDATE = "UPDATE public.developers SET first_name = ?, last_name = ?, age = ?, " +
            "salary = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM public.developers WHERE id = ? AND first_name = ? AND last_name = ? " +
            "AND age = ? AND salary = ?;";
    private static final String FIND_BY_ID = "SELECT id, first_name, last_name, age, salary FROM public.developers WHERE id = ?;";
    private static final String SELECT_BY_PROJECT = "SELECT developers.id, developers.first_name, developers.last_name, " +
            "developers.age, developers.salary " +
            "FROM developers JOIN developers_projects ON developers_projects.developer_id = developers.id " +
            "WHERE developers_projects.project_id = ?;";
    private static final String SELECT_ALL = "SELECT id, first_name, last_name, age, salary FROM developers";
    private static final String SELECT_BY_MID_SKILL = "SELECT developers.id, developers.first_name, developers.last_name, " +
            "developers.age, developers.salary, skills.area " +
            "FROM developers JOIN developers_skills ON developers_skills.developer_id = developers.id " +
            "JOIN skills ON skills.id = developers_skills.skill_id WHERE skills.level = 'Middle';";
    public DeveloperService(DatabaseManagerConnector manager) {
        this.manager = manager;
    }

    public DeveloperDto create(DeveloperDto developerDto){
        DeveloperDao entity = developerConverter.convertToDao(developerDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setInt(3, entity.getAge());
            statement.setInt(4, entity.getSalary());
            statement.execute();
            entity.setId(getGeneratedKey(statement.getGeneratedKeys()));
            System.out.println("Developer successfully created!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Developer not created");
        }
        return developerDto;
    }

    public DeveloperDto update(DeveloperDto developerDto){
        DeveloperDao entity = developerConverter.convertToDao(developerDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setInt(3, entity.getAge());
            statement.setInt(4, entity.getSalary());
            statement.setInt(5, entity.getId());
            if (statement.executeUpdate() == 0){
                System.out.println("Developer not updated!");
            } else {
                System.out.println("Developer successfully updated!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Developer not updated!");
        }
        return developerDto;
    }

    public void delete(DeveloperDto developerDto){
        DeveloperDao entity = developerConverter.convertToDao(developerDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setInt(4, entity.getAge());
            statement.setInt(5, entity.getSalary());
            if (statement.executeUpdate() == 0){
                System.out.println("Developer not deleted!");
            } else {
                System.out.println("Developer successfully deleted!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Developer not deleted!");
        }
    }

    public DeveloperDto findById(Integer id){
        DeveloperDao entity = new DeveloperDao();
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("Developer not found!");
            } else {
                while (result.next()) {
                    entity.setId(result.getInt("id"));
                    entity.setFirstName(result.getString("first_name"));
                    entity.setLastName(result.getString("last_name"));
                    entity.setAge(result.getInt("age"));
                    entity.setSalary(result.getInt("salary"));
                    System.out.println(developerConverter.convertToDto(entity).toString());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Developer not found!");
        }
        return developerConverter.convertToDto(entity);
    }
    public void selectByProject(Integer projectId){
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_PROJECT)){
            statement.setInt(1, projectId);
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("Project not found!");
            } else {
                System.out.println("Developers: ");
                System.out.println("Id | Name | LastName | Age | Salary ");
                while (result.next()) {
                    System.out.print(result.getInt(1) + " | ");
                    System.out.print(result.getString(2) + "| ");
                    System.out.print(result.getString(3) + "| ");
                    System.out.print(result.getInt(4) + "| ");
                    System.out.print(result.getInt(5) + "\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Project not found!");
        }
    }

    public void selectAll(){
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)){
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("No developers found!");
            } else {
                System.out.println("Developers: ");
                System.out.println("Id | Name | LastName | Age | Salary ");
                while (result.next()) {
                    System.out.print(result.getInt(1) + " | ");
                    System.out.print(result.getString(2) + "| ");
                    System.out.print(result.getString(3) + "| ");
                    System.out.print(result.getInt(4) + "| ");
                    System.out.print(result.getInt(5) + "\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("No developers found!");
        }
    }
    public void selectByMidSkill(){
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_MID_SKILL)){
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("No developers found!");
            } else {
                System.out.println("Developers: ");
                System.out.println("Id | Name | LastName | Age | Salary | Area");
                while (result.next()) {
                    System.out.print(result.getInt(1) + " | ");
                    System.out.print(result.getString(2) + "| ");
                    System.out.print(result.getString(3) + "| ");
                    System.out.print(result.getInt(4) + "| ");
                    System.out.print(result.getInt(5) + "| ");
                    System.out.print(result.getString(6) + "\n");
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
