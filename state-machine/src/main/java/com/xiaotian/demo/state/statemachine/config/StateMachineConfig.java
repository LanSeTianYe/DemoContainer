package com.xiaotian.demo.state.statemachine.config;

import com.xiaotian.demo.state.statemachine.events.States;
import com.xiaotian.demo.state.statemachine.states.Events;
import org.springframework.context.annotation.Configuration;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig  extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
        throws Exception {
        config
            .withConfiguration()
            .autoStartup(true)
            .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
        throws Exception {
        states
            .withStates()
            .initial(States.SI)
            .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
        throws Exception {
        transitions
            .withExternal()
            .source(States.SI).target(States.S1).event(Events.E1)
            .and()
            .withExternal()
            .source(States.S1).target(States.S2).event(Events.E2);
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
