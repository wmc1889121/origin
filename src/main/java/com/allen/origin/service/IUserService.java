package com.allen.origin.service;

import com.allen.origin.entity.domain.User;
import com.allen.origin.entity.exception.NotFoundException;

import java.util.Collection;

public interface IUserService {

    User get(long id, Collection<String> optional) throws NotFoundException;

    long create(User user);

    boolean save(long id, User user, boolean updateNull) throws NotFoundException;

    int delete(long id);
}
