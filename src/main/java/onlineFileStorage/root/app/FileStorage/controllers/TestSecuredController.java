package onlineFileStorage.root.app.FileStorage.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class have one method "greeting",this endpoint required to be authenticated by jwt,or other security components
 */

@RestController
@RequestMapping("/client/secured/test")
@SecurityRequirement(name = "bearerAuth")
public class TestSecuredController {

    @GetMapping("greeting")
    public String SecuredGreeting(){
        return "Hello, you are the special one, if you see this message ;)";
    }
}
