package com.allen.origin.service;

import com.allen.origin.entity.domain.Comment;
import com.allen.origin.entity.domain.Education;

import java.util.List;

public interface ICommentService {
    List<Comment> listByUser(long userId);
}
