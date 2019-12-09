package com.camavilca.controllers.pasiente;

import com.camavilca.model.Paciente;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import pe.albatross.octavia.dynatable.DynatableFilter;
import pe.albatross.octavia.dynatable.DynatableResponse;
import pe.albatross.zelpers.miscelanea.ExceptionHandler;
import pe.albatross.zelpers.miscelanea.JsonHelper;
import pe.albatross.zelpers.miscelanea.JsonResponse;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String rutaModulo = this.getClass().getAnnotation(RequestMapping.class).value()[0];

    @Autowired
    PasienteService service;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute(rutaModulo, rutaModulo);
        return "paciente/paciente";
    }

    @ResponseBody
    @RequestMapping("save")
    public JsonResponse save(@RequestBody Paciente paciente) {
        JsonResponse response = new JsonResponse();
        try {
            if (paciente.getId() == null) {
                response.setMessage("Paciente Guardado");
            } else {
                response.setMessage("Datos del Paciente Actualizado");
            }
            service.save(paciente);
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("delete")
    public JsonResponse delete(@RequestBody Paciente panamericano) {
        JsonResponse response = new JsonResponse();
        try {
            service.delete(panamericano);
            response.setMessage("Paciente Eliminado");
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("all")
    public JsonResponse all() {
        JsonResponse response = new JsonResponse();
        JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = new ArrayNode(jsonFactory);
        try {
            List<Paciente> pasientes = service.all();
            for (Paciente pasiente : pasientes) {
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
    @RequestMapping("allDynatable")
    public DynatableResponse all(DynatableFilter filter) {
        DynatableResponse json = new DynatableResponse();
        List<Paciente> pacientes = service.allDynatable(filter);
        ArrayNode array = new ArrayNode(JsonNodeFactory.instance);
        pacientes.forEach((paciente) -> {
            ObjectNode node = JsonHelper.createJson(paciente, JsonNodeFactory.instance,
                    new String[]{"*"});
            array.add(node);
        });
        json.setData(array);
        json.setTotal(filter.getTotal());
        json.setFiltered(filter.getFiltered());
        return json;
    }

}
