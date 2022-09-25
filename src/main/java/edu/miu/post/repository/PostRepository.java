package edu.miu.post.repository;

import edu.miu.post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    public static final String FIND_POST_ID = "SELECT id FROM post";
    @Query(value = FIND_POST_ID, nativeQuery = true)

    List<Long> findAllProjectId();
}
