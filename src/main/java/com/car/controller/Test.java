package com.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhengjie on 2020/4/25.
 */
@Controller
@RequestMapping("/car")
public class Test {
    @RequestMapping("/car1")
    public ModelAndView order()    {
        return new ModelAndView("echart");
    }
}
