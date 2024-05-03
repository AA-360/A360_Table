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
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.FindInListSchema;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "NewEmptyRow",
        name = "NewEmptyRow",
        icon = "pkg.svg",
        description = "[[NewEmptyRow.description]]",
        node_label = "[[NewEmptyRow.node_label]]"
)


public class NewEmptyRow {

    @Execute
    public void action(
            @Idx(index = "1", type = TABLE)
            @Pkg(label = "[[NewEmptyRow.table.label]]",description = "[[NewEmptyRow.table.description]]")
            @NotEmpty
                    Table Tabela,
            @Idx(index = "2", type = CHECKBOX )
            @Pkg(label = "[[NewEmptyRow.check_at_index.label]]",description = "[[NewEmptyRow.check_at_index.description]]")
            @NotEmpty
                    Boolean check_at_index,

            @Idx(index = "2.1", type = NUMBER )
            @Pkg(label = "[[NewEmptyRow.number.label]]",description = "[[NewEmptyRow.number.description]]")
            @NotEmpty
                    Double index
    ) {
        List<Row> rws =  Tabela.getRows();
        List<Value> RowListValues = new ArrayList<>();

        for(int i=0;i<Tabela.getSchema().size();i++){
            RowListValues.add(new StringValue(""));
        }

        if(check_at_index) {
            rws.add(index.intValue(), new Row(RowListValues));
        }else{
            rws.add(new Row(RowListValues));
        }

        Tabela.setRows(rws);
    }

}
