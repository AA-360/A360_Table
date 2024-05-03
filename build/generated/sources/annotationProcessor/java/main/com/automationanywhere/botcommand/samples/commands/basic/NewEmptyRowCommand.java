package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.Boolean;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Double;
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

public final class NewEmptyRowCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(NewEmptyRowCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    NewEmptyRow command = new NewEmptyRow();
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

    if(parameters.containsKey("check_at_index") && parameters.get("check_at_index") != null && parameters.get("check_at_index").get() != null) {
      convertedParameters.put("check_at_index", parameters.get("check_at_index").get());
      if(convertedParameters.get("check_at_index") !=null && !(convertedParameters.get("check_at_index") instanceof Boolean)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","check_at_index", "Boolean", parameters.get("check_at_index").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("check_at_index") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","check_at_index"));
    }
    if(convertedParameters.get("check_at_index") != null && (Boolean)convertedParameters.get("check_at_index")) {
      if(parameters.containsKey("index") && parameters.get("index") != null && parameters.get("index").get() != null) {
        convertedParameters.put("index", parameters.get("index").get());
        if(convertedParameters.get("index") !=null && !(convertedParameters.get("index") instanceof Double)) {
          throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","index", "Double", parameters.get("index").get().getClass().getSimpleName()));
        }
      }
      if(convertedParameters.get("index") == null) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","index"));
      }

    }

    try {
      command.action((Table)convertedParameters.get("Tabela"),(Boolean)convertedParameters.get("check_at_index"),(Double)convertedParameters.get("index"));Optional<Value> result = Optional.empty();
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
