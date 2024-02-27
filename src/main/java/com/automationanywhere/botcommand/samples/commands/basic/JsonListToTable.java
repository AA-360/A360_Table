package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.Assets;
import com.automationanywhere.botcommand.samples.commands.utils.Debugger;
import com.automationanywhere.botcommand.samples.commands.utils.FindInListSchema;
import com.automationanywhere.botcommand.samples.commands.utils.Uteis;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.CodeType;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;

import static com.automationanywhere.commandsdk.model.AttributeType.TABLE;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;

@BotCommand
@CommandPkg(
        //Unique name inside a package and label to display.
        name = "JsonListToTable",
        label = "JsonListToTable",
        icon = "pkg.svg",
        node_label = "[[JsonListToTable.node_label]]",
        description = "[[JsonListToTable.description]]",
        return_type = DataType.TABLE,
        return_required = true,
        return_description = "[[JsonListToTable.return_description]]"
)


public class JsonListToTable {

    @Execute
    public TableValue action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "[[JsonListToTable.json.label]]",description = "[[JsonListToTable.json.description]]")
            @NotEmpty
            String json

    ) {
        try {


            Map<String, Object>[] mapping = new ObjectMapper().readValue(json, HashMap[].class);
            List<String> cols = uniqueCols(mapping);
            FindInListSchema fnd = new FindInListSchema(cols);

            List<Row> listRows= new ArrayList<>();
            for(Map<String, Object> el : mapping){
                List<Value> rwValue = newEmptyRow(cols);
                 for(String col : el.keySet()) {
                     int idx = fnd.indexSchema(col);

                     String value = el.get(col).toString();
                     rwValue.set(idx,new StringValue(value));

                 }
                listRows.add(new Row(rwValue));

            }

            Table tbl = new Table();
            tbl.setSchema(fnd.schemas);
            tbl.setRows(listRows);

            TableValue OUTPUT = new TableValue();
            OUTPUT.set(tbl);
            return OUTPUT;


        }catch (JsonProcessingException e){
            throw new BotCommandException("Error:" + e.getMessage());
        }
    }

    private List<String> uniqueCols(Map<String, Object>[] hash){
        List<String> keys = new ArrayList<>();
        for(Map<String, Object> el : hash) {
            for (String col : el.keySet()) {
                if(!keys.contains(col)){
                    keys.add(col);
                }
            }
        }
        return keys;
    }

    private List<Value> newEmptyRow(List<String> cols){
        List<Value> rwValue = new ArrayList<>();
        for(String c: cols){
            rwValue.add(new StringValue());
        }

        return rwValue;
    }


}
