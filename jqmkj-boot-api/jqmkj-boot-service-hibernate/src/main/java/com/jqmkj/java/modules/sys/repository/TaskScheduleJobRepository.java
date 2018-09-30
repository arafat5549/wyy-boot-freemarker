/**
 * Copyright &copy; 2015 <a href="http://www.bs-innotech.com/">bs-innotech</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.repository;

import com.jqmkj.java.common.persistence.repository.DataRepository;
import com.jqmkj.java.modules.sys.domain.TaskScheduleJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 任务调度管理Repository 任务调度
 *
 * @author lj
 * @version 2017-01-23
 */
public interface TaskScheduleJobRepository extends JpaRepository<TaskScheduleJob, String>, JpaSpecificationExecutor<TaskScheduleJob>, DataRepository<TaskScheduleJob, String> {


    List<TaskScheduleJob> findByStatusAndJobStatus(Integer status, String jobStatus);

    TaskScheduleJob findTopBySourceIdAndStatusNot(String soruceId, Integer flagDelete);

    List<TaskScheduleJob> findAllBySourceId(String sourceId);
}
