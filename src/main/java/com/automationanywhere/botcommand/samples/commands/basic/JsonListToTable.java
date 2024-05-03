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
import com.automationanywhere.commandsdk.annotations.sapbapi.Entries;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.automationanywhere.botcommand.samples.commands.utils.File;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;

import static com.automationanywhere.commandsdk.model.AttributeType.*;

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
            @Idx(index = "1", type = FILE)
            @Pkg(label = "[[JsonListToTable.json.label]]",description = "[[JsonListToTable.json.description]]")
            @NotEmpty
            String file,
            @Idx(index = "2", type = CHECKBOX)
            @Pkg(label = "[[JsonListToTable.explode.label]]",description = "[[JsonListToTable.explode.description]]",default_value = "false",default_value_type = DataType.BOOLEAN)
            @NotEmpty
            Boolean explode
    ) {
        try {

            String json = readJsonFile(file);

            Map<String, Object>[] mapping = new ObjectMapper().readValue(json, HashMap[].class);
            List<String> cols = explode? uniqueColsExplode(mapping): uniqueCols(mapping);
            FindInListSchema fnd = new FindInListSchema(cols);

            List<Row> listRows= new ArrayList<>();

            for(Map<String, Object> el : mapping) {
                List<Value> rwValue = newEmptyRow(cols);
                for (String key : cols) {
                    int idx = fnd.indexSchema(key);
                    String val = getValByKey(el,key,"\\.");
                    rwValue.set(idx,new StringValue(val));
                }
                listRows.add(new Row(rwValue));
            }




//            for(Map<String, Object> el : mapping){
//                List<Value> rwValue = newEmptyRow(cols);
//                 for(String col : el.keySet()) {
//                     int idx = fnd.indexSchema(col);
//                     Object val = el.get(col);
//                     String value = val==null?"": val.toString();
//                     rwValue.set(idx,new StringValue(value));
//
//                 }
//                listRows.add(new Row(rwValue));
//
//            }

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


    private String readJsonFile(String file){
        File fl = new File();
        List<String> buffer = fl.readFile(file);

        String joined = "";
        for(String line: buffer){
            joined +=line + "\n";
        }

        joined = joined.substring(0, joined.length() - 1);
        return  joined;
    }


    private String getValByKey(Object hash,String Key,String delimiter,Integer... index){
        Integer idx = index.length>0 ? index[0]:0;
        String currentKey = Key.split(delimiter)[idx];
        Object val = null;

        if(hash.getClass() == HashMap.class){
            //k = ((HashMap)hash).keySet();
            val = ((HashMap)hash).get(currentKey);
        }
        if(hash.getClass() == LinkedHashMap.class) {
           //k = ((LinkedHashMap) hash).keySet();
           val = ((LinkedHashMap)hash).get(currentKey);
        }
        if(val==null) return "";

        if(val.getClass() == HashMap[].class || val.getClass() == LinkedHashMap.class){
            if(Key.split(delimiter).length-1 >= idx+1){
                return getValByKey(val,Key,delimiter,idx+1);
            }

        }

        String value = val==null?"": val.toString();



        return value.toString();
    }

    private List<String> uniqueColsExplode(Object hash,String... parent){
        List<String> keys = new ArrayList<>();
        String father = parent.length > 0 ? parent[0] + "." : "";
        father = father.replace("..",".");

        if(hash.getClass() == HashMap[].class){
            for(Map<String, Object> el : (HashMap[])hash) {
                for (String col : el.keySet()) {
                    Object val = el.get(col);
//                    if(val.getClass() == ArrayList.class){
//
//                    }
                    if(val!=null &&(val.getClass() == HashMap[].class || val.getClass() == LinkedHashMap.class)){
                        for(String k : uniqueColsExplode(val,col)){
                            if (!keys.contains(k)) {
                                keys.add(k);
                            }
                        }
                        //keys.addAll(uniqueColsExplode(val,col));
                    } else if (!keys.contains(col)){
                        keys.add(col);
                    }
                }
            }
        }


        if(hash.getClass() == LinkedHashMap.class){
            Set<String> k = ((LinkedHashMap)hash).keySet();
            for(String col : k){
                Object val = ((LinkedHashMap)hash).get(col);
                if(val.getClass() == HashMap[].class || val.getClass() == LinkedHashMap.class){
                    for(String kk : uniqueColsExplode(val,father + col)){
                        if (!keys.contains(kk)) {
                            keys.add(kk);
                        }
                    }
                    //keys.addAll(uniqueColsExplode(val,father + col));
                } else if (!keys.contains(col)){
                    keys.add(father + col);
                }
            }
        }





        return keys;
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
