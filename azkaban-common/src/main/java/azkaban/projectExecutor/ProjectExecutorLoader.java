package azkaban.projectExecutor;

import azkaban.ServiceProvider;
import azkaban.executor.ExecutorManagerException;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ProjectExecutorLoader {
    public static ProjectExecutorDao projectExecutorDao;
    public static Map<Integer, Set<String>> cachedProjectExecutors;
    public static int cacheRefreshInterval = 60 * 1000;
    public static AtomicLong lastRefreshTimestamp;

    public static Map<Integer, Set<String>> getAllProjectExecutors() throws ExecutorManagerException {
        if (projectExecutorDao == null) {
            projectExecutorDao = ServiceProvider.SERVICE_PROVIDER.getInstance(ProjectExecutorDao.class);
            refreshCache();
        } else {
            long now = System.currentTimeMillis();
            if (now - lastRefreshTimestamp.get() > cacheRefreshInterval) {
                refreshCache();
            }
        }
        return cachedProjectExecutors;

    }

    private static void refreshCache() throws ExecutorManagerException {
        HashMap<Integer, Set<String>> _cachedProjectExecutors = new HashMap<>();
        List<ProjectExecutor> projectExecutors = projectExecutorDao.fetchAllExecutors();
        for (ProjectExecutor projectExecutor : projectExecutors) {
            Set<String> hosts = _cachedProjectExecutors.get(projectExecutor.getProject_id());
            if (hosts == null) {
                hosts = new HashSet<>();
                hosts.add(projectExecutor.getHost());
                _cachedProjectExecutors.put(projectExecutor.getProject_id(), hosts);
            } else {
                hosts.add(projectExecutor.getHost());
            }
        }
        cachedProjectExecutors = _cachedProjectExecutors;
        lastRefreshTimestamp = new AtomicLong(System.currentTimeMillis());
    }
}
