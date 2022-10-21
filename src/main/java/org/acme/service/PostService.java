package org.acme.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.extern.slf4j.Slf4j;
import org.acme.dto.PostDto;
import org.acme.model.Post;
import org.acme.model.Tag;
import org.acme.repository.PostRepository;
import org.acme.repository.TagsRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class PostService {

    @Inject
    private
    TagsRepository tagsRepository;

    @Inject
    private PostRepository postRepository;

    public List<Post> getAll(){
        return postRepository.findAll().stream().collect(Collectors.toList());
    }

    @Transactional
    public PostDto createPost(PostDto postDto){
        log.info("TOTAL TAGS {}", postDto.getTags().length);
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());

        for (String tags : postDto.getTags()){
            Tag tag = tagsRepository.findByLabel(tags);
            post.getTags().add(tag);
        }
        PanacheEntityBase.persist(post);
        return postDto;
    }

    public Post getPostById(Long id){
        return postRepository.
                findByIdOptional(id).orElseThrow(() -> new NotFoundException("Data Post Not Found"));
    }

    @Transactional
    public PostDto updatePost(PostDto postDto, Long id){

        Post post = postRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Data Post Not Found"));

        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());

        for (String tags : postDto.getTags()){
            Tag tag = tagsRepository.findByLabel(tags);
            post.getTags().add(tag);
        }
        PanacheEntityBase.persist(post);
        return postDto;
    }

    @Transactional
    public Boolean deletePost(Long id){
        Post post = postRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Data Post Not Found"));
        postRepository.delete(post);
        return Boolean.TRUE;
    }

}
