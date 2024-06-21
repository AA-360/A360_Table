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
import com.automationanywhere.botcommand.data.impl.NumberValue;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.automationanywhere.commandsdk.model.AttributeType.*;
//import MaskFormatter;

//import java.Math;
//import Math;

@BotCommand
@CommandPkg(
        label = "PivotTable",
        name = "PivotTable",
        icon = "pkg.svg",
        description = "[[PivotTable.description]]",
        node_label = "[[PivotTable.node_label]]",
        return_description = "[[PivotTable.return_description]]",
        return_type = DataType.TABLE,
        return_required = true
)


public class PivotTable {

    @Execute
    public TableValue action(
            @Idx(index = "1", type = TABLE)
            @Pkg(label = "[[PivotTable.table.label]]",description = "[[PivotTable.table.description]]")
            @NotEmpty
                    Table Tabela,

            @Idx(index = "2", type = LIST )
            @Pkg(label = "[[PivotTable.list.label]]",description = "[[PivotTable.list.description]]")
            @NotEmpty
            List<Value> pivot_columns,

            @Idx(index = "3", type = TEXT)
            @Pkg(label = "[[PivotTable.agg_column.label]]",description = "[[PivotTable.agg_column.description]]")
            @NotEmpty
            String agg_column,

            @Idx(index = "4", type = SELECT, options = {
                    @Idx.Option(index = "4.1", pkg = @Pkg(label = "Count", value = "count")),
                    @Idx.Option(index = "4.2", pkg = @Pkg(label = "Sum", value = "sum"))})
            @Pkg(label = "[[PivotTable.calc_mode.label]]",description = "[[PivotTable.calc_mode.description]]", default_value = "count", default_value_type = DataType.STRING)
            @NotEmpty
            String calc_mode,

            @Idx(index = "5", type = TEXT )
            @Pkg(label = "[[PivotTable.calc_column.label]]",description = "[[PivotTable.calc_column.description]]")
            @NotEmpty
            String calc_column

    ) {
        List<Schema> SCHEMAS = new ArrayList<>(Tabela.getSchema());
        FindInListSchema fnd = new FindInListSchema(SCHEMAS);
//        Table output = CloneClass.cloneTable(Tabela);

        //============================================================ CHECKING COLUMNS

        for(Value c:pivot_columns){
            if(!fnd.exists(c.toString())){
                throw new BotCommandException("Column '" + c.toString() + "' not found!");
            }
        }
        if(!fnd.exists(agg_column)){
            throw new BotCommandException("Column '" + agg_column + "' not found!");
        }
        if(!fnd.exists(calc_column)){
            throw new BotCommandException("Column '" + calc_column + "' not found!");
        }

        //=========================================================== PIVOT TABLE
        Table tbl_pivot = new Table();
        //======================================= NEW COLUMNS
        FindInListSchema fnd2 = new FindInListSchema(new ArrayList<>());

        for(Value c:pivot_columns) fnd2.addSchema(c.toString());

        Integer agg_idx = fnd.indexSchema(agg_column);
        List<Row> rws =  Tabela.getRows();

        List<String> distinct_agg_values= new ArrayList<>();
        for(Row r:rws){
            String val = r.getValues().get(agg_idx).toString();
            if(!distinct_agg_values.contains(val)){
                distinct_agg_values.add(val);
                fnd2.addSchema(val);
            }
        }
        //======================================= GET PIVOT INDEXES
        List<Integer> pivot_indexes = new ArrayList<>();

        for(Value v: pivot_columns){
            pivot_indexes.add(fnd.indexSchema(v.toString()));
        }
        //======================================= DISTINCT PIVOT COLUMNS
        List<Row> new_rows = new ArrayList<>();
        for(Row r:rws){
            boolean ctn = this.containsValues(new_rows,pivot_indexes,r);
            if(!ctn){
                Row new_row = this.newRow(r,pivot_indexes,fnd2.schemas.size());
                new_rows.add(new_row);
            }
        }
        //======================================= ROWS CALCULATE
        Integer calc_colum_idx = fnd.indexSchema(calc_column);

         for(Row r: new_rows){
             for(String aggCol:distinct_agg_values) {
                 List<Row> values = this.getValues(Tabela, agg_idx, aggCol, pivot_indexes, r);
                 if(calc_mode.equals("count")){
                     double val = this.countValues(values,calc_colum_idx);
                     int idxAgg = fnd2.indexSchema(aggCol);
                     r.getValues().set(idxAgg,new NumberValue(val));
                 }else{
                     double val = this.sumValues(values,calc_colum_idx);
                     int idxAgg = fnd2.indexSchema(aggCol);
                     r.getValues().set(idxAgg,new NumberValue(val));
                     int a = 1;
                 }
             }
         }


//        Table tb = new Table(fnd.schemas,new_rws);
//        Table tb = new Table(new ArrayList<>(fnd.schemas),new ArrayList<>());
        tbl_pivot.setSchema(fnd2.schemas);
        tbl_pivot.setRows(new_rows);
        TableValue OUTPUT = new TableValue();
        OUTPUT.set(tbl_pivot);
        return OUTPUT;

    }


    private double countValues(List<Row> rows,int calcColumIdx){
        double val = 0.0;
        for(Row r: rows){
            if(!r.getValues().get(calcColumIdx).toString().strip().equals("")){
                val +=1;
            }
        }
        return val;
    }

    private double sumValues(List<Row> rows,int calcColumIdx){
        double val = 0.0;
        for(Row r: rows){
            val += r.getValues().get(calcColumIdx).getAsDouble();
        }
        return val;
    }

    private boolean containsValues(List<Row> row,List<Integer> pivot_indexes,Row current_row){

        for(Row r: row){
            int pos = 0;
            boolean contains = true;
            for(int idxpvt: pivot_indexes){
                String v = current_row.getValues().get(idxpvt).toString();
                String v2 = r.getValues().get(pos++).toString();
                if(!v.equals(v2)){
                    contains = false;
                    break;
                }
            }
            if(contains) return true;
        }
        return false;
    }

    private List<Row> getValues(Table tbl,Integer agg_idx,String aggValue,List<Integer> pivot_indexes,Row current_row ){
        List<Row> current_rows = tbl.getRows();


        //============= FILTER AGG
        List<Row> agg_filtered = new ArrayList<>();

        for(Row r: current_rows){
            if(r.getValues().get(agg_idx).toString().equals(aggValue)){
                agg_filtered.add(r);
            }
        }

        //============= FILTER PIVOT
        List<Row> pivot_filtered = new ArrayList<>();
        for(Row r: agg_filtered){
            boolean include = true;
            int pos = 0;
            for(Integer pidx: pivot_indexes) {
                if(!r.getValues().get(pidx).toString().equals(current_row.getValues().get(pos++).toString())){
                    include = false;
                }
            }
            if(include) pivot_filtered.add(r);
        }

        return pivot_filtered;
    }

    private Row newRow(Row current_row,List<Integer> pivot_indexes,Integer nCols){
        List<Value> RowListValues = new ArrayList<>();

        for(int i=0;i<nCols;i++){
            RowListValues.add(new StringValue(""));
        }

        int pos = 0;
        for(int i: pivot_indexes){
            RowListValues.set(pos,current_row.getValues().get(i));
            pos++;
        }


        return new Row(RowListValues);
    }

}
