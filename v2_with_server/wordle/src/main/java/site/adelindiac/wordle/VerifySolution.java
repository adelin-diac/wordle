package site.adelindiac.wordle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class VerifySolution {
  public static class MyRequestBody {
    private String field;

    // Getter
    public String getField() {
      return this.field;
    }

    @Override
    public String toString() {
      return "MyRequestBody{" +
          "field='" + field + "\'" +
          "}";
    }
  }

  @PostMapping("/api/verify-solution")
  public ResponseEntity<String> verifySolution(@RequestBody MyRequestBody requestBody) {
    System.out.println("");
    System.out.println(requestBody);
    System.out.println(requestBody.getField());
    System.out.println("");
    String ret = requestBody.getField() + "-verify";
    // Method implementation
    return ResponseEntity.ok(ret);
  }
}