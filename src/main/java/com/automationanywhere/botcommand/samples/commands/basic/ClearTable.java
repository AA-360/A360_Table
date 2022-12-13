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
package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "ClearTable",
        name = "ClearTable",
        icon = "pkg.svg",
        description = "[[ClearTable.description]]",
        node_label = "[[ClearTable.node_label]]"
)


public class ClearTable {

    @Execute
    public void action(
            @Idx(index = "1", type = TABLE)
            @Pkg(label = "[[ClearTable.table.label]]",description = "[[ClearTable.table.description]]")
            @NotEmpty
                    Table Tabela
    ) {
        List<Row> rws =  new ArrayList<>();
        Tabela.setRows(rws);
    }

}
