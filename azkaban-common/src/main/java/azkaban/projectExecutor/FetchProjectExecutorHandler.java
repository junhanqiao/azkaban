package azkaban.projectExecutor;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FetchProjectExecutorHandler implements
        ResultSetHandler<List<ProjectExecutor>> {
    public static final String FETCH_ALL_PROJECT_EXECUTOR = "select project_id,executor_id,description from projects_executor";

    @Override
    public List<ProjectExecutor> handle(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return Collections.emptyList();
        }

        final List<ProjectExecutor> projectExecutors = new ArrayList<>();
        do {
            final int project_id = rs.getInt(1);
            final int executor_id = rs.getInt(2);
            final String description = rs.getString(3);
            final ProjectExecutor projectExecutor = new ProjectExecutor(project_id, executor_id, description);
            projectExecutors.add(projectExecutor);
        } while (rs.next());

        return projectExecutors;
    }
}
