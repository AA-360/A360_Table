import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.basic.*;
import com.automationanywhere.botcommand.samples.commands.conditionals.ColCount;
import com.automationanywhere.botcommand.samples.commands.conditionals.HasHeader;
import com.automationanywhere.botcommand.samples.commands.conditionals.RowCount;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PivotTest {

    @Test
    public void json(){
        PivotTable p = new PivotTable();
        XlsxToTable x = new XlsxToTable();

        //========================================= GET SHEET
        String file = "C:\\Users\\melque\\Documents\\Libro2.xlsx";
        String getSheetBy = "index";// "index"
        String sheetName = "Hoja1";
        Double sheetIndex = 0.0;
        Boolean RowStartCheck = false;
        Double RowStart = 1.0;
        //======================================== GET RANGE

        //======================================== OTHERS
        Boolean hasHeaders = true;
        String Columns = "A:D";

        TableValue tbs = x.action(file,getSheetBy,sheetName,sheetIndex,Columns,hasHeaders,RowStartCheck,RowStart);


        ListValue<String> returnvalue = new ListValue<String>();
        List<Value> pivot_columns = new ArrayList<Value>();


        pivot_columns.add(new StringValue("Order2"));
        pivot_columns.add(new StringValue("Order"));


        Table tb = tbs.get();
        uteisTest.printTable(tb,10);
        Table tb2  = p.action(tb,pivot_columns,"Movement","sum","Quantity").get();

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
        header.add(new Schema("TEST "));
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