/*
 * Copyright 2014 LinkedIn Corp.
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

import java.util.Objects;

/**
 * Class to represent an AzkabanExecutorServer details for ExecutorManager
 *
 * @author gaggarwa
 */
public class ProjectExecutor{

  private int project_id;
  private String host;
  private String description;

  public ProjectExecutor(int project_id, String host, String description) {
    this.project_id = project_id;
    this.host = host;
    this.description = description;
  }

  public int getProject_id() {
    return project_id;
  }

  public void setProject_id(int project_id) {
    this.project_id = project_id;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProjectExecutor that = (ProjectExecutor) o;
    return project_id == that.project_id &&
            host.equals(that.host);
  }

  @Override
  public int hashCode() {
    return Objects.hash(project_id, host);
  }

  @Override
  public String toString() {
    return "ProjectExecutor{" +
            "project_id=" + project_id +
            ", host='" + host + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
