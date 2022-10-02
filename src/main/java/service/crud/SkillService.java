package service.crud;

import config.DatabaseManagerConnector;
import entities.dao.SkillDao;
import entities.dto.SkillDto;
import service.converter.SkillConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillService {
    SkillConverter skillConverter = new SkillConverter();
    private final DatabaseManagerConnector manager;
    private static final String INSERT = "INSERT INTO public.skills (area, level) VALUES(?,?);";
    private static final String UPDATE = "UPDATE public.skills SET area = ?, level = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM public.skills WHERE id = ? AND area = ? AND level = ?;";
    private static final String FIND_BY_ID = "SELECT id, area, level FROM public.skills WHERE id = ?;";

    public SkillService(DatabaseManagerConnector manager) {
        this.manager = manager;
    }

    public SkillDto create(SkillDto skillDto){
        SkillDao entity = skillConverter.convertToDao(skillDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)){
            statement.setString(1, entity.getArea());
            statement.setString(2, entity.getLevel());
            statement.execute();
            } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Skill not created");
        }
        return findById(entity.getId());
    }

    public SkillDto update(SkillDto skillDto){
        SkillDao entity = skillConverter.convertToDao(skillDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, entity.getArea());
            statement.setString(2, entity.getLevel());
            statement.setInt(3, entity.getId());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Skill not updated");
        }
        return findById(entity.getId());
    }

    public void delete(SkillDto skillDto){
        SkillDao entity = skillConverter.convertToDao(skillDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getArea());
            statement.setString(3, entity.getLevel());
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Skill not deleted");
        }
    }

    public SkillDto findById(Integer id){
        SkillDao entity = new SkillDao();
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()){
                entity.setId(result.getInt("id"));
                entity.setArea(result.getString("area"));
                entity.setLevel(result.getString("level"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Skill not found");
        }
        return skillConverter.convertToDto(entity);
    }
}
