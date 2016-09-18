package org.zoltor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zoltor.config.BeanName;
import org.zoltor.config.ViewName;
import org.zoltor.pojo.Seance;

import java.util.List;

/**
 * Created by zoltor on 18/09/16.
 */
@Controller
@SuppressWarnings("unchecked")
public class SeancesController extends BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSeances(ModelMap modelMap) {
        List<Seance> seanceList = (List) CTX.getBean(BeanName.SEANCES);
        modelMap.addAttribute(seanceList);
        return ViewName.SEANCES;
    }

    @RequestMapping(value = "/seance/{}", method = RequestMethod.GET)
    public String getSeanceInfo(ModelMap modelMap) {
        List<Seance> seanceList = (List) CTX.getBean(BeanName.SEANCES);
        modelMap.addAttribute(seanceList);
        return ViewName.SEANCES;
    }

}
