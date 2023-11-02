package com.enums.tourist.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.enums.tourist.domain.enums.MemberType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@SequenceGenerator(name = "member_seq", initialValue = 1, allocationSize = 1)
@DynamicUpdate
@Getter @Setter @ToString(exclude = "planners")
@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
   @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
   private Long id;
   
   private String username;
   
   
   private String password;
   
   
   private String realname;
   
   
   private String email;
   
   private LocalDateTime createDate;

   @Enumerated(EnumType.STRING)
   private MemberType memberType;
   
   @OneToMany(mappedBy = "member")
   private List<Planner> planners;
}
