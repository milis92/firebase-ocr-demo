package com.forestgump.boilerplate.core.domain

import java.net.UnknownHostException
import okhttp3.OkHttpClient

/**
 * General Failure
 * TODO See if we can remote this and use classes extending throwables directly
 */
open class Failure(val throwable: Throwable? = null)

/**
 * Represents the connection failure
 * [OkHttpClient] will throw [UnknownHostException] in this situation
 */
object NetworkFailure : Failure(null)

/**
 * Represents the server side errors
 *
 * @param statusCode - returns the HTTP status code of the error
 * @param error - textual representation of the error
 */
class ServerFailure(val statusCode: Int, val error: String?) : Failure()

/**
 * Extend from this class to create feature specific errors that should be handled by
 * underlying implementations
 */
abstract class FeatureFailure() : Failure()
