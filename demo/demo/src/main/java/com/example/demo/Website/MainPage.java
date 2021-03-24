package com.example.demo.Website;

import com.example.demo.Flights.Flight;
import com.example.demo.Service.FlightsService;
import com.example.demo.Service.UserService;
import com.example.demo.User.User;
import com.example.demo.Website.Security.ConfirmationToken;
import com.example.demo.Website.Security.ConfirmationTokenRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


@Controller
public class MainPage {
    private final FlightsService flightsService;
    private final UserService userService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public MainPage(FlightsService flightsService,
                    UserService userService,
                    ConfirmationTokenRepository confirmationTokenRepository) {
        this.flightsService = flightsService;
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
        model.addAttribute("flights", flightsService.getAllFlightsByCity(params.get("searchbar")));
        return "search.html";
    }

    @GetMapping("/search.html")
    public String searchGet(Model model) {
        model.addAttribute("flights", flightsService.getAllFlights());
        return "search.html";
    }

    @GetMapping(path = "/flight/{id}")
    public String flight(@PathVariable("id") Long id, Model model) {
        Flight chosenFlight = flightsService.getFlightById(id);
        File directory = new File("src/main/resources/static/css/images/" + id);
        String[] array = directory.list();
        String joined = Arrays.toString(array);
        joined = joined.replace("[", "").replace("]", "").replace(" ", "");
        model.addAttribute("flight", chosenFlight);
        model.addAttribute("size", joined);
        return "flight.html";
    }

    @GetMapping("/flight/{id}/book")
    public String bookFlight(@PathVariable("id") Long id, Model model) {
        Flight chosenFlight = flightsService.getFlightById(id);
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
        if (path.equals("/error")) {
            redir.addFlashAttribute("message", "a");
        } else if (path.equals("/flight/" + id + "/book")) {
            redir.addFlashAttribute("error", "true");
        }
        return redirectView;
    }

    @GetMapping("/confirm-account")
    public ModelAndView confirm(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) throws InterruptedException {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userService.getUserByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userService.enableNewUser(user, token.getTokenId());
            modelAndView.setViewName("confirm.html");
            modelAndView.addObject("Message", "Confirmed your account!");
        } else {
            modelAndView.setViewName("templates/error.html");
        }
        return modelAndView;
    }

    @GetMapping("/confirm")
    public String end(Model model) {
        model.addAttribute("Message","Please confirm your e-mail");
        return "confirm.html";
    }


    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("flights", flightsService.getAllFlights());
        return "admin.html";
    }

    @PostMapping("/admin/add")
    public RedirectView add(@RequestParam Map<String, String> params,
                            @RequestParam("files") List<MultipartFile> files) throws JSONException {
        JSONObject json = new JSONObject(params);

        json.remove("files");
        Hashtable<String, String> place = new Hashtable<String, String>();
        place.put("city", json.getString("City"));
        place.put("hotel", json.getString("Hotel"));
        Flight flight = new Flight(
                place,
                json.getString("desc"),
                json.getInt("Price"),
                json.getInt("Rating")
        );

        flightsService.addNewFlight(flight);
        String path = "src/main/resources/static/css/images/" + flight.getId();
        File theDir = new File(path);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        files.forEach(file -> {
            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
                Path pathOfFile = Paths.get(path + "/" + file.getOriginalFilename());
                Files.write(pathOfFile, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return new RedirectView("/admin");
    }

    @GetMapping("/admin/delete/{id}")
    public RedirectView deleteFlight(@PathVariable("id") Long id) {
        flightsService.deleteFlight(id);
        return new RedirectView("/admin");
    }

    @GetMapping("/loginAdmin")
    public String loginAdmin() {
        return "loginAdmin.html";
    }

    @GetMapping("/admin/edit/{id}")
    public String editPage(@PathVariable("id") Long id,Model model){
        Flight flight = flightsService.getFlightById(id);
        model.addAttribute("flight",flight);
        return "edit.html";
    }
    @PostMapping("/admin/edit/{id}")
    public RedirectView editFlight(@PathVariable("id") Long id,@RequestParam Map<String,String> params, Model model){
        Flight flight = flightsService.getFlightById(id);
        flight.setDescription(params.get("desc"));
        flight.setPrice(Integer.parseInt(params.get("price")));
        flight.setRating(Integer.parseInt(params.get("rating")));
        Hashtable<String, String> a = new Hashtable<String, String>();
        a.put("city",params.get("city"));
        a.put("hotel",params.get("hotel"));
        flight.setFlights(a);
        flightsService.editFlight(flight);
        return new RedirectView("/admin");

    }
}

