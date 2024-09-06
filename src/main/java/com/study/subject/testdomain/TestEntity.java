package com.study.subject.testdomain;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="memo_tbl")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    @Column(length=200, nullable = false)
    private String title;
    @Column(length=200, nullable = false)
    private String content;
    // 생성 시각 자동 기록
    @CreationTimestamp
    private LocalDateTime createdAt;

    // 업데이트 시각 자동 기록
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
/*
* TestEntity 클래스는 Java의 JPA (Java Persistence API)를 사용하여 데이터베이스와 매핑되는 엔티티 클래스를 정의한 것입니다. 각 애너테이션과 필드의 역할을 설명드리겠습니다.

1. 애너테이션 설명
@Entity:

이 클래스가 JPA의 엔티티임을 나타냅니다. 즉, 이 클래스의 인스턴스는 데이터베이스의 테이블과 매핑됩니다.
@Table(name="memo_tbl"):

이 애너테이션은 이 엔티티가 매핑될 데이터베이스의 테이블 이름을 지정합니다. 여기서는 memo_tbl이라는 테이블에 매핑됩니다.
@ToString:

Lombok 라이브러리의 애너테이션으로, 이 클래스의 toString() 메서드를 자동으로 생성합니다. 이 메서드를 사용하면 이 객체의 속성을 문자열로 출력할 수 있습니다.
@Getter와 @Setter:

Lombok 애너테이션으로, 각각의 필드에 대한 getter 및 setter 메서드를 자동으로 생성합니다. @Getter는 각 필드의 값을 읽을 수 있는 메서드를, @Setter는 각 필드의 값을 설정할 수 있는 메서드를 생성합니다.
@Builder:

Lombok 애너테이션으로, 빌더 패턴을 구현할 수 있게 해줍니다. 이를 통해 객체를 쉽게 생성할 수 있습니다. 예를 들어, TestEntity entity = TestEntity.builder().title("Title").content("Content").build();와 같이 사용할 수 있습니다.
@AllArgsConstructor:

모든 필드를 인자로 받는 생성자를 자동으로 생성합니다. 객체를 생성할 때 모든 필드를 한 번에 초기화할 수 있습니다.
@NoArgsConstructor:

인자가 없는 기본 생성자를 자동으로 생성합니다. JPA가 엔티티 객체를 생성할 때 필요합니다.
@Column(length=200, nullable = false):

이 애너테이션은 데이터베이스의 열(column) 속성을 정의합니다.
length=200: 이 필드의 최대 길이를 200자로 제한합니다.
nullable = false: 이 필드가 null 값을 가질 수 없음을 나타냅니다. 즉, 이 필드는 반드시 값을 가져야 합니다.
@Id:

이 필드가 엔티티의 기본 키(primary key임을 나타냅니다.
@GeneratedValue(strategy = GenerationType.IDENTITY):

이 애너테이션은 기본 키 값을 자동으로 생성하는 방법을 정의합니다.
GenerationType.IDENTITY: 데이터베이스에서 자동으로 생성된 ID 값을 사용하도록 설정합니다. 일반적으로 데이터베이스에서 해당 필드에 대한 AUTO_INCREMENT 속성을 사용하는 경우 사용됩니다.
@CreationTimestamp:

이 애너테이션은 객체가 생성될 때 자동으로 현재 시각을 기록하도록 합니다. createdAt 필드에 현재 날짜와 시간이 자동으로 저장됩니다.
@UpdateTimestamp:

이 애너테이션은 객체가 업데이트될 때마다 자동으로 현재 시각을 기록하도록 합니다. updatedAt 필드는 마지막으로 수정된 시각을 저장합니다.
* */