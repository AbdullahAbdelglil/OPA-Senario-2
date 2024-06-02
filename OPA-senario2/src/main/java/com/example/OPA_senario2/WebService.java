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
    
    private User currentUser;

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

    @GetMapping("/login")
    public String login(@RequestParam("email") String email) {
        currentUser = userService.login(email);
        return "login";
    }
    
    @GetMapping("/general")
    public String getGeneralPage(Model model) {
        if(currentUser==null) {
            return "loginFirst";
        }

        if(!opaService.isAllowed(currentUser.getRole(), "/general")) {
            return unauthorizedResponse(model, currentUser);
        }
        String message = "Hello in general page, " + currentUser.getUsername();
        model.addAttribute("message", message);
        model.addAttribute("privileges", currentUser.getPrivileges().toArray());
        return "general";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        if(currentUser==null) {
            return "loginFirst";
        }

        if(!opaService.isAllowed(currentUser.getRole(), "/admin")) {
            return unauthorizedResponse(model, currentUser);
        }
        String message = "Hello in admin page, "+currentUser.getUsername();
        model.addAttribute("message",message);
        model.addAttribute("privileges", currentUser.getPrivileges());
        return "admin";
    }

    @GetMapping("/user")
    public String getUserPage(Model model) {
        if(currentUser==null) {
            return "loginFirst";
        }

        if(!opaService.isAllowed(currentUser.getRole(), "/user")) {
            return unauthorizedResponse(model, currentUser);
        }
        String message = "Hello in user page, "+currentUser.getUsername();
        model.addAttribute("message",message);
        model.addAttribute("privileges", currentUser.getPrivileges());
        return "user";
    }

    @GetMapping("/accountant")
    public String getAccountantPage(Model model) {
        if(currentUser==null) {
            return "loginFirst";
        }

        if(!opaService.isAllowed(currentUser.getRole(), "/accountant")) {
            return unauthorizedResponse(model, currentUser);
        }
        String message = "Hello in accountant page, "+currentUser.getUsername();
        model.addAttribute("message",message);
        model.addAttribute("privileges", currentUser.getPrivileges());
        return "accountant";
    }


}
