package com.allen.origin.service;

import com.allen.origin.entity.domain.Education;

import java.util.List;

public interface IEducationService {
    List<Education> listByUser(long userId);
}
