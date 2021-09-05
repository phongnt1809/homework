package com.techcombank.homework.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcombank.homework.exception.InvalidPoolId;
import com.techcombank.homework.model.ACTION;
import com.techcombank.homework.model.CalculatedQuantileResult;
import com.techcombank.homework.repository.PoolRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PoolServiceImpl implements PoolService{
  final static String persistenceFile = "persistence";
  final PoolRepository repository;
  final boolean enablePersistence;

  public PoolServiceImpl(
      @Value("${persistence.enable:false}") boolean enablePersistence,
      PoolRepository repository
      ) {
    log.info("persistence.enable={}", enablePersistence);
    this.enablePersistence = enablePersistence;
    if (enablePersistence) {
      try {
        File file = new File(persistenceFile);
        if (!file.exists()) {
          file.createNewFile();
        } else {
          //load persistence data
          ObjectMapper objectMapper = new ObjectMapper();
          Scanner scanner = new Scanner(file);
          while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            PoolCommand cmd = objectMapper.readValue(data, PoolCommand.class);
            log.info(data);
            try {
              if (cmd.cmd == POOL_COMMAND.ADD) {
                repository.addToPool(cmd.id, cmd.values);
              }
            } catch (Exception e) {
              //ignore invalid command
            }

          }
          scanner.close();

        }

      } catch (IOException e) {
        log.error("Init persistence failed", e);
      }
    }
    this.repository = repository;
  }

  @Override
  public ACTION addToPool(Long poolId, ArrayList<Long> values) {
    if (enablePersistence) {
      appendToPersistenceFile(poolId, values);
    }
    return repository.addToPool(poolId, values);
  }

  @Override
  public CalculatedQuantileResult query(Long poolId, float percentile) throws InvalidPoolId {
    return repository.query(poolId, percentile);
  }


  void appendToPersistenceFile(Long poolId, ArrayList<Long> values) {
    ObjectMapper objectMapper = new ObjectMapper();
    PoolCommand cmd = new PoolCommand(System.currentTimeMillis(), poolId, POOL_COMMAND.ADD, values);

    try {
      FileWriter fileWriter = new FileWriter(persistenceFile, true);
      String cmdAsStr = objectMapper.writeValueAsString(cmd);
      fileWriter.write(cmdAsStr);
      fileWriter.write(System.lineSeparator());
      fileWriter.close();
    } catch (IOException e) {
      log.error("", e);
    }
  }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class PoolCommand {
  long timestamp;
  long id;
  POOL_COMMAND cmd;
  ArrayList<Long> values;
}

enum POOL_COMMAND {
  ADD
}