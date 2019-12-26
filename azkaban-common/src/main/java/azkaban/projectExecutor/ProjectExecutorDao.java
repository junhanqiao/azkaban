/*
 * Copyright 2017 LinkedIn Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package azkaban.projectExecutor;

import azkaban.db.DatabaseOperator;
import azkaban.executor.ExecutorManagerException;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ProjectExecutorDao {

  private static final Logger logger = Logger.getLogger(ProjectExecutorDao.class);
  private final DatabaseOperator dbOperator;

  @Inject
  public ProjectExecutorDao(final DatabaseOperator dbOperator) {
    this.dbOperator = dbOperator;
  }

  List<ProjectExecutor> fetchAllExecutors() throws ExecutorManagerException {
    try {
      return this.dbOperator
          .query(FetchProjectExecutorHandler.FETCH_ALL_PROJECT_EXECUTOR, new FetchProjectExecutorHandler());
    } catch (final Exception e) {
      throw new ExecutorManagerException("Error fetching executors", e);
    }
  }

}
