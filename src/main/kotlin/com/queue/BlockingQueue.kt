package com.queue

import java.util.concurrent.locks.ReentrantLock

class BlockingQueue<T>(private val maxSize: Int) {

    private val queue = mutableListOf<T>()

    private val lock = ReentrantLock()

    fun size(): Int = queue.size

    fun enqueue(item: T): Boolean = when (queue.size) {
        maxSize -> false
        else -> {
            queue.add(item)
            true
        }
    }

    fun dequeue(): T? = when(queue.size) {
        0 -> null
        else -> queue.removeAt(0)
    }
}