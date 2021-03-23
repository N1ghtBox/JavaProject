package com.example.demo.Website;

import com.example.demo.Flights.Flight;
import com.example.demo.Flights.FlightController;
import com.example.demo.Service.UserService;
import com.example.demo.User.User;
import com.example.demo.Website.Security.ConfirmationToken;
import com.example.demo.Website.Security.ConfirmationTokenRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

@Controller
public class MainPage {
    private final FlightController flightController;
    private final UserService userService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    public MainPage(FlightController flightController,
                    UserService userService,
                    ConfirmationTokenRepository confirmationTokenRepository) {
        this.flightController = flightController;
        this.userService = userService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @GetMapping("/main.html")
    public String hello(Model model) {
        return "main.html";
    }

    @PostMapping("/search.html")
    public String search(@RequestParam Map<String, String> params, Model model) {
        JSONObject json = new JSONObject(params);
        model.addAttribute("flights", flightController.searchFlights(params.get("searchbar")));
        return "search.html";
    }

    @GetMapping("/search.html")
    public String searchGet(Model model) {
        model.addAttribute("flights", flightController.getAllFlight());
        return "search.html";
    }

    @GetMapping(path = "/flight/{id}")
    public String flight(@PathVariable("id") Long id, Model model) {
        Flight chosenFlight = flightController.getById(id);
        File directory = new File("src/main/resources/static/css/images/" + id);
        String[] array = directory.list();
        String joined = Arrays.toString(array);
        joined = joined.replace("[", "").replace("]", "").replace(" ", "");
        model.addAttribute("flight", chosenFlight);
        model.addAttribute("size", joined);
        return "flight.html";
    }

    @GetMapping("/flight/{id}/book")
    public String bookFlight(@PathVariable("id") Long id,Model model){
        Flight chosenFlight = flightController.getById(id);
        model.addAttribute("flight", chosenFlight);
        return "form.html";
    }

    @GetMapping("/brazil.html")
    public String brazil() {
        return "brazil.html";
    }


    @PostMapping(path = "/flight/{id}/book", produces = {"application/json"})
    public RedirectView flight(@PathVariable("id") Long id,
                               @RequestParam Map<String, String> params,
                               RedirectAttributes redir) throws JSONException, InterruptedException {

        JSONObject json = new JSONObject(params);
        String path = userService.addNewUser(json, id);
        RedirectView redirectView = new RedirectView(path);
        if(path.equals("/error")){
            redir.addFlashAttribute("message","a");
        }else if(path.equals("/flight/"+id+"/book")){
            redir.addFlashAttribute("error","true");
        }
        return redirectView;
    }

    @GetMapping("/confirm-account")
    public ModelAndView confirm(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) throws InterruptedException {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userService.getUserByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userService.enableNewUser(user,token.getTokenId());
            modelAndView.setViewName("confirm.html");
        } else {
            modelAndView.addObject("errorMessage", "Invalid or broken token");
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }
    @GetMapping("/confirm")
    public String end(){
        return "confirm.html";
    }
    @GetMapping("/error")
    public String error(@RequestParam("message") String a){
        return "error.html";

    }
}

