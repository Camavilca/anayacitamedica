package com.camavilca.controllers.usuario;

import com.camavilca.model.Usuario;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import pe.albatross.zelpers.miscelanea.JsonHelper;
import pe.albatross.zelpers.miscelanea.JsonResponse;
import pe.albatross.zelpers.miscelanea.ObjectUtil;
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
    @RequestMapping("usuario/all")
    public JsonResponse all() {
        JsonResponse response = new JsonResponse();
        JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = new ArrayNode(jsonFactory);
        try {
            List<Usuario> pasientes = service.all();
            for (Usuario pasiente : pasientes) {
                ObjectNode node = JsonHelper.createJson(pasiente, jsonFactory, new String[]{"*"});
                arrayNode.add(node);
            }
            response.setData(arrayNode);
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
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
    @RequestMapping("delete")
    public JsonResponse delete(@RequestBody Usuario usuario) {
        JsonResponse response = new JsonResponse();
        try {
            service.delete(usuario);
            response.setMessage("Eliminado Satisfactorio");
            response.setSuccess(Boolean.TRUE);
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
            Usuario usuarioDB = service.iniciar(usuario);
            ObjectUtil.printAttr(usuarioDB);
            response.setMessage("Bienvenido");
            response.setSuccess(Boolean.TRUE);
            response.setData(usuarioDB);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

}
