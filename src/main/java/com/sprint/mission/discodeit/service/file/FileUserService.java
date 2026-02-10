package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.dto.*;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class FileUserService implements UserService {
    private final String FILE_PATH = "users.dat";

    @SuppressWarnings("unchecked")
    private List<User> loadUsers() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UserResponseDto convertToDto(User user) {
        return new UserResponseDto(user, false);
    }

    @Override
    public UserResponseDto create(UserCreateDto dto) {
        List<User> users = loadUsers();
        User newUser = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
        users.add(newUser);
        saveUsers(users);
        return convertToDto(newUser);
    }

    @Override
    public UserResponseDto find(UUID userId) {
        User user = loadUsers().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("유저를 찾을 수 없습니다."));
        return convertToDto(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return loadUsers().stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public UserResponseDto update(UUID userId, UserUpdateDto dto) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.update(dto.getNickname(), null, dto.getPassword());
                saveUsers(users);
                return convertToDto(user);
            }
        }
        throw new NoSuchElementException("수정할 유저가 없습니다.");
    }

    @Override
    public void delete(UUID userId) {
        List<User> users = loadUsers();
        users.removeIf(u -> u.getId().equals(userId));
        saveUsers(users);
    }

    @Override
    public void updateStatus(UserStatusUpdateDto dto) {
    }
}