package com.sprint.mission.discodeit.util;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class FileLockProvider {

  private final Map<Path, ReentrantLock> locks = new ConcurrentHashMap<>();

  public void executeWithLock(Path path, Runnable task) {
    ReentrantLock lock = locks.computeIfAbsent(path, k -> new ReentrantLock());
    lock.lock();
    try {
      task.run();
    } finally {
      lock.unlock();
    }
  }

  public <T> T executeWithLock(Path path, Supplier<T> task) {
    ReentrantLock lock = locks.computeIfAbsent(path, k -> new ReentrantLock());
    lock.lock();
    try {
      return task.get();
    } finally {
      lock.unlock();
    }
  }
}