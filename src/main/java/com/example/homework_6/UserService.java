package com.example.homework_6;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String getUsername() {
        return userRepository.getUsername();
    }
}

