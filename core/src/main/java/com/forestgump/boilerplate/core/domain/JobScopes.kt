package com.forestgump.boilerplate.core.domain

/**
 * Application worker scopes
 *
 * Every job running inside the app should belong to some kind of scope, that's bound to this app.
 * This is required out of convenience for monitoring/cancelling pending/running jobs
 */
internal object JobScopes {

    /**
     * This is shared scope for every job worker, and by default every worker should belong to this scope.
     */
    const val APPLICATION_JOB_SCOPE = "com.forestgump.boilerplate.jobs"

    /**
     * Specific job scopes
     */
    const val SYNC_NEWS_JOB = "com.forestgump.boilerplate.jobs.sync-news"
}
