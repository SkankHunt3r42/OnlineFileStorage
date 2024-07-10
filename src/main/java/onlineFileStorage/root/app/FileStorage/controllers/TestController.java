package onlineFileStorage.root.app.FileStorage.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * This class make test request without required jwt, or other security components
 *
 */


@RestController
@RequestMapping("/client/test")
public class TestController {

    @GetMapping("/greeting")
    public String greeting(){
        return "This is testing endpoint !";
    }

}
