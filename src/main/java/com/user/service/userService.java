package com.user.service;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.user.member.userDTO;
import org.springframework.web.servlet.ModelAndView;

@Service
@Log
public class userService {

    private userDTO userDTO;
    private ModelAndView mv;

    private BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();




}
