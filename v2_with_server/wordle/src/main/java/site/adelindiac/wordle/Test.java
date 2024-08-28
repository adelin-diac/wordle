package site.adelindiac.wordle;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/api/test")
    public String getMessage() {
      String ret = "Hello testing";
      return ret;
    }
}