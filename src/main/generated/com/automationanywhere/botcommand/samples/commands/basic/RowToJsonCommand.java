package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.record.Record;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RowToJsonCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(RowToJsonCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    RowToJson command = new RowToJson();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("type") && parameters.get("type") != null && parameters.get("type").get() != null) {
      convertedParameters.put("type", parameters.get("type").get());
      if(convertedParameters.get("type") !=null && !(convertedParameters.get("type") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","type", "String", parameters.get("type").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("type") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","type"));
    }
    if(convertedParameters.get("type") != null) {
      switch((String)convertedParameters.get("type")) {
        case "record" : {
          if(parameters.containsKey("record") && parameters.get("record") != null && parameters.get("record").get() != null) {
            convertedParameters.put("record", parameters.get("record").get());
            if(convertedParameters.get("record") !=null && !(convertedParameters.get("record") instanceof Record)) {
              throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","record", "Record", parameters.get("record").get().getClass().getSimpleName()));
            }
          }
          if(convertedParameters.get("record") == null) {
            throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","record"));
          }


        } break;
        case "table" : {
          if(parameters.containsKey("Tabela") && parameters.get("Tabela") != null && parameters.get("Tabela").get() != null) {
            convertedParameters.put("Tabela", parameters.get("Tabela").get());
            if(convertedParameters.get("Tabela") !=null && !(convertedParameters.get("Tabela") instanceof Table)) {
              throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Tabela", "Table", parameters.get("Tabela").get().getClass().getSimpleName()));
            }
          }
          if(convertedParameters.get("Tabela") == null) {
            throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Tabela"));
          }


        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","type"));
      }
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((String)convertedParameters.get("type"),(Record)convertedParameters.get("record"),(Table)convertedParameters.get("Tabela")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
