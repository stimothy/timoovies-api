package com.steventimothy.timoovies.repository.timoovies.sessions;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.steventimothy.timoovies.repository.config.DbConfig;
import com.steventimothy.timoovies.repository.timoovies.TimooviesDbService;
import com.steventimothy.timoovies.repository.timoovies.config.TimooviesDbConfig;
import com.steventimothy.timoovies.repository.timoovies.sessions.config.SessionsDbConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <h1>The SessionsDbService Class</h1>
 * <p>This class is responsible for talking with the sessions table in the database.</p>
 */
@Slf4j
@Component
class SessionsDbService extends TimooviesDbService {

  private SessionsDbConfig sessionsDbConfig;

  @Autowired
  public SessionsDbService(DbConfig dbConfig, MysqlDataSource mysqlDataSource, TimooviesDbConfig timooviesDbConfig, SessionsDbConfig sessionsDbConfig) {
    super(dbConfig, mysqlDataSource, timooviesDbConfig);
    this.sessionsDbConfig = sessionsDbConfig;
  }
}
