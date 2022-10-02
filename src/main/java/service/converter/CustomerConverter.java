package service.converter;

import entities.dao.CustomerDao;
import entities.dto.CustomerDto;

public class CustomerConverter {
    public CustomerDao convertToDao (CustomerDto customerDto){
        return new CustomerDao(customerDto.getId(), customerDto.getName(), customerDto.getDescription());
    }

    public CustomerDto convertToDto (CustomerDao customerDao){
        return new CustomerDto(customerDao.getId(), customerDao.getName(), customerDao.getDescription());
    }
}
