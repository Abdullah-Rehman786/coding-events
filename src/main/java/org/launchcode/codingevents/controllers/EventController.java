package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String displayAllEvents(Model model) {

        model.addAttribute("events", eventRepository.findAll());

        return "events/index";
    }

    // /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute("event",new Event());
        model.addAttribute("eventTypes", EventType.values());
        return "events/create";
    }


    // /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }

    // /events/delete
    @GetMapping("delete")
    public String renderDeleteEventForm(Model model){
        model.addAttribute("title","Delete Event");
        model.addAttribute("events", eventRepository.findAll());

        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null && eventIds.length != 0){
            for(int id : eventIds){
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

}
