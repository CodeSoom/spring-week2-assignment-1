package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.User;
import com.codesoom.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 유저의 비즈니스 로직을 처리하는 역할을 가지며 Business Layer에 해당합니다.
 * Presentation Layer에게 Persistence Layer 기능을 추상화해서 제공할 책임을 가지고 있습니다.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 유저를 생성해서 리턴합니다.
     *
     * @param name 유저명
     * @return 유저
     */
    public User create(String name) {
        return userRepository.add(name);
    }
}
