package com.seoulit.common;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController implements ErrorController {

    @GetMapping({ "/", "/error" })
    public String getEmp() {
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}