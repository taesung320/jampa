package com.study.subject.testdomain;

import com.study.subject.testdomain.dto.*;
        import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private TestRepository testRepository;
    public TestService(TestRepository TestRepository) {
        this.testRepository = TestRepository;
    }
    public TestEntity getTest(Long id) throws Exception {

        return testRepository.findById(id).orElseThrow(() -> new Exception("User not found with id: " + id));
    }

    public Page<TestEntity> getTestList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return testRepository.findAllByOrderByCreatedAtAsc(pageable);
    }

    public Slice<TestEntity> getTestListBySlicce(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return testRepository.findSliceByOrderByCreatedAtAsc(pageable);
    }

    public void postTest(CreateRequest test) {
        testRepository.save(TestEntity.builder().
                title(test.getTitle()).
                content(test.getContent()).
                build());
    }

    public void putTest(Long id, UpdateRequest test) throws Exception {
        TestEntity target = testRepository.findById(id).orElseThrow(() -> new Exception("User not found with id: " + id));
        target.setTitle(test.getTitle());
        target.setContent(test.getContent());
        testRepository.save(target);
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}


/*
*
* package com.study.subject.testdomain;

import com.study.subject.testdomain.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private TestRepository testRepository;
    public TestService(TestRepository TestRepository) {
        this.testRepository = TestRepository;
    }
    public TestEntity getTest(Long id) throws Exception {

        return testRepository.findById(id).orElseThrow(() -> new Exception("User not found with id: " + id));
    }

    public Page<TestEntity> getTestList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return testRepository.findAllByOrderByCreatedAtAsc(pageable);
    }

    public Slice<TestEntity> getTestListBySlicce(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return testRepository.findSliceByOrderByCreatedAtAsc(pageable);
    }

    public void postTest(CreateRequest test) {
        testRepository.save(TestEntity.builder().
                title(test.getTitle()).
                content(test.getContent()).
                build());
    }

    public void putTest(Long id, UpdateRequest test) throws Exception {
        TestEntity target = testRepository.findById(id).orElseThrow(() -> new Exception("User not found with id: " + id));
        target.setTitle(test.getTitle());
        target.setContent(test.getContent());
        testRepository.save(target);
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}


throw error 와
builder ,setter 의 차이점 , jpa repository method 중점으로 동작 설명해줘
ChatGPT의 말:
ChatGPT
아래에서 throw 구문, builder와 setter의 차이점, JPA Repository 메서드의 동작 방식을 설명하겠습니다.

1. throw 구문
throw 구문은 Java에서 예외를 발생시키는 데 사용됩니다. 특정 조건이 충족되지 않았을 때 또는 오류가 발생했을 때 예외를 던져 호출자에게 알릴 수 있습니다. 예를 들어, getTest 메서드에서 testRepository.findById(id).orElseThrow(...)를 사용하여 사용자가 요청한 ID로 찾는 엔티티가 없을 경우 Exception을 던집니다.

java
코드 복사
return testRepository.findById(id)
    .orElseThrow(() -> new Exception("User not found with id: " + id));
장점: 예외를 던짐으로써, 호출자는 오류에 대해 적절히 대응할 수 있는 기회를 갖게 됩니다. 이를 통해 프로그램의 흐름을 제어할 수 있습니다.
2. builder와 setter의 차이점
Builder Pattern:

장점: 객체 생성 시 가독성을 높이고, 필요한 필드만 설정할 수 있으며, 불변 객체를 쉽게 생성할 수 있습니다. 여러 개의 필드가 있는 객체를 생성할 때 특히 유용합니다.
사용 예시:
java
코드 복사
TestEntity testEntity = TestEntity.builder()
    .title(test.getTitle())
    .content(test.getContent())
    .build();
장점:
가독성: 객체 생성 시 가독성이 높아짐
불변 객체 생성: 객체가 불변성이 요구되는 경우 유용
Setter Method:

장점: 객체를 생성한 후에 필드 값을 설정할 수 있습니다. 필드가 변경 가능한 경우 유용합니다.
사용 예시:
java
코드 복사
TestEntity target = new TestEntity();
target.setTitle(test.getTitle());
target.setContent(test.getContent());
단점:
객체가 불완전한 상태로 생성될 수 있으며, 잘못된 상태를 방지하기 어려울 수 있음
3. JPA Repository Method의 동작 설명
JPA Repository 메서드는 Spring Data JPA에서 제공하는 기능으로, 데이터베이스와 상호작용하는 데 사용됩니다. 각 메서드의 동작 방식은 다음과 같습니다:

findById:

주어진 ID로 데이터베이스에서 엔티티를 찾습니다.
결과는 Optional로 반환되어, 존재하지 않을 경우 orElseThrow를 사용하여 예외를 던질 수 있습니다.
save:

새 엔티티를 데이터베이스에 저장합니다. 만약 ID가 존재한다면 해당 엔티티를 업데이트합니다.
빌더 패턴이나 setter 메서드를 사용하여 엔티티의 속성을 설정한 후, save 메서드를 호출합니다.
deleteById:

주어진 ID로 엔티티를 데이터베이스에서 삭제합니다. 만약 해당 ID의 엔티티가 존재하지 않으면, 아무런 영향을 주지 않습니다.
findAllByOrderByCreatedAtAsc:

모든 엔티티를 가져오면서 createdAt 필드를 기준으로 오름차순으로 정렬하여 반환합니다.
페이징 처리를 위해 Pageable 객체를 인자로 받아 처리합니다.
findSliceByOrderByCreatedAtAsc:

모든 엔티티를 가져오면서 createdAt 필드를 기준으로 오름차순으로 정렬하고, 슬라이스 방식으로 반환합니다.
슬라이스는 페이지의 마지막이 아닌, 다음 페이지가 있는지 여부만 확인할 수 있도록 설계되었습니다.
요약
**throw**는 예외를 발생시키고, **builder**는 객체 생성을 간결하게 하고 가독성을 높이며, **setter**는 개별적으로 속성을 설정할 수 있게 합니다.
JPA Repository 메서드는 데이터베이스와의 상호작용을 간편하게 하여 CRUD 작업을 수행하는 데 필수적인 역할을 합니다.*/
