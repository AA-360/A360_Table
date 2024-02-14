/*
 * Copyright (c) 2020 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */

/**
 *
 */
package com.automationanywhere.botcommand.samples.commands.conditionals;

import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import static com.automationanywhere.commandsdk.annotations.BotCommand.CommandType.Condition;
import static com.automationanywhere.commandsdk.model.AttributeType.*;


@BotCommand(commandType = Condition)
@CommandPkg(
        label = "ColCount",
        name = "ColCount",
        description = "[[ColCount.description]]",
        node_label = "[[ColCount.node_label]]"
)
public class ColCount {

    @ConditionTest
    public Boolean validate(
            @Idx(index = "1", type = TABLE)
            @Pkg(label = "[[ColCount.table.label]]",description = "[[ColCount.table.description]]")
            @NotEmpty
                    Table Tabela,
            @Idx(index = "2", type = SELECT, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = ">", value = ">")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "<", value = "<")),
                    @Idx.Option(index = "2.3", pkg = @Pkg(label = "<=", value = "<=")),
                    @Idx.Option(index = "2.4", pkg = @Pkg(label = ">=", value = ">=")),
                    @Idx.Option(index = "2.5", pkg = @Pkg(label = "=", value = "="))

            })
            @Pkg(label = "[[ColCount.condition.label]]",description = "[[ColCount.condition.description]]", default_value = "=", default_value_type = DataType.STRING)
            @NotEmpty
            String condition,

            @Idx(index = "3", type = NUMBER)
            @Pkg(label = "[[ColCount.numb.label]]",description = "[[ColCount.numb.description]]")
            @NotEmpty
                    Double number
    ) {
        Integer num_cols = Tabela.getSchema().size();

        if (condition.equals("=")){
            return num_cols == number.intValue();
        }
        if (condition.equals(">")){
            return num_cols > number.intValue();
        }
        if (condition.equals("<")){
            return num_cols < number.intValue();
        }
        if (condition.equals(">=")){
            return num_cols >= number.intValue();
        }
        if (condition.equals("<=")){
            return num_cols <= number.intValue();
        }
        return false;
    }

}
