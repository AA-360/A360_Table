import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.WorkbookHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
