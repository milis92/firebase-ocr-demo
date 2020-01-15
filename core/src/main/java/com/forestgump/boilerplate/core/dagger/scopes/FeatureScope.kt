package com.forestgump.boilerplate.core.dagger.scopes

import javax.inject.Scope

/**
* This dagger scope represents instances tied to feature modules, meaning that new instance of dependency
 * will be provided for each receiver
 */
@Scope
annotation class FeatureScope
