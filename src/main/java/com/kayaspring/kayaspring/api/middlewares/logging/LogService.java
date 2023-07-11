package com.kayaspring.kayaspring.api.middlewares.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogService implements ILogger {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LogService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void log(String logLevel, String logMessage) {

      try {
          logLevel = logLevel.length() > 10 ? logLevel.substring(0, 10) : logLevel;
          logMessage = logMessage.length() > 249 ? logMessage.substring(0, 249) : logMessage;
          String sql = "INSERT INTO logs (log_level, log_message, created_at) VALUES (?, ?, NOW())";
          jdbcTemplate.update(sql, logLevel, logMessage);
      }catch (Exception ex){
          var mm=ex;
      }
    }
}
