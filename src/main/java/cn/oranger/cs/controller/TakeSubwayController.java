package cn.oranger.cs.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oranger
 * @date 2021/1/17
 */
@RestController
@RequestMapping("/take")
public class TakeSubwayController {

    @PostMapping("/subway")
    public String take(){

        return "票根";
    }
}
