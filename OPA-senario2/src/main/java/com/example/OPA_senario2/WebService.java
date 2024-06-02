package com.example.OPA_senario2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/web")
public class WebService {

    private final UserService userService;
    private final OpaService opaService;

    public WebService(UserService userService, OpaService opaService) {
        this.userService = userService;
        this.opaService = opaService;
    }
    private String unauthorizedResponse(Model model, User user) {
        String message = "Sorry, " + user.getUsername()+", you are not allowed to access this page";
        model.addAttribute("message", message);
        model.addAttribute("privileges", user.getPrivileges().toArray());

        return "unauthorized";
    }

    @GetMapping("/general")
    public String getGeneralPage(@RequestParam("id") int id, Model model) {
        User user = userService.findUserByID(id);
        if(!opaService.isAllowed(user.getRole(), "/general")) {
            return unauthorizedResponse(model, user);
        }
        String message = "Hello in general page, " + user.getUsername();
        model.addAttribute("message", message);
        model.addAttribute("privileges", user.getPrivileges().toArray());
        return "general";
    }

    @GetMapping("/admin")
    public String getAdminPage(@RequestParam("id") int id, Model model) {
        User user = userService.findUserByID(id);
        if(!opaService.isAllowed(user.getRole(), "/admin")) {
            return unauthorizedResponse(model, user);
        }
        String message = "Hello in admin page, "+user.getUsername();
        model.addAttribute("message",message);
        model.addAttribute("privileges", user.getPrivileges());
        return "admin";
    }

    @GetMapping("/user")
    public String getUserPage(@RequestParam("id") int id, Model model) {
        User user = userService.findUserByID(id);
        if(!opaService.isAllowed(user.getRole(), "/user")) {
            return unauthorizedResponse(model, user);
        }
        String message = "Hello in user page, "+user.getUsername();
        model.addAttribute("message",message);
        model.addAttribute("privileges", user.getPrivileges());
        return "user";
    }

    @GetMapping("/accountant")
    public String getAccountantPage(@RequestParam("id") int id, Model model) {
        User user = userService.findUserByID(id);
        if(!opaService.isAllowed(user.getRole(), "/accountant")) {
            return unauthorizedResponse(model, user);
        }
        String message = "Hello in accountant page, "+user.getUsername();
        model.addAttribute("message",message);
        model.addAttribute("privileges", user.getPrivileges());
        return "accountant";
    }
}
