//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RefreshScope  //动态的获取Nacos上的数据
//@RestController
//public class ConfigController {
//
//    @Value("${user.name}")
//    private String name;
//
//    @Value("${user.age}")
//    private Integer age;
//
//    @GetMapping("/config") // localhost:8001/article/config
//    public String getConfig() {
//        String content = "name: " + name + "，age: " + age;
//        System.out.println(content);
//        return content;
//    }
//
//}
