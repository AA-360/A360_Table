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

import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.samples.commands.utils.CloneClass;
import com.automationanywhere.botcommand.samples.commands.utils.FindInListSchema;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.TABLE;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "ReverseTable",
        name = "ReverseTable",
        icon = "pkg.svg",
        description = "[[ReverseTable.description]]",
        node_label = "[[ReverseTable.node_label]]",
        return_description = "[[ReverseTable.return_description]]",
        return_type = DataType.TABLE,
        return_required = true
)


public class ReverseTable {

    @Execute
    public TableValue action(
            @Idx(index = "1", type = TABLE)
            @Pkg(label = "[[ReverseTable.table.label]]",description = "[[ClearTable.table.description]]")
            @NotEmpty
                    Table Tabela
    ) {
        List<Schema> SCHEMAS = new ArrayList<>(Tabela.getSchema());
        FindInListSchema fnd = new FindInListSchema(SCHEMAS);
        Table tbl_reversed = CloneClass.cloneTable(Tabela);


        Collections.reverse(tbl_reversed.getRows());

        TableValue OUTPUT = new TableValue();
        OUTPUT.set(tbl_reversed);
        return OUTPUT;
    }

}
