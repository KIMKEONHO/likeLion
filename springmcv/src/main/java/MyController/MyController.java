package MyController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hi")
    public String hi(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hi";
    }
}
