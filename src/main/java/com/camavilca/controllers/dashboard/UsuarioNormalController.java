package com.camavilca.controllers.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("usuario")
public class UsuarioNormalController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "dashboard/usuario";
    }

}
