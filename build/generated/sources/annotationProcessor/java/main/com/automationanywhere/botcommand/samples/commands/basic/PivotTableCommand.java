package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PivotTableCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(PivotTableCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    PivotTable command = new PivotTable();
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

    if(parameters.containsKey("pivot_columns") && parameters.get("pivot_columns") != null && parameters.get("pivot_columns").get() != null) {
      convertedParameters.put("pivot_columns", parameters.get("pivot_columns").get());
      if(convertedParameters.get("pivot_columns") !=null && !(convertedParameters.get("pivot_columns") instanceof List)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","pivot_columns", "List", parameters.get("pivot_columns").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("pivot_columns") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","pivot_columns"));
    }

    if(parameters.containsKey("agg_column") && parameters.get("agg_column") != null && parameters.get("agg_column").get() != null) {
      convertedParameters.put("agg_column", parameters.get("agg_column").get());
      if(convertedParameters.get("agg_column") !=null && !(convertedParameters.get("agg_column") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","agg_column", "String", parameters.get("agg_column").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("agg_column") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","agg_column"));
    }

    if(parameters.containsKey("calc_mode") && parameters.get("calc_mode") != null && parameters.get("calc_mode").get() != null) {
      convertedParameters.put("calc_mode", parameters.get("calc_mode").get());
      if(convertedParameters.get("calc_mode") !=null && !(convertedParameters.get("calc_mode") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","calc_mode", "String", parameters.get("calc_mode").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("calc_mode") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","calc_mode"));
    }
    if(convertedParameters.get("calc_mode") != null) {
      switch((String)convertedParameters.get("calc_mode")) {
        case "count" : {

        } break;
        case "sum" : {

        } break;
        default : throw new BotCommandException(MESSAGES_GENERIC.getString("generic.InvalidOption","calc_mode"));
      }
    }

    if(parameters.containsKey("calc_column") && parameters.get("calc_column") != null && parameters.get("calc_column").get() != null) {
      convertedParameters.put("calc_column", parameters.get("calc_column").get());
      if(convertedParameters.get("calc_column") !=null && !(convertedParameters.get("calc_column") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","calc_column", "String", parameters.get("calc_column").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("calc_column") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","calc_column"));
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((Table)convertedParameters.get("Tabela"),(List<Value>)convertedParameters.get("pivot_columns"),(String)convertedParameters.get("agg_column"),(String)convertedParameters.get("calc_mode"),(String)convertedParameters.get("calc_column")));
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
