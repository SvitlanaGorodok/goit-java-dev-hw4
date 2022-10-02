package service.converter;

import entities.dao.DeveloperDao;
import entities.dto.DeveloperDto;

public class DeveloperConverter {

    public DeveloperDao convertToDao(DeveloperDto developerDto){
        return new DeveloperDao(developerDto.getId(), developerDto.getFirstName(),
                developerDto.getLastName(), developerDto.getAge(), developerDto.getSalary());
    }

    public DeveloperDto convertToDto(DeveloperDao developerDao){
        return new DeveloperDto(developerDao.getId(), developerDao.getFirstName(),
                developerDao.getLastName(), developerDao.getAge(), developerDao.getSalary());
    }
}
