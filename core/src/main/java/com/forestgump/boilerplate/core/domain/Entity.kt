package com.forestgump.boilerplate.core.domain

/**
 * This interface is representation of single unit of data exposed by the domain (Domain Token).
 *
 * By standard and out of convenience, implementations *shouldn't* rely on Domain Entities but rather
 * on its Model representations due to frequent changes in Domain Entities
 *
 */
interface Entity<out T : Model> {

    /**
     * Maps the entity to its model representation
     */
    fun toModel(): T
}
