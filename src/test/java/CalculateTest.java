import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.basic.Calculate;
import com.automationanywhere.botcommand.samples.commands.basic.ColumnToList;
import com.automationanywhere.botcommand.samples.commands.basic.JsonListToTable;
import com.automationanywhere.botcommand.samples.commands.basic.ListToHeaders;
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

public class CalculateTest {

    @Test
    public void json(){
        JsonListToTable jsn = new JsonListToTable();
        String jsonText = "[{\"toExplode\":{\"string\":\"asdasd\",\"number\":2,\"boolean\":true,\"double\":2.1,\"toExplode2\":{\"string\":\"asdasd\",\"number\":2,\"boolean\":true,\"double\":2.1}},\"hehe\": 1},{\"list\":[{\"otherlist\":[{\"id\":1},{\"id\":2}],\"hehe\":1},{\"id\":1},{\"id\":2}],\"id\":11906}]";//,\"event_id\":2331067,\"bank_id\":8,\"credit_account_id\":null,\"date_schedule\":\"2024-02-27\",\"date_transfer\":null,\"debit_account_id\":null,\"favored\":\"Guilherme Gomes Jacinto\",\"passed_value\":\"5581.50\",\"requested_value\":\"5581.50\",\"status_id\":1,\"type_account\":\"CP\",\"type_document\":\"CPF\",\"document\":\"011.344.312-95\",\"agency\":\"3430\",\"account\":\"0007783638-7\",\"authenticator\":null,\"release_management_id\":null,\"created_at\":\"2024-02-27 13:47:20\",\"updated_at\":\"2024-02-27 13:47:20\",\"schedule_status_date\":\"2024-02-27\",\"conclusion_status_date\":null,\"invalidation_status_date\":null,\"cancelation_status_date\":null,\"agency_account\":\"3430 \\/ 0007783638-7\",\"status\":{\"id\":1,\"description\":\"N\\u00e3o iniciado\"},\"bank\":{\"id\":8,\"bank_code\":\"104\",\"name\":\"CEF - Caixa Econ\\u00f4mica Federal\"},\"debit_account\":null,\"credit_account\":null,\"event\":{\"name\":\"ASTRO INDIE \\/\\/ Indoor\\/\\/ Open Bar\",\"start_date\":\"2024-03-02 22:00:00\",\"end_date\":\"2024-03-03 05:00:00\"},\"organizer_info\":{\"USER_ID\":11025858,\"FIRST_NAME\":\"Astro\",\"LAST_NAME\":\"Produ\\u00e7\\u00f5es\",\"EMAIL\":\"astroproducoespvh@gmail.com\",\"FULL_NAME\":\"11025858 - Astro Produ\\u00e7\\u00f5es\"}}]";
        jsonText = "[ { \"id\": 11943, \"event_id\": 2312313, \"bank_id\": 15, \"credit_account_id\": null, \"date_schedule\": \"2024-02-28\", \"date_transfer\": null, \"debit_account_id\": null, \"favored\": \"Salud Clínica e Educação LTDA\", \"passed_value\": \"4732.22\", \"requested_value\": \"4732.22\", \"status_id\": 1, \"type_account\": \"CC\", \"type_document\": \"CNPJ\", \"document\": \"27.943.639/0001-67\", \"agency\": \"2003\", \"account\": \"01527-0\", \"authenticator\": null, \"release_management_id\": null, \"created_at\": \"2024-02-28 15:56:28\", \"updated_at\": \"2024-02-28 15:56:28\", \"schedule_status_date\": \"2024-02-28\", \"conclusion_status_date\": null, \"invalidation_status_date\": null, \"cancelation_status_date\": null, \"agency_account\": \"2003 / 01527-0\", \"status\": { \"id\": 1, \"description\": \"Não iniciado\" }, \"bank\": { \"id\": 15, \"bank_code\": \"748\", \"name\": \"Banco Cooperativo Sicredi SA\" }, \"debit_account\": null, \"credit_account\": null, \"event\": { \"name\": \"A neurodiversidade, abordagens multidisciplinares e suas intervenções terapêuticas atuais\", \"start_date\": \"2024-05-10 08:00:00\", \"end_date\": \"2024-05-11 19:00:00\" }, \"organizer_info\": { \"USER_ID\": 3399901, \"FIRST_NAME\": \"Salud\", \"LAST_NAME\": \"Clinica e Educação LTDA\", \"EMAIL\": \"financeiro@salud.med.br\", \"FULL_NAME\": \"3399901 - Salud Clinica e Educação LTDA\" } }, { \"id\": 11944, \"event_id\": 2280581, \"bank_id\": 36, \"credit_account_id\": null, \"date_schedule\": \"2024-02-28\", \"date_transfer\": null, \"debit_account_id\": null, \"favored\": \"Marcio Phillipe Barros \", \"passed_value\": \"2760.94\", \"requested_value\": \"2760.94\", \"status_id\": 1, \"type_account\": \"CC\", \"type_document\": \"CPF\", \"document\": \"065.207.686-69\", \"agency\": \"0001\", \"account\": \"3090177-4\", \"authenticator\": null, \"release_management_id\": null, \"created_at\": \"2024-02-28 15:56:46\", \"updated_at\": \"2024-02-28 15:56:46\", \"schedule_status_date\": \"2024-02-28\", \"conclusion_status_date\": null, \"invalidation_status_date\": null, \"cancelation_status_date\": null, \"agency_account\": \"0001 / 3090177-4\", \"status\": { \"id\": 1, \"description\": \"Não iniciado\" }, \"bank\": { \"id\": 36, \"bank_code\": \"077\", \"name\": \"Banco Inter\" }, \"debit_account\": null, \"credit_account\": null, \"event\": { \"name\": \"CURSO OFICIAL INTENSIVO DE GESTÃO DE CONTRATOS 4.0 (19/02 à 29/02/24) - ACADEMIA DE CONTRATOS\", \"start_date\": \"2024-02-19 19:35:00\", \"end_date\": \"2024-02-29 21:35:00\" }, \"organizer_info\": { \"USER_ID\": 2841946, \"FIRST_NAME\": \"Márcio Phillipe\", \"LAST_NAME\": \"Barros\", \"EMAIL\": \"mphconsultoria@outlook.com\", \"FULL_NAME\": \"2841946 - Márcio Phillipe Barros\" } }, { \"id\": 11945, \"event_id\": 2342937, \"bank_id\": 22, \"credit_account_id\": null, \"date_schedule\": \"2024-02-28\", \"date_transfer\": null, \"debit_account_id\": null, \"favored\": \"Women Leadership LTDA\", \"passed_value\": \"285.07\", \"requested_value\": \"285.07\", \"status_id\": 1, \"type_account\": \"CC\", \"type_document\": \"CNPJ\", \"document\": \"33.713.695/0001-08\", \"agency\": \"0001\", \"account\": \"42932219-0\", \"authenticator\": null, \"release_management_id\": null, \"created_at\": \"2024-02-28 15:57:05\", \"updated_at\": \"2024-02-28 15:57:05\", \"schedule_status_date\": \"2024-02-28\", \"conclusion_status_date\": null, \"invalidation_status_date\": null, \"cancelation_status_date\": null, \"agency_account\": \"0001 / 42932219-0\", \"status\": { \"id\": 1, \"description\": \"Não iniciado\" }, \"bank\": { \"id\": 22, \"bank_code\": \"260\", \"name\": \"Nubank\" }, \"debit_account\": null, \"credit_account\": null, \"event\": { \"name\": \"Elas Lideram com Protagonismo PRESENCIAL - São Paulo\", \"start_date\": \"2024-03-19 19:00:00\", \"end_date\": \"2024-03-19 22:00:00\" }, \"organizer_info\": { \"USER_ID\": 8353436, \"FIRST_NAME\": \"Women \", \"LAST_NAME\": \"Leadership\", \"EMAIL\": \"contato@womenleadership.com.br\", \"FULL_NAME\": \"8353436 - Women Leadership\" } } ]";
        TableValue tb = jsn.action("C:\\Users\\melque\\Documents\\json.txt",true);


    }


    public void test(){
        Table tb = this.tabela();
        RowCount a = new RowCount();
        ColCount b = new ColCount();

        boolean v = b.validate(tb,"=",3.0);

        System.out.println(v);

    }


    public void listToHeaders(){
        ListToHeaders a = new ListToHeaders();
        Table tb = this.tabela();

        ListValue<String> returnvalue = new ListValue<String>();
        List<Value> vals = new ArrayList<Value>();

        vals.add(new StringValue("OK"));
        vals.add(new StringValue("ABC"));
        vals.add(new StringValue("DEF"));

        tb = a.action(tb,vals).get();

        uteisTest.printTable(tb,10);

    }

    //@Test
    public void teste(){
        Calculate a = new Calculate();
        Table tb = this.tabela();

        NumberValue ret = a.action(tb,"USD|@123",code());
        System.out.println("==================" + ret.get());
    }

    //@Test
    public void HasHeaders(){
        HasHeader a = new HasHeader();

        Table tb = this.tabela();
        System.out.println(a.validate(tb,"TEST",false,true));

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

    private String code(){
        try{
            BufferedReader reader = Files.newBufferedReader(Paths.get("C:/Temp/js.js"), StandardCharsets.UTF_8);
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