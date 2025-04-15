package com.metaverse.academy.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 데이터베이스 설정 관련 유틸리티 클래스
 * application.properties 파일에서 데이터베이스 연결 정보를 로드하고 JPA 설정을 제공함
 */
public class DatabaseConfig {
    
    private static final Properties properties = new Properties();
    
    // 클래스 로딩 시 초기화
    static {
        loadProperties();
    }
    
    /**
     * application.properties 파일에서 설정을 로드
     */
    private static void loadProperties() {
        try (InputStream is = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (is != null) {
                properties.load(is);
                System.out.println("application.properties에서 DB 설정 로드 완료");
            } else {
                System.err.println("application.properties 파일을 찾을 수 없습니다");
            }
        } catch (IOException e) {
            System.err.println("application.properties 로드 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * JPA 설정을 위한 속성 맵을 반환
     * @return JPA 속성 맵
     */
    public static Map<String, String> getJpaProperties() {
        Map<String, String> props = new HashMap<>();
        
        // 기존 javax.persistence에서 Jakarta EE 9+ 호환을 위해 jakarta.persistence로 변경
        props.put("jakarta.persistence.jdbc.driver", properties.getProperty("DB_DRIVER"));
        props.put("jakarta.persistence.jdbc.url", properties.getProperty("DB_URL"));
        props.put("jakarta.persistence.jdbc.user", properties.getProperty("DB_USER"));
        props.put("jakarta.persistence.jdbc.password", properties.getProperty("DB_PASSWORD"));
        
        return props;
    }
    
    /**
     * 특정 속성값을 조회
     * @param key 속성 키
     * @return 속성값
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * 현재 로드된 데이터베이스 연결 정보 출력
     */
    public static void printDatabaseInfo() {
        System.out.println("=== 데이터베이스 연결 정보 ===");
        System.out.println("드라이버: " + properties.getProperty("DB_DRIVER"));
        System.out.println("URL: " + properties.getProperty("DB_URL"));
        System.out.println("사용자: " + properties.getProperty("DB_USER"));
        System.out.println("========================");
    }
}
