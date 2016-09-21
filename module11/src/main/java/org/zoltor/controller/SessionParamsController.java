package org.zoltor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpSession;

/**
 * Created by zoltor on 21/09/2016.
 */
@Controller
public class SessionParamsController extends BaseController {

    @RequestMapping(value = "/lang", method = RequestMethod.POST)
    public String setLocale(@ModelAttribute("lang") String lang, HttpSession session) {
        session.setAttribute("locale", lang);
        return "redirect:/";
    }

}
