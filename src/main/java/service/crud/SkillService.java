package service.crud;

import config.DatabaseManagerConnector;
import entities.dao.SkillDao;
import entities.dto.SkillDto;
import service.converter.SkillConverter;

import java.sql.*;

public class SkillService {
    SkillConverter skillConverter = new SkillConverter();
    private final DatabaseManagerConnector manager;
    private static final String INSERT = "INSERT INTO public.skills (area, level) VALUES(?,?);";
    private static final String UPDATE = "UPDATE public.skills SET area = ?, level = ? WHERE id = ?;";
    private static final String DELETE = "DELETE FROM public.skills WHERE id = ?;";
    private static final String FIND_BY_ID = "SELECT id, area, level FROM public.skills WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT id, area, level FROM public.skills;";

    public SkillService(DatabaseManagerConnector manager) {
        this.manager = manager;
    }

    public SkillDto create(SkillDto skillDto){
        SkillDao entity = skillConverter.convertToDao(skillDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, entity.getArea());
            statement.setString(2, entity.getLevel());
            statement.execute();
            entity.setId(getGeneratedKey(statement.getGeneratedKeys()));
            System.out.println("Skill successfully created!");
            } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Skill not created");
        }
        return skillDto;
    }

    public SkillDto update(SkillDto skillDto){
        SkillDao entity = skillConverter.convertToDao(skillDto);
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, entity.getArea());
            statement.setString(2, entity.getLevel());
            statement.setInt(3, entity.getId());
            if (statement.executeUpdate() == 0){
                System.out.println("Skill not updated!");
            } else {
                System.out.println("Skill successfully updated!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Skill not updated");
        }
        return skillDto;
    }

    public void delete(Integer skillId){
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, skillId);
            if (statement.executeUpdate() == 0){
                System.out.println("Skill not deleted!");
            } else {
                System.out.println("Skill successfully deleted!");
            }
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
            if (!result.isBeforeFirst()){
                System.out.println("Skill not found!");
            } else {
                while (result.next()) {
                    entity.setId(result.getInt("id"));
                    entity.setArea(result.getString("area"));
                    entity.setLevel(result.getString("level"));
                    System.out.println(skillConverter.convertToDto(entity).toString());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Skill not found");
        }
        return skillConverter.convertToDto(entity);
    }
    public void selectAll(){
        ResultSet result;
        try (Connection connection = manager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)){
            result = statement.executeQuery();
            if (!result.isBeforeFirst()){
                System.out.println("No skills found!");
            } else {
                System.out.println("Skills: ");
                System.out.println("Id | Area | Level ");
                while (result.next()) {
                    System.out.print(result.getInt(1) + " | ");
                    System.out.print(result.getString(2) + "| ");
                    System.out.print(result.getString(3) + "\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("No skills found!");
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
