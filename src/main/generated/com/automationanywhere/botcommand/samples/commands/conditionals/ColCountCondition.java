package com.automationanywhere.botcommand.samples.commands.conditionals;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.Condition;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Double;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ColCountCondition implements Condition {
  private static final Logger logger = LogManager.getLogger(ColCountCondition.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  public boolean test(Map<String, Value> parameters) {
    return test(null, parameters, null);
  }

  public boolean test(GlobalSessionContext globalSessionContext, Map<String, Value> parameters,
      Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    ColCount command = new ColCount();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("Tabela") && parameters.get("Tabela") != null && parameters.get("Tabela").get() != null) {
      convertedParameters.put("Tabela", parameters.get("Tabela").get());
      if(convertedParameters.get("Tabela") !=null && !(convertedParameters.get("Tabela") instanceof Table)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Tabela", "Table", parameters.get("Tabela").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("Tabela") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Tabela"));
    }

    if(parameters.containsKey("condition") && parameters.get("condition") != null && parameters.get("condition").get() != null) {
      convertedParameters.put("condition", parameters.get("condition").get());
      if(convertedParameters.get("condition") !=null && !(convertedParameters.get("condition") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","condition", "String", parameters.get("condition").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("condition") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","condition"));
    }
    if(convertedParameters.get("condition") != null) {
      switch((String)convertedParameters.get("condition")) {
        case ">" : {

        } break;
        case "<" : {

        } break;
        case "<=" : {

        } break;
        case ">=" : {

        } break;
        case "=" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","condition"));
      }
    }

    if(parameters.containsKey("number") && parameters.get("number") != null && parameters.get("number").get() != null) {
      convertedParameters.put("number", parameters.get("number").get());
      if(convertedParameters.get("number") !=null && !(convertedParameters.get("number") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","number", "Double", parameters.get("number").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("number") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","number"));
    }

    try {
      boolean result = command.validate((Table)convertedParameters.get("Tabela"),(String)convertedParameters.get("condition"),(Double)convertedParameters.get("number"));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","validate"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(), e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
