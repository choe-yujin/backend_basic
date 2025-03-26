package com.devyujin.jdbc.service;


import com.devyujin.jdbc.dao.RoleDao;
import com.devyujin.jdbc.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/*
 * 📌 Service 레이어란?
 * ✅ Service Layer의 역할
 * Service Layer(서비스 레이어)는 비즈니스 로직을 처리하는 계층으로, 주된 역할은 다음과 같다:
 * - 비즈니스 로직 처리: 데이터베이스와 직접적으로 상호작용하는 DAO(Data Access Object) 계층을 활용하여,
 *   필요한 데이터를 가공하거나 추가적인 로직을 수행.
 *
 * - 트랜잭션 관리: 여러 개의 데이터 변경 작업이 원자성을 유지하도록 **commit() / rollback()**을 활용해 트랜잭션을 관리.
 * - 예외 처리: DAO에서 발생한 SQLException을 상위 계층(View)에서 처리할 수 있도록 변환 또는 로깅.
 * - 데이터 검증: 요청된 데이터가 유효한지 검증한 후 처리.
 * */
public class RoleService {

    /*
     * SLF4J (Simple Logging Facade for Java)란?
     * SLF4J는 다양한 로깅 프레임워크에 대한 추상화 계층을 제공하는 라이브러리이다.
     * 이를 통해 개발자는 특정 로깅 구현체에 종속되지 않고, 일관된 API를 사용하여 로그를 기록할 수 있다.
     * 즉, 로그를 다양한 형태로 남기기 위한 인터페이스이다.
     *
     * SLF4J는 다음과 같은 특징을 가지고 있습니다:
     * 1. 로깅 구현체 독립성: SLF4J를 사용하면 Log4j, Logback, java.util.logging 등 다양한 로깅 프레임워크를 쉽게 교체할 수 있다.
     * 2. 간편한 로깅 : SLF4J는 간단한 API를 제공하여 로그 메시지를 쉽게 기록할 수 있도록 합니다.
     * 3. 성능 : SLF4J는 로그 레벨에 따라 불필요한 문자열 생성과 같은 비용을 줄이기 위해 지연 평가를 지원한다.
     * 4. 다양한 로깅 레벨 지원: DEBUG, INFO, WARN, ERROR 등 다양한 로그 레벨을 지원하여 로그의 중요도에 따라 적절하게 기록할 수 있다.
     *
     * 위의 Logger 객체는 LessonService 클래스의 로그를 기록하기 위해 SLF4J를 사용하여 초기화된 것입니다.
     */
    private static final Logger log = LoggerFactory.getLogger(RoleService.class);


    private final Connection connection;
    private final RoleDao roleDao;


    public RoleService(Connection connection) {
        this.connection = connection;
        this.roleDao = new RoleDao(connection);
    }

    /**
     * 📌 ID를 기반으로 역할(Role) 조회
     * - 주어진 ID에 해당하는 역할이 존재하는지 확인 후 반환
     * - 존재하지 않으면 예외 발생
     *
     * @param roleId 조회할 역할 ID
     * @return Role - 조회된 역할 객체
     * @throws IllegalArgumentException 역할이 존재하지 않을 경우 발생
     * @throws SQLException 데이터베이스 접근 중 오류 발생 시
     */
    public Role getRoleById(int roleId) throws SQLException {
        Role role = roleDao.getRoleById(roleId);
        if (role == null) {
            throw new IllegalArgumentException("해당 ID의 역할을 찾을 수 없습니다.");
        }
        return role;
    }


}