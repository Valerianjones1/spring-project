package com.example.springproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    @Autowired
    private GruzService service;

    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Gruz> listGruzs = service.listAll(keyword);
        model.addAttribute("listGruzs", listGruzs);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewGruzForm(Model model) {
        Gruz gruz = new Gruz();
        model.addAttribute("gruz", gruz);
        return "new_gruz";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveGruz(@ModelAttribute("gruz") Gruz gruz){
        service.save(gruz);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditGruzForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_gruz");
        Gruz gruz = service.get(id);
        mav.addObject("gruz", gruz);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteGruz(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
