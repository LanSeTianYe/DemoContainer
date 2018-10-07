package com.sun.xiaotain.demo.springcloud.hystrix.command;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.sun.xiaotain.demo.springcloud.hystrix.model.Person;
import com.sun.xiaotain.demo.springcloud.hystrix.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;

@Component
public class GetOnePersonCommand extends HystrixObservableCommand<Person> {

    @Autowired
    private PersonRepository personRepository;

    public GetOnePersonCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("PersonRepository"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetOnePersonCommand")));
    }

    @Override
    protected Observable<Person> construct() {
        return Observable.unsafeCreate(subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    Person person = personRepository.getOne();
                    subscriber.onNext(person);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    @Override
    protected Observable<Person> resumeWithFallback() {
        return Observable.unsafeCreate(subscriber -> {
            subscriber.onNext(Person.EmptyPerson);
            subscriber.onCompleted();
        });
    }
}
