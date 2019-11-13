package com.xiaotian.demo.springboot.transactional.resolver;

import com.xiaotian.demo.springboot.transactional.person.Person;
import com.xiaotian.demo.springboot.transactional.person.PersonService;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
public class PersonParameterHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final PersonService personService;

    public PersonParameterHandlerMethodArgumentResolver(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if(parameter.getParameterType().equals(Person.class)) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        String personId = webRequest.getParameter("personId");
        if (null == personId || "".equals(personId)) {
            return Person.NULL;
        }
        Optional<Person> personOptional = personService.findByPersonId(Long.valueOf(personId));
        return personOptional.orElse(Person.NULL);
    }
}
