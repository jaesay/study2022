package com.example.keywordalarmappbackend.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "member")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@AllArgsConstructor
@Builder
public class MemberEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  private String authProvider;

  public static MemberEntity create(String username, String authProvider) {
    MemberEntity entity = new MemberEntity();
    entity.username = username;
    entity.authProvider = authProvider;
    return entity;
  }
}
