package com.forestgump.boilerplate.core.dagger.scopes

import javax.inject.Scope

/**
 * This dagger scope represents instances that are bound to application lifecycle,
 * meaning that dagger will provide only one instance for all receivers
 */
@Scope
annotation class ApplicationScope
