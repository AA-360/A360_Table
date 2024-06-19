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
import com.automationanywhere.botcommand.samples.commands.utils.CloneClass;
import com.automationanywhere.botcommand.samples.commands.utils.FindInListSchema;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import java.util.ArrayList;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "ClearColums",
        name = "ClearColums",
        icon = "pkg.svg",
        description = "[[ClearColums.description]]",
        node_label = "[[ClearColums.node_label]]",
        return_description = "[[ClearColums.return_description]]",
        return_type = DataType.TABLE,
        return_required = true
)


public class ClearColums {

    @Execute
    public TableValue action(
            @Idx(index = "1", type = TABLE)
            @Pkg(label = "[[ClearColums.table.label]]",description = "[[ClearColums.table.description]]")
            @NotEmpty
                    Table Tabela,
            @Idx(index = "2", type = SELECT, options = {
                    @Idx.Option(index = "2.1", pkg = @Pkg(label = "List", value = "list")),
                    @Idx.Option(index = "2.2", pkg = @Pkg(label = "Single", value = "single"))})
            @Pkg(label = "[[ClearColums.from.label]]",description = "[[ClearColums.from.description]]", default_value = "single", default_value_type = DataType.STRING)
            @NotEmpty
            String from,

            @Idx(index = "2.1.1", type = LIST )
            @Pkg(label = "[[ClearColums.list.label]]",description = "[[ClearColums.list.description]]")
            @NotEmpty
            List<Value> list,

            @Idx(index = "2.1.2", type = TEXT)
            @Pkg(label = "[[ClearColums.single.label]]",description = "[[ClearColums.single.description]]")
            @NotEmpty
            String single

    ) {
        //============================================================ CHECKING COLUMNS
        List<Schema> SCHEMAS = new ArrayList<>(Tabela.getSchema());
        FindInListSchema fnd = new FindInListSchema(SCHEMAS);
        Table output = CloneClass.cloneTable(Tabela);

        if(from.equals("single")){
            if(!fnd.exists(single)){
                throw new BotCommandException("Column '" + single + "' not found!");
            }

            Integer idx = fnd.indexSchema(single);
            List<Row> rws =  output.getRows();

            for(Row row :rws) {
                List<Value> colList = row.getValues();
                colList.set(idx, new StringValue(""));
            }

        } else if (from.equals("list")) {
            for(Value hrd: list){
                if(!fnd.exists(hrd.toString())){
                    throw new BotCommandException("Column '" + hrd.toString() + "' not found!");
                }
            }

            for(Value col_to_clear: list){
                Integer idx = fnd.indexSchema(col_to_clear.toString());

                List<Row> rws =  output.getRows();

                for(Row row :rws) {
                    List<Value> colList = row.getValues();
                    colList.set(idx, new StringValue(""));
                }

            }

        }


//        Table tb = new Table(fnd.schemas,new_rws);
//        Table tb = new Table(new ArrayList<>(fnd.schemas),new ArrayList<>());

        TableValue OUTPUT = new TableValue();
        OUTPUT.set(output);
        return OUTPUT;

    }

}
