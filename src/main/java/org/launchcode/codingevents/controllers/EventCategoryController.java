package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("eventCategories")
@Controller
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping("index")
    // /eventCategories/index
    private String displayAllEvents(Model model){


        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }


    @GetMapping("create")
    // /eventCategories/create
    private String renderCreateEventCategoryForm(Model model){


        model.addAttribute("title", "Create Category");
        model.addAttribute("eventCategory", new EventCategory());
        return "eventCategories/create";
    }


    @PostMapping("create")
    // /eventCategories/create
    private String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newEventCategory, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:index";
    }

    @GetMapping("delete")
    // /eventCategories/delete
    private String renderDeleteEventCategoryForm(Model model){


        model.addAttribute("title", "Delete Category");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/delete";
    }


    @PostMapping("delete")
    // /eventCategories/delete
    private String processDeleteEventCategoryForm(@RequestParam(required = false) int[] categoryIds){

        if(categoryIds != null && categoryIds.length != 0){
            for(int id : categoryIds){
                eventCategoryRepository.deleteById(id);
            }
        }
        return "redirect:index";
    }
}
