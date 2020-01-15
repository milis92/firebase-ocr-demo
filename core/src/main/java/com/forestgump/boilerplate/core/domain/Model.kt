package com.forestgump.boilerplate.core.domain

/**
 * This interface represents local data Model.
 *
 * By convention, [UseCase] can expose only valid models and *not* their [Entity] representations.
 * This means that if class wants to be used as return scheduler of the [UseCase.invoke] it needs to implement
 * this contract
 */
interface Model
