package tacos;

import java.time.Duration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, properties = "server.port=8081")
public class HomePageBrowserTest {

  private static HtmlUnitDriver browser;

  @BeforeAll
  public static void setup() {
    browser = new HtmlUnitDriver();

    browser.manage().timeouts()
          .implicitlyWait(Duration.ofSeconds(10));
  }

  @AfterAll
  public static void teardown() {
    browser.quit();
  }

  @Test
  public void testHomePage() {
    String homePage = "http://localhost:8081";
    browser.get(homePage);

    String titleText = browser.getTitle();
    Assertions.assertThat(titleText).isEqualTo("Taco Cloud");

    String h1Text = browser.findElement(By.tagName("h1")).getText();
    Assertions.assertThat(h1Text).isEqualTo("Welcome to taco cloud eiei");


    String imgSrc = browser.findElement(By.tagName("img"))
                                              .getAttribute("src");
    Assertions.assertThat(imgSrc).isEqualTo(homePage + "/images/TacoCloud.png");
  }


}
