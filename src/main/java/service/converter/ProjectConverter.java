package service.converter;

import entities.dao.ProjectDao;
import entities.dto.ProjectDto;

public class ProjectConverter {
    public ProjectDao convertToDao(ProjectDto projectDto){
        return new ProjectDao(projectDto.getId(), projectDto.getName(), projectDto.getDescription(), projectDto.getStartDate());
    }

    public ProjectDto convertToDto(ProjectDao projectDao){
        return new ProjectDto(projectDao.getId(), projectDao.getName(), projectDao.getDescription(), projectDao.getStartDate());
    }
}
