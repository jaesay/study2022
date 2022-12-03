package com.example.todoappbackend.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Todo")
public class TodoEntity {

  @Id
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String id; // 이 오브젝트의 아이디
  private String userId; // 이 오브젝트를 생성한 유저의 아이디
  private String title; // Todo 타이틀 예) 운동 하기
  private boolean done; // true - todo를 완료한 경우(checked)
}
