package com.enums.tourist.domain;

import java.util.ArrayList;
import java.util.List;

import com.enums.tourist.domain.embedd.Tourist;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "board_seq_gen", sequenceName = "board_seq", 
   allocationSize = 1, initialValue = 1)
@Getter @Setter
public class Board {
   
   @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
   @Column(name = "board_id")
   private Long id;
   
   @Embedded
   private Tourist tourist;

   @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
   private List<Like> likes = new ArrayList<>();
   public void addLike(Like like){
      this.likes.add(like);
      like.setBoard(this);
   }

   // @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
   // private List<Comment> comments = new ArrayList<>();
   // public void addComment(Comment comment){
   //    this.comments.add(comment);
   //    comment.setBoard(this);
   // }
}
