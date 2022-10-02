package service.converter;

import entities.dao.CompanyDao;
import entities.dao.CustomerDao;
import entities.dto.CompanyDto;
import entities.dto.CustomerDto;

public class CompanyConverter {
    public CompanyDao convertToDao (CompanyDto customerDto){
        return new CompanyDao(customerDto.getId(), customerDto.getName(), customerDto.getDescription());
    }
    public CompanyDto convertToDto (CompanyDao customerDao){
        return new CompanyDto(customerDao.getId(), customerDao.getName(), customerDao.getDescription());
    }
}
