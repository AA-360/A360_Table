import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.samples.commands.basic.ClearColums;
import com.automationanywhere.botcommand.samples.commands.basic.NewColumns;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class NewColumnsTest {

    @Test
    public void test2(){
        ClearColums a = new ClearColums();
        Table tb = this.tabela();
        Table tb2 = this.tabela();

        ListValue<String> returnvalue = new ListValue<String>();
        List<Value> vals = new ArrayList<Value>();

        vals.add(new StringValue("USD"));
        vals.add(new StringValue("TEST"));

        tb2 = a.action(tb,"list",vals,"USD").get();

        uteisTest.printTable(tb,10);
        uteisTest.printTable(tb2,10);

    }
    @Test
    public void listToHeaders(){
        NewColumns a = new NewColumns();
        Table tb = this.tabela();
        Table tb2 = this.tabela();

        ListValue<String> returnvalue = new ListValue<String>();
        List<Value> vals = new ArrayList<Value>();

        vals.add(new StringValue("OK"));
        vals.add(new StringValue("ABC"));
        vals.add(new StringValue("DEF"));

        tb2 = a.action(tb,vals).get();

        uteisTest.printTable(tb,10);
        uteisTest.printTable(tb2,10);




    }



    private Table tabela(){
        //TableValue tbv = new TableValue();
        Table searchResult = new Table();
        List<Schema> header = new ArrayList<Schema>();
        List<Row> rows = new ArrayList<Row>();
        List<Value> currentRow = new ArrayList<>();
        Row rw = new Row();

        //CRIA AS COLUNAS
        header.add(new Schema("TEST"));
        header.add(new Schema("USD"));
        header.add(new Schema("BRL"));
        searchResult.setSchema(header);

        //ADCIONA A LINHA
        currentRow.add(new StringValue("ROW1COL1"));
        currentRow.add(new StringValue("1456.25"));
        currentRow.add(new StringValue("12"));
        rw.setValues(currentRow);
        rows.add(rw);

        //SEGUNDA LINHA
        currentRow = new ArrayList<>();
        rw = new Row();
        currentRow.add(new StringValue("ROW2COL1"));
        currentRow.add(new StringValue("25.40"));
        currentRow.add(new StringValue(""));
        rw.setValues(currentRow);
        rows.add(rw);

        //TERCEIRA LINHA
        currentRow = new ArrayList<>();
        rw = new Row();
        currentRow.add(new StringValue("ROW3COL1"));
        currentRow.add(new StringValue("25.40"));
        currentRow.add(new StringValue("4.658,58"));
        rw.setValues(currentRow);
        rows.add(rw);

        searchResult.setRows(rows);
        return searchResult;
    }




}