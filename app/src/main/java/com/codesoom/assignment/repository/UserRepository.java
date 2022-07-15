package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 유저들의 저장소입니다.
 * 유저에 대한 삽입, 조회, 갱신, 삭제을 수행할 책임을 가지고 있습니다.
 */
@Repository
public class UserRepository {
    private Long id = 0L;
    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    /**
     * 식별자를 가진 유저를 저장 후 리턴합니다.
     *
     * @param name 유저명
     * @return 유저
     */
    public User add(String name) {
        User user = new User(id, name);
        userMap.put(id++, user);
        return user;
    }
}
