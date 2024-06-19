package com.automationanywhere.botcommand.samples.commands.utils;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;

import java.util.ArrayList;
import java.util.List;

public class CloneClass {

    // This method calls Object's clone().
    public static Table cloneTable(Table tbl) {
//        try {
            List<Schema> SCHEMAS = new ArrayList<>(tbl.getSchema());
            FindInListSchema fnd = new FindInListSchema(SCHEMAS);

            Table tb = new Table(new ArrayList<>(fnd.schemas), new ArrayList<>());

            List<Row> rws = tbl.getRows();
            List<Row> new_rws = new ArrayList<>();

            for (Row row : rws) {
                List<Value> colList = row.getValues();
                List<Value> new_col_list = new ArrayList<>();
                for (Value vl : colList) {
                    new_col_list.add(vl);
                }
                new_rws.add(new Row(new_col_list));
            }

            tb.setRows(new_rws);

            return tb;
//        }
//        catch (InstantiationException e){
//            throw new BotCommandException(e);
//        }
//        catch(IllegalAccessException e){
//            throw new BotCommandException(e);
//        }

    }
}