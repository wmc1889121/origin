package com.allen.origin.service;

import com.allen.origin.entity.domain.Education;
import com.allen.origin.repository.EducationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EducationService implements IEducationService {
    @Resource
    private EducationRepository educationRepository;

    @Override
    public List<Education> listByUser(long userId) {
        return educationRepository.selectByUser(userId);
    }
}
