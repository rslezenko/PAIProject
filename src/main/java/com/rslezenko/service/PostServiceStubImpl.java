package com.rslezenko.service;

import com.rslezenko.model.Post;
import com.rslezenko.model.User;
import com.rslezenko.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceStubImpl implements PostService {
  /*  private List<Post> posts = new ArrayList<Post>() {{
        add(new Post(1L, "First Post", "<p>Line #1.</p><p>Line #2</p>", null));
        add(new Post(2L, "Second Post",
                "Second post content:<ul><li>line 1</li><li>line 2</li></p>",
                new User(10L, "pesho10", "Peter Ivanov")));
        add(new Post(3L, "Post #3", "<p>The post number 3 nice</p>",
                new User(10L, "merry", null)));
        add(new Post(4L, "Forth Post", "<p>Not interesting post</p>", null));
        add(new Post(5L, "Post Number 5", "<p>Just posting</p>", null));
        add(new Post(6L, "Sixth Post", "<p>Another interesting post</p>", null));
    }};

*/

    @Autowired
    private PostRepository postRepo;
    @Override
    public List<Post> findAll() {
        return this.postRepo.findAll();
    }

    @Override
    public Post findById(Long id) {
        return this.postRepo.findOne(id);
    }
    @Override
    public Post create(Post post) {
        return this.postRepo.save(post);
    }
    @Override
    public Post edit(Post post) {
        return this.postRepo.save(post);
    }
    @Override
    public void deleteById(Long id) {
        this.postRepo.delete(id);
    }
/*
    @Override
    public List<Post> findLatest5() {
        return this.posts.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Post findById(Long id) {
        return this.posts.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(Post post) {
        post.setId(this.posts.stream().mapToLong(
                p -> p.getId()).max().getAsLong() + 1);
        this.posts.add(post);
        return post;
    }

    @Override
    public Post edit(Post post) {
        for (int i = 0; i < this.posts.size(); i++) {
            if (Objects.equals(this.posts.get(i).getId(), post.getId())) {
                this.posts.set(i, post);
                return post;
            }
        }
        throw new RuntimeException("Post not found: " + post.getId());
    }

    @Override
    public void deleteById(Long id) {
        for (int i = 0; i < this.posts.size(); i++) {
            if (Objects.equals(this.posts.get(i).getId(), id)) {
                this.posts.remove(i);
                return;
            }
        }
        throw new RuntimeException("Post not found: " + id);
    }
    */
}
