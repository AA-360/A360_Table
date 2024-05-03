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
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.record.Record;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.GreaterThanEqualTo;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;
import com.google.gson.Gson;
//import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "RowToJson",
        name = "RowToJson",
        icon = "pkg.svg",
        description = "[[RowToJson.description]]",
        node_label = "[[RowToJson.node_label]]",
        return_description = "[[RowToJson.return_description]]",
        return_type = DataType.STRING,
        return_required = true
)


public class RowToJson {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = SELECT, options = {
                    @Idx.Option(index = "1.1", pkg = @Pkg(label = "Record", value = "record")),
                    @Idx.Option(index = "1.2", pkg = @Pkg(label = "Table", value = "table"))})
            @Pkg(label = "[[RowToJson.type.label]]", description = "[[RowToJson.type.description]]", default_value = "record", default_value_type = DataType.STRING)
            @NotEmpty
                    String type,
            @Idx(index = "1.1.1", type = RECORD)
            @Pkg(label = "[[RowToJson.record.label]]",description = "[[RowToJson.record.description]]")
            @NotEmpty
                    Record record,
            @Idx(index = "1.2.1", type = TABLE)
            @Pkg(label = "[[RowToJson.table.label]]",description = "[[RowToJson.table.description]]")
            @NotEmpty
                    Table Tabela
    ) {
        try{
            if(type.equals("record")){
                List<Schema> schemas = record.getSchema();
                List<Value>  colunas = record.getValues();
                Map<String, String> data = new HashMap<String, String>();

                for( int i = 0; i < schemas.size(); i++){
                    data.put(schemas.get(i).getName(), colunas.get(i).toString());
                }

                String value = new Gson().toJson(data);
                return new StringValue(value);



            }else {
                List<Schema> schemas = Tabela.getSchema();
                List<Map<String, String>> myList = new ArrayList<Map<String, String>>();

                for (Row linha : Tabela.getRows()) {
                    Map<String, String> data = new HashMap<String, String>();
                    List<Value> colunas = linha.getValues();


                    for (int i = 0; i < schemas.size(); i++) {
                        data.put(schemas.get(i).getName(), colunas.get(i).toString());
                    }

                    myList.add(data);
                }

                String value = new Gson().toJson(myList);

                return new StringValue(value);

            }

        } catch (Exception e) {
            throw new BotCommandException("Error:" + e.toString());
        }
    }
}
