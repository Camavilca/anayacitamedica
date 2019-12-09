package com.camavilca.controllers.area;

import com.camavilca.model.Area;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.albatross.zelpers.miscelanea.ExceptionHandler;
import pe.albatross.zelpers.miscelanea.JsonHelper;
import pe.albatross.zelpers.miscelanea.JsonResponse;
import pe.albatross.zelpers.miscelanea.PhobosException;

@Controller
@RequestMapping("area")
public class AreaController {

    @Autowired
    AreaService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
        return "area/area";
    }

    @ResponseBody
    @RequestMapping("save")
    public JsonResponse save(@RequestBody Area area) {
        JsonResponse response = new JsonResponse();
        try {
            if (area.getId() == null) {
                response.setMessage("Area Medica Guardado");
            } else {
                response.setMessage("Area Medica Actualizado");
            }
            service.save(area);
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
        JsonResponse json = new JsonResponse();
        try {
            List<Area> areas = service.all();
            ArrayNode array = new ArrayNode(JsonNodeFactory.instance);
            for (Area area : areas) {
                array.add(JsonHelper.createJson(area, JsonNodeFactory.instance,
                        new String[]{
                            "*"
                        }));
            }
            json.setData(array);
            json.setSuccess(true);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, json);
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex, json);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping("delete")
    public JsonResponse delete(@RequestBody Area area) {
        JsonResponse response = new JsonResponse();
        try {
            service.delete(area);
            response.setMessage("Area Medica Eliminado");
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

}
