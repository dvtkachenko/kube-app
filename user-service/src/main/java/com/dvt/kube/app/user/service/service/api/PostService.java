package com.dvt.kube.app.user.service.service.api;

import com.dvt.kube.app.user.service.entity.Post;

public interface PostService {
    Post create(Long userId, Post post);
}
