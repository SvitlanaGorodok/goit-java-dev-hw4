package entities.dao;

public class SkillDao {
    private Integer id;
    private String area;
    private String level;
    public SkillDao() {}
    public SkillDao(Integer id, String area, String level) {
        this.id = id;
        this.area = area;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
