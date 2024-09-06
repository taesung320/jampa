package com.study.subject.testdomain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {
    Page<TestEntity> findAllByOrderByCreatedAtAsc(Pageable pageable);

    Slice<TestEntity> findSliceByOrderByCreatedAtAsc(Pageable pageable);
/*
* findAllByOrderByCreatedAtAsc

jpa repo 에 이런거 해도 작동하는 이유가 뭐여? 원리가 뭐지 regex 기반인가?
ChatGPT의 말:
ChatGPT
findAllByOrderByCreatedAtAsc와 같은 메서드 이름이 JPA Repository에서 작동하는 이유는 Spring Data JPA의 쿼리 메서드 기능 덕분입니다. 이 기능은 메서드 이름을 기반으로 자동으로 쿼리를 생성해주는 기능으로, 내부적으로 리플렉션(reflection)과 메서드 이름 파싱(parsing)을 사용합니다.

1. 메서드 이름 기반 쿼리 생성
Spring Data JPA는 메서드 이름을 분석하여 해당 메서드가 어떤 쿼리를 수행할지 결정합니다. 메서드 이름은 특정 키워드와 규칙을 따르며, 이 키워드와 규칙은 JPA의 쿼리 생성을 위한 기반이 됩니다.

예시 메서드 이름 구조
find: 데이터를 조회하는 쿼리.
findBy: 특정 필드를 기준으로 조회.
OrderBy: 결과를 정렬할 기준.
Asc 또는 Desc: 오름차순 또는 내림차순 정렬.
이런 식으로 조합된 메서드 이름은 Spring Data JPA가 SQL 쿼리를 생성하는 데 필요한 정보를 제공합니다.

2. 리플렉션과 파싱
Spring Data JPA는 메서드 이름을 다음과 같은 단계로 처리합니다:

리플렉션(Reflection): 메서드의 이름을 리플렉션을 통해 가져오고, 해당 이름을 분석합니다.
파싱(Parsing): 메서드 이름을 정해진 규칙에 따라 파싱하여, 각 키워드(find, By, Order, Asc, 등)를 분리합니다.
쿼리 생성: 파싱된 정보를 바탕으로 쿼리를 생성합니다. 예를 들어, findAllByOrderByCreatedAtAsc는 다음과 같은 SQL로 변환됩니다:
sql
코드 복사
SELECT * FROM post ORDER BY created_at ASC;
3. 정규 표현식(Regex)
Spring Data JPA는 메서드 이름을 해석할 때 내부적으로 정규 표현식을 사용할 수 있지만, 개발자가 직접 정규 표현식을 사용해야 하는 것은 아닙니다. 대신, Spring Data JPA는 이미 정의된 패턴을 사용하여 메서드 이름을 해석하고 SQL 쿼리로 변환합니다.

4. 요약
메서드 이름 기반 쿼리 생성: 메서드 이름의 구조를 기반으로 자동으로 쿼리를 생성.
리플렉션과 파싱: 메서드 이름을 리플렉션을 통해 가져오고, 정해진 규칙에 따라 파싱.
자동 SQL 생성: 파싱된 정보를 바탕으로 SQL 쿼리를 자동으로 생성.
이러한 기능 덕분에 개발자는 복잡한 SQL 쿼리를 직접 작성하지 않고도 간편하게 데이터베이스 작업을 수행할 수 있습니다.
*
* */

}
