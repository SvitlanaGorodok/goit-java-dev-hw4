package service.crud;

import config.DatabaseManagerConnector;
import entities.dao.CompanyDao;
import entities.dto.CompanyDto;
import service.converter.CompanyConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyService {
    CompanyConverter companyConverter = new CompanyConverter();
    private final DatabaseManagerConnector manager;
    private static final String INSERT = "INSERT INTO public.companies (name, description) VALUES(?,?);";
    private static final String UPDATE = "UPDATE public.companies SET name = ?, description = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM public.companies WHERE id = ? AND name = ? AND description = ?;";
    private static final String FIND_BY_ID = "SELECT id, name, description FROM public.companies WHERE id = ?;";

    public CompanyService(DatabaseManagerConnector manager) {
        this.manager = manager;
    }

    public CompanyDto create(CompanyDto companyDto){
        CompanyDao entity = companyConverter.convertToDao(companyDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.execute();
            } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not created");
        }
        return findById(entity.getId());
    }

    public CompanyDto update(CompanyDto companyDto){
        CompanyDao entity = companyConverter.convertToDao(companyDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not updated");
        }
        return findById(entity.getId());
    }

    public void delete(CompanyDto companyDto){
        CompanyDao entity = companyConverter.convertToDao(companyDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getDescription());
            statement.execute();
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
            while (result.next()){
                entity.setId(result.getInt("id"));
                entity.setName(result.getString("name"));
                entity.setDescription(result.getString("description"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Company not found");
        }
        return companyConverter.convertToDto(entity);
    }
}
