package org.zoltor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zoltor.config.BeanName;
import org.zoltor.config.ViewName;
import org.zoltor.pojo.Human;
import org.zoltor.pojo.Place;
import org.zoltor.pojo.Seance;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zoltor on 18/09/16.
 */
@Controller
@SuppressWarnings("unchecked")
public class SeancesController extends BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getSeances(Model model) {
        List<Seance> seanceList = (List) CTX.getBean(BeanName.SEANCES);
        model.addAttribute("seanceList", seanceList);
        return ViewName.SEANCES;
    }

    @RequestMapping(value = "/seance/{id}", method = RequestMethod.GET)
    public String getSeanceInfo(@PathVariable("id") int id, Model model) {
        List<Seance> seanceList = (List) CTX.getBean(BeanName.SEANCES);
        Seance seance = seanceList.get(id);
        model.addAttribute("seance", seance);
        model.addAttribute("seanceId", id);
        return ViewName.SEANCE_INFO;
    }

    @RequestMapping(value = "/seance/{id}/book/{rowNum}/{seatNum}", method = RequestMethod.GET)
    public String getBookingForm(@PathVariable("id") int id,
                                 @PathVariable("rowNum") int rowNum,
                                 @PathVariable("seatNum") int seatNum,
                                 Model model) {
        List<Seance> seanceList = (List) CTX.getBean(BeanName.SEANCES);
        Place place = seanceList.get(id).getPlaces().get(rowNum).get(seatNum - 1);
        if (!place.isFree()) {
            throw new RuntimeException("The place with row No " + rowNum + " and seat No " + seatNum +
                    " is busy by " + place.getVisitor().getFirstAndLastName());
        }
        model.addAttribute("seanceId", id);
        model.addAttribute("rowNum", rowNum);
        model.addAttribute("seatNum", seatNum);
        return ViewName.BOOKING_FORM;
    }

    @RequestMapping(value = "/seance/{id}/book/{rowNum}/{seatNum}/confirm", method = RequestMethod.POST)
    public String bookThePlaceAndOpenHome(@PathVariable("id") int id,
                               @PathVariable("rowNum") int rowNum,
                               @PathVariable("seatNum") int seatNum,
                               @ModelAttribute("visitor") Human visitor/*,
                               RedirectAttributes ra*/) {
        List<Seance> seanceList = (List) CTX.getBean(BeanName.SEANCES);
        Place place = seanceList.get(id).getPlaces().get(rowNum).get(seatNum - 1);
        if (!place.isFree()) {
            throw new RuntimeException("The place with row No " + rowNum + " and seat No " + seatNum +
                    " is busy by " + place.getVisitor().getFirstAndLastName());
        }
        place.setVisitor(visitor);
        return "redirect:/seance/" + id;
    }

    @ExceptionHandler(Exception.class)
    public String onException(Exception e) {
        e.printStackTrace();
        return ViewName.ERROR;
    }
}
