package org.example.service;

public class UserService {
    final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setUser(String user) {
        threadLocal.set(user);
    }

    public void printUser() {
        System.out.println(threadLocal.get());
    }
}
