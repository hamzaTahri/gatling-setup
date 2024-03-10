package computerdatabase;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://computer-database.gatling.io")
    .inferHtmlResources()
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.9,ar;q=0.8,fr;q=0.7,fr-FR;q=0.6")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0");
  
  private Map<CharSequence, String> headers_0 = Map.ofEntries(
    Map.entry("Accept-Encoding", "gzip, deflate"),
    Map.entry("Cache-Control", "no-cache"),
    Map.entry("Pragma", "no-cache"),
    Map.entry("Proxy-Connection", "keep-alive"),
    Map.entry("Sec-Mesh-Client-Arch", "x86_64"),
    Map.entry("Sec-Mesh-Client-Edge-Channel", "stable"),
    Map.entry("Sec-Mesh-Client-Edge-Version", "122.0.2365.80"),
    Map.entry("Sec-Mesh-Client-OS", "Windows"),
    Map.entry("Sec-Mesh-Client-OS-Version", "10.0.22631"),
    Map.entry("Sec-Mesh-Client-WebView", "0")
  );
  
  private Map<CharSequence, String> headers_1 = Map.ofEntries(
    Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
    Map.entry("Cache-Control", "max-age=0"),
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-site"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Microsoft Edge\";v=\"122\""),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "\"Windows\"")
  );
  
  private Map<CharSequence, String> headers_2 = Map.ofEntries(
    Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Microsoft Edge\";v=\"122\""),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "\"Windows\"")
  );
  
  private Map<CharSequence, String> headers_4 = Map.ofEntries(
    Map.entry("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
    Map.entry("Cache-Control", "max-age=0"),
    Map.entry("Origin", "https://computer-database.gatling.io"),
    Map.entry("Sec-Fetch-Dest", "document"),
    Map.entry("Sec-Fetch-Mode", "navigate"),
    Map.entry("Sec-Fetch-Site", "same-origin"),
    Map.entry("Sec-Fetch-User", "?1"),
    Map.entry("Upgrade-Insecure-Requests", "1"),
    Map.entry("sec-ch-ua", "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Microsoft Edge\";v=\"122\""),
    Map.entry("sec-ch-ua-mobile", "?0"),
    Map.entry("sec-ch-ua-platform", "\"Windows\"")
  );
  
  private String uri1 = "http://edge-http.microsoft.com/captiveportal/generate_204";

  private ScenarioBuilder scn = scenario("RecordedSimulation")
    .exec(
      http("request_0")
        .get(uri1)
        .headers(headers_0),
      pause(3),
      http("request_1")
        .get("/computers")
        .headers(headers_1),
      pause(7),
      http("request_2")
        .get("/computers?f=mac")
        .headers(headers_2),
      pause(1),
      http("request_3")
        .get("/computers/new")
        .headers(headers_2),
      pause(27),
      http("request_4")
        .post("/computers")
        .headers(headers_4)
        .formParam("name", "xps13")
        .formParam("introduced", "1999-12-12")
        .formParam("discontinued", "2022-12-12")
        .formParam("company", "10")
    );

  {
	  setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
  }
}
