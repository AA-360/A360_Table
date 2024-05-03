import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.record.Record;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.basic.RowToJson;
import com.automationanywhere.botcommand.samples.commands.basic.XlsxToTable;
import com.automationanywhere.botcommand.samples.commands.utils.WorkbookHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Testes {

    @Test
    public void encodes() {
        XlsxToTable x = new XlsxToTable();
        RowToJson a = new RowToJson();

        TableValue tbl2 = x.action("C:\\Users\\melque\\Documents\\teste.xlsx","index",null,0.0,"A:H",true,false,null);


        Table tbl = tabela();

        Record rc = new Record();
        rc.setSchema(tbl.getSchema());
        rc.setValues(tbl.getRows().get(0).getValues());

        a.action("table",rc,tbl2.get());


    }



    private Table tabela(){
        //TableValue tbv = new TableValue();
        Table searchResult = new Table();
        List<Schema> header = new ArrayList<Schema>();
        List<com.automationanywhere.botcommand.data.model.table.Row> rows = new ArrayList<com.automationanywhere.botcommand.data.model.table.Row>();
        List<Value> currentRow = new ArrayList<>();
        com.automationanywhere.botcommand.data.model.table.Row rw = new com.automationanywhere.botcommand.data.model.table.Row();

        //CRIA AS COLUNAS
        header.add(new Schema("TEST"));
        header.add(new Schema("USD"));
        header.add(new Schema("BRL"));
        searchResult.setSchema(header);

        //ADCIONA A LINHA
        currentRow.add(new StringValue("OK"));
        currentRow.add(new StringValue("1,456.25"));
        currentRow.add(new StringValue("12"));
        rw.setValues(currentRow);
        rows.add(rw);

        //SEGUNDA LINHA
        currentRow = new ArrayList<>();
        rw = new com.automationanywhere.botcommand.data.model.table.Row();
        currentRow.add(new StringValue("ROW2COL1"));
        currentRow.add(new StringValue("25.42"));
        currentRow.add(new StringValue(""));
        rw.setValues(currentRow);
        rows.add(rw);

        //TERCEIRA LINHA
        currentRow = new ArrayList<>();
        rw = new Row();
        currentRow.add(new StringValue("ROW3COL1"));
        currentRow.add(new StringValue("HEHE"));
        currentRow.add(new StringValue("4.658,58"));
        rw.setValues(currentRow);
        rows.add(rw);

        searchResult.setRows(rows);
        return searchResult;
    }


    public void encode() {
        try {
            String key = "1234";
            String data = "1234";
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            alert(Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
            System.out.println(Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));

        } catch (Exception e) {
            alert(e.getMessage());
        }
    }

    private void alert(String text){
        JOptionPane.showMessageDialog(null, text, "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
    }

    //@Test
    public void testCode(){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("text/vbscript");
        String codee = code();

        try{
            engine.eval(codee);
        }
        catch (Exception e){
            throw new BotCommandException("Error when trying to load Js code!" + e.getMessage());
        }


    }

    private String code(){
        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get("C:/Temp/vbs.vb"), StandardCharsets.UTF_8);
            StringWriter writer = new StringWriter();
            String code = "";
            String line;
            while ((line = reader.readLine()) != null) {
                code += line + "\n";
            }

            return code;
        }
        catch (Exception e){
            throw new BotCommandException("Error when trying to load Js code!");
        }
    }

}
