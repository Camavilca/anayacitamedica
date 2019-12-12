package com.camavilca.controllers.citamedica;

import com.camavilca.model.CitaMedica;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("citamedica")
public class CitaMedicaController {

    @Autowired
    CitaMedicaService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        return "citamedicaadmin/citamedica";
    }

    @ResponseBody
    @RequestMapping("save")
    public JsonResponse save(@RequestBody CitaMedica citaMedica) {
        JsonResponse response = new JsonResponse();
        try {
            if (citaMedica.getId() == null) {
                response.setMessage("Cita Medica Guardado");
            } else {
                response.setMessage("Cita Medica Actualizado");
            }
            service.save(citaMedica);
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
        List<CitaMedica> citas = service.allDynatable(filter);
        ArrayNode array = new ArrayNode(JsonNodeFactory.instance);
        citas.forEach((cita) -> {
            ObjectNode node = JsonHelper.createJson(cita, JsonNodeFactory.instance,
                    new String[]{"*", "medico.*"});
            array.add(node);
        });
        json.setData(array);
        json.setTotal(filter.getTotal());
        json.setFiltered(filter.getFiltered());
        return json;
    }

    @ResponseBody
    @RequestMapping("all")
    public JsonResponse all() {
        JsonResponse response = new JsonResponse();
        JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = new ArrayNode(jsonFactory);
        try {
            List<CitaMedica> pasientes = service.all();
            for (CitaMedica pasiente : pasientes) {
                ObjectNode node = JsonHelper.createJson(pasiente, jsonFactory, new String[]{"*", "medico.*", "usuario.*"});
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
    @RequestMapping("delete")
    public JsonResponse delete(@RequestBody CitaMedica citaMedica) {
        JsonResponse response = new JsonResponse();
        try {
            service.delete(citaMedica);
            response.setMessage("Cita Medica Eliminado");
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }
}
