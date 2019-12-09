package com.camavilca.controllers.medico;

import com.camavilca.model.Area;
import com.camavilca.model.Medico;
import com.camavilca.model.Paciente;
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
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    MedicoService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        return "medico/medico";
    }

    @ResponseBody
    @RequestMapping("save")
    public JsonResponse save(@RequestBody Medico medico) {
        JsonResponse response = new JsonResponse();
        try {
            if (medico.getId() == null) {
                response.setMessage("Medico Guardado");
            } else {
                response.setMessage("Medico Actualizado");
            }
            service.save(medico);
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
        List<Medico> medicos = service.allDynatable(filter);
        ArrayNode array = new ArrayNode(JsonNodeFactory.instance);
        medicos.forEach((medico) -> {
            ObjectNode node = JsonHelper.createJson(medico, JsonNodeFactory.instance,
                    new String[]{"*", "area.*"});
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
            List<Medico> medicos = service.all();
            for (Medico medico : medicos) {
                ObjectNode node = JsonHelper.createJson(medico, jsonFactory, new String[]{"*"});
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
    public JsonResponse delete(@RequestBody Medico medico) {
        JsonResponse response = new JsonResponse();
        try {
            service.delete(medico);
            response.setMessage("Medico Eliminado");
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

}
