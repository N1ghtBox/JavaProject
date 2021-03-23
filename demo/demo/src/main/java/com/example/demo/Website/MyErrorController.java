//package com.example.demo.Website;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//public class MyErrorController implements ErrorController {
//
//    @RequestMapping(value="/error")
//    public String handleError(HttpServletRequest request, Model model) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        System.out.println("a");
//        if (status != null) {
//            int statusCode = Integer.parseInt(status.toString());
//
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                model.addAttribute("mess","404");
//            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                model.addAttribute("mess","500");
//
//            }
//        }
//        return "error";
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//}
