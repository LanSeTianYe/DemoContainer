package m.sun.xiaotian.demo.react.server.controller;


import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactDemoController {

    @PostMapping("/demo/")
    public Mono<String> postMethod(@RequestBody Publisher<String> params) {
        return save(params);
    }

    @GetMapping("demo")
    public Flux<String> getMethod() {
        return get();
    }

    private Mono<String> save(Publisher<String> params) {
        return null;
    }

    private Flux<String> get() {
        return null;
    }
}
