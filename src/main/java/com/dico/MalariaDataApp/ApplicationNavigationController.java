package com.dico.MalariaDataApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationNavigationController {

    @GetMapping("")
    public String home () {
        return "index";
    }

    @GetMapping("/deskofficers")
    public String deskofficers(){
        return "/deskofficers/index";
    }


    @GetMapping("/report")
    public String report(){
        return "/report/index";
    }

    @GetMapping("/settings")
    public String role(){
        return "/settings/index";
    }

    @GetMapping("/security")
    public String security(){
        return "/security/index";
    }


}
