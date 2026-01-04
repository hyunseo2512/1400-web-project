package com.community.demo.service;

import com.community.demo.dto.UserDTO;
import com.community.demo.entity.AuthRole;
import com.community.demo.entity.User;
import com.community.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    @Transactional
//    @Override
//    public String register(UserDTO userDTO) {
//        userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd()));
//        User user = convertDtoToEntity(userDTO);
//        user.addAuth(AuthRole.USER);
//
//        return userRepository.save(user).getEmail();
//    }

    @Transactional
    @Override
    public String register(UserDTO userDTO) {
        // 1. 비밀번호 일치 확인 (백엔드 검증)
        if (!userDTO.getPwd().equals(userDTO.getPwdConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 2. fName + lName 합쳐서 nickName 설정
        String fullName = userDTO.getLastName() + userDTO.getFirstName();
        userDTO.setNickName(fullName);

        // 3. 비밀번호 암호화
        userDTO.setPwd(passwordEncoder.encode(userDTO.getPwd()));

        // 4. 엔티티 변환 및 저장
        User user = convertDtoToEntity(userDTO);
        user.addAuth(AuthRole.USER);

        return userRepository.save(user).getEmail();
    }

    @Override
    public List<UserDTO> getList() {
        List<User> userList = userRepository.findAllWithAuthList();

        return userList.stream()
                .map(this::convertEntityToDTO)
                .toList();
    }

    @Override
    public void lastLoginUpdate(String name) {

    }
}
