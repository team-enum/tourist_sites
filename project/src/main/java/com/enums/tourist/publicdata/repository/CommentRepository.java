package com.enums.tourist.publicdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enums.tourist.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    
	List<Comment> findAllByContentId(Long contentId);

	@Modifying
    @Query("UPDATE comments c SET c.commentText = :updatedComment WHERE c.id = :commentId")
    void updateCommentTextByCommentId(@Param("commentId")Long commentId, @Param("updatedComment")String updatedComment);
}
