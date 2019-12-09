package com.camavilca.controllers.usuario;

import com.camavilca.model.Usuario;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.albatross.zelpers.miscelanea.ExceptionHandler;
import pe.albatross.zelpers.miscelanea.JsonResponse;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Controller
@RequestMapping("/")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String rutaModulo = this.getClass().getAnnotation(RequestMapping.class).value()[0];

    @Autowired
    UsuarioService service;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute(rutaModulo, rutaModulo);
        return "usuario/usuario";
    }

    @ResponseBody
    @RequestMapping("registro")
    public JsonResponse save(@RequestBody Usuario usuario) {
        JsonResponse response = new JsonResponse();
        try {
            service.save(usuario);
            response.setMessage("Registro Satisfactorio");
            response.setSuccess(Boolean.TRUE);
            List<String> datos = Arrays.asList("admin", usuario.getNombre());
            response.setData(datos);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("iniciar")
    public JsonResponse iniciar(@RequestBody Usuario usuario) {
        JsonResponse response = new JsonResponse();
        try {
            Usuario usua = service.iniciar(usuario);
            response.setMessage("Bienvenido");
            response.setSuccess(Boolean.TRUE);
            List<String> datos = Arrays.asList("admin", usua.getNombre());
            response.setData(datos);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

}
