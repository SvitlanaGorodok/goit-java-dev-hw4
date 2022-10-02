package entities.dto;

import java.sql.Date;

public class ProjectDto {
    private Integer id;
    private String name;
    private String description;
    private Date startDate;
    public ProjectDto() {}
    public ProjectDto(Integer id, String name, String description, Date startDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return  "id = " + id +
                ", name = '" + name + '\'' +
                ", description = '" + description + '\'' +
                ", startDate = " + startDate;
    }
}
