package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {

        model.addAttribute("events", EventData.getAll());

        return "events/index";
    }

    // /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }


    // /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute Event newEvent){
        EventData.add(newEvent);

        return "redirect:";
    }

    // /events/delete
    @GetMapping("delete")
    public String renderDeleteEventForm(Model model){
        model.addAttribute("title","Delete Event");
        model.addAttribute("events", EventData.getAll());

        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null && eventIds.length != 0){
            for(int id : eventIds){
                EventData.remove(id);
            }
        }

        return "redirect:";
    }

}
