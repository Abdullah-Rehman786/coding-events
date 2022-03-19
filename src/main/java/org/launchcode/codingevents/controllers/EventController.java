package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("events")
public class EventController {
    private static List<String> events = new ArrayList<>();


    @GetMapping
    public String displayAllEvents(Model model) {
        Map<String, String> eventsMap = new HashMap<>();

        eventsMap.put("StrangeLoop", "Strange Loop is a multi-disciplinary conference that brings" +
                " together the developers and thinkers building tomorrow's" +
                " technology in fields such as emerging languages, alternative" +
                " databases, concurrency, distributed systems, security," +
                " and the web.");
        eventsMap.put("CodeDay St. Louis", "CodeDay is a worldwide event where student" +
                " artists, programmers, musicians, actors, and writers get" +
                " together to build apps and games.");
        eventsMap.put("Washington DC Cyber Security Conference","Washington DC Cyber Security" +
                " Conference brings high-level Cyber Security Training " +
                "discovering cutting-edge security approaches, managing risk" +
                " in the ever-changing threat of the cyber security workforce.");

        model.addAttribute("eventsMap", eventsMap);

        String strangeloop = "StrangeLoop";
        String strangeloopAdr = "1001 Weird Circle Drive";
        model.addAttribute("strangeloop", strangeloop);
        model.addAttribute("strangeloopAdr", strangeloopAdr);


        String codeDay = "CodeDay St. Louis";
        String codeDayAdr = "1234 C0D3 Square Drive";
        model.addAttribute("codeDay", codeDay);
        model.addAttribute("codeDayAdr", codeDayAdr);


        String cyber = "Washington DC Cyber Security Conference";
        String cyberAdr = "0000 Secret Triangle Drive";
        model.addAttribute("cyber", cyber);
        model.addAttribute("cyberAdr", cyberAdr);


//        Washington DC Cyber Security Conference



        return "events/index";
//        model.addAttribute("events", events);
//
//        return "events/index";
    }

    // /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }


    // /events/create
    @PostMapping("create")
        public String createEvent(@RequestParam String eventName){
            events.add(eventName);

            return "redirect:";
        }
}
