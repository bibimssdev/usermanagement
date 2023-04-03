package com.springbank.user.cmd.api.commands;

import com.springbank.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder

public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;
    private User user;

}
