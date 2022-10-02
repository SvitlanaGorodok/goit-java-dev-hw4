package service.converter;

import entities.dao.SkillDao;
import entities.dto.SkillDto;

public class SkillConverter {
    public SkillDao convertToDao (SkillDto skillDto){
        return new SkillDao(skillDto.getId(), skillDto.getArea(), skillDto.getLevel());
    }

    public SkillDto convertToDto (SkillDao skillDao){
        return new SkillDto(skillDao.getId(), skillDao.getArea(), skillDao.getLevel());
    }
}
