package com.allen.origin.service;

import com.allen.origin.entity.domain.Comment;
import com.allen.origin.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Resource
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listByUser(long userId) {
        return commentRepository.selectByUser(userId);
    }

    public static void main(String[] args) throws Exception {
        String d1 = "1990-09-16";
        String d2 = "2012-06-30";
        String d3 = "2012-09-01";
        String d4 = "2015-06-30";
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(f.parse(d1).getTime());
        System.out.println(f.parse(d2).getTime());
        System.out.println(f.parse(d3).getTime());
        System.out.println(f.parse(d4).getTime());
    }
}
