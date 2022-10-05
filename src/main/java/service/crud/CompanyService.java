package service.crud;

import config.DatabaseManagerConnector;
import entities.dao.CompanyDao;
import entities.dto.CompanyDto;
import service.converter.CompanyConverter;

import java.sql.*;

public class CompanyService {
    CompanyConverter companyConverter = new CompanyConverter();
    private final DatabaseManagerConnector manager;
    private static final String INSERT = "INSERT INTO public.companies (name, description) VALUES(?,?);";
    private static final String UPDATE = "UPDATE public.companies SET name = ?, description = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM public.companies WHERE id = ?;";
    private static final String FIND_BY_ID = "SELECT id, name, description FROM public.companies WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT id, name, description FROM public.companies";

    public CompanyService(DatabaseManagerConnector manager) {
        this.manager = manager;
    }

    public CompanyDto create(CompanyDto companyDto){
        CompanyDao entity = companyConverter.convertToDao(companyDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.execute();
            entity.setId(getGeneratedKey(statement.getGeneratedKeys()));
            System.out.println("Company successfully created!");
            } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not created");
        }
        return companyDto;
    }

    public CompanyDto update(CompanyDto companyDto){
        CompanyDao entity = companyConverter.convertToDao(companyDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getId());
            if (statement.executeUpdate() == 0){
                System.out.println("Company not updated!");
            } else {
                System.out.println("Company successfully updated!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not updated");
        }
        return companyDto;
    }

    public void delete(Integer companyId){
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, companyId);
            if (statement.executeUpdate() == 0){
                System.out.println("Company not deleted!");
            } else {
                System.out.println("Company successfully deleted!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not deleted");
        }
    }

    public CompanyDto findById(Integer id){
        CompanyDao entity = new CompanyDao();
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("Company not found!");
            } else {
                while (result.next()) {
                    entity.setId(result.getInt("id"));
                    entity.setName(result.getString("name"));
                    entity.setDescription(result.getString("description"));
                    System.out.println(companyConverter.convertToDto(entity).toString());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not found");
        }
        return companyConverter.convertToDto(entity);
    }
    public void selectAll(){
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)){
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("No company found!");
            } else {
                System.out.println("Companies: ");
                System.out.println("Id | Name | Description ");
                while (result.next()) {
                    System.out.print(result.getInt(1) + " | ");
                    System.out.print(result.getString(2) + "| ");
                    System.out.print(result.getString(3) + "\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("No company found!");
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
