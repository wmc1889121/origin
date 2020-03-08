package com.allen.origin.service;

import com.allen.origin.entity.domain.User;
import com.allen.origin.entity.exception.NotFoundException;
import com.allen.origin.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class UserService implements IUserService {
    private static final String OPTIONAL_EDUCATIONS = "educations";
    private static final String OPTIONAL_COMMENTS = "comments";

    @Resource
    private UserRepository userRepository;
    @Resource
    private IEducationService educationService;
    @Resource
    private ICommentService commentService;

    @Override
    public User get(long id, Collection<String> optional) {
        User user = userRepository.select(id);
        if (user == null)
            throw new NotFoundException();

        if (CollectionUtils.isEmpty(optional))
            return user;

        if (optional.contains(OPTIONAL_EDUCATIONS))
            user.setEducations(educationService.listByUser(id));

        if (optional.contains(OPTIONAL_COMMENTS))
            user.setComments(commentService.listByUser(id));

        return user;
    }

    @Override
    public long create(User user) {
        return 0;
    }

    @Override
    public boolean save(long id, User user, boolean updateNull) {
        return false;
    }

    @Override
    public int delete(long id) {
        return userRepository.delete(id);
    }
}
