package com.udemy.event.driven.cqrs.webapp.intro.command.interceptor;

import com.udemy.event.driven.cqrs.webapp.intro.command.CreateProductCommand;
import com.udemy.event.driven.cqrs.webapp.intro.core.data.ProductLookupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
@Component
@AllArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

  private final ProductLookupRepository repository;

  @Override
  public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
    return (index, command) -> {
      log.info("Intercepted command {}", command.getPayload());
      if (CreateProductCommand.class.equals(command.getPayloadType())) {
        var createProductCommand = (CreateProductCommand) command.getPayload();
        var entity = repository.findByProductIdOrTitle(createProductCommand.getProductId(),
                createProductCommand.getTitle());
        //
        if (entity != null) {
          throw new IllegalStateException(String.format("Product %s %s already exists", entity.getProductId(),
                  entity.getTitle()));

        }
      }
      return command;
    };
  }
}
