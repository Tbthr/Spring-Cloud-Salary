package com.salary;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class getPsd {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("aaa"));
    }
}
