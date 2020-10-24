package com.queue

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class BlockingQueue<T>(private val maxSize: Int) {

    private val queue = mutableListOf<T>()

    private val lock = ReentrantLock()
    private val isFull = lock.newCondition()
    private val isEmpty = lock.newCondition()

    fun size(): Int = lock.withLock { queue.size }

    fun enqueue(item: T) = lock.withLock {
        if (queue.size == maxSize) {
            isFull.await()
        }
        queue.add(item)
        isEmpty.signal()
    }

    fun dequeue(): T = lock.withLock {
        if (queue.isEmpty()) {
            isEmpty.await()
        }
        val item = queue.removeAt(0)
        isFull.signal()
        return item
    }
}