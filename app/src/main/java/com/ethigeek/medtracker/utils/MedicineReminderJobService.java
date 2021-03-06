package com.ethigeek.medtracker.utils;

import android.app.job.JobParameters;
import android.app.job.JobService;



/**
 * Class for Medicine reminder job service
 * Created by ethiraj srinivasan on 15/03/17.
 */
public class MedicineReminderJobService extends JobService {
    /**
     *
     * @param jobParameters
     * @return
     */
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        ReminderTasks.executeTask(MedicineReminderJobService.this, ReminderTasks.ACTION_MEDICINE_REMINDER);
        jobFinished(jobParameters, false);
        return true;
    }

    /**
     *
     * @param jobParameters
     * @return
     */
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
