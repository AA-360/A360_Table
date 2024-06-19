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
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.CloneClass;
import com.automationanywhere.botcommand.samples.commands.utils.FindInListSchema;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.LIST;
import static com.automationanywhere.commandsdk.model.AttributeType.TABLE;
import com.automationanywhere.botcommand.data.model.table.Row;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "NewColumns",
        name = "NewColumns",
        icon = "pkg.svg",
        description = "[[NewColumns.description]]",
        node_label = "[[NewColumns.node_label]]",
        return_description = "[[NewColumns.return_description]]",
        return_type = DataType.TABLE,
        return_required = true
)


public class NewColumns {

    @Execute
    public TableValue action(
            @Idx(index = "1", type = TABLE)
            @Pkg(label = "[[NewColumns.table.label]]",description = "[[NewColumns.table.description]]")
            @NotEmpty
                    Table Tabela,
            @Idx(index = "2", type = LIST )
            @Pkg(label = "[[NewColumns.list.label]]",description = "[[NewColumns.list.description]]")
            @NotEmpty
                    List<Value> lista
    ) {
        //============================================================ CHECKING COLUMNS
        Table output = CloneClass.cloneTable(Tabela);
        List<Schema> SCHEMAS = new ArrayList<>(output.getSchema());

        for(Value hrd: lista){
            SCHEMAS.add(new Schema(hrd.toString()));
        }

        FindInListSchema fnd = new FindInListSchema(SCHEMAS);


        List<Row> rws =  output.getRows();
        List<Row> new_rws = new ArrayList<>();

        for(Row row:rws){
            List<Value> RowListValues = new ArrayList<>();
            for(Value vl:row.getValues()){
                RowListValues.add(vl);
            }
            for(Value hrd: lista) {
                RowListValues.add(new StringValue(""));
            }
            new_rws.add(new Row(RowListValues));
        }

        output.setSchema(SCHEMAS);
        output.setRows(new_rws);

        TableValue OUTPUT = new TableValue();
        OUTPUT.set(output);
        return OUTPUT;
    }

}
