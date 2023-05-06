package com.example.homework_6;

public class Application {
    public static void main(String[] args) throws Exception {
        Context context = new Context("com.example");
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.getUsername()); // Output: JohnDoe
    }
}