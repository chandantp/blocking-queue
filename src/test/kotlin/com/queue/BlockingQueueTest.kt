package com.queue

import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.concurrent.thread

object BlockingQueueTest: Spek({

    describe("BlockingQueue") {

        context("enqueue") {
            it("adding an item to an empty queue is successful") {
                val queue = BlockingQueue<String>(3)
                queue.enqueue("blah1")
                assertThat(queue.size(), equalTo(1))
            }

            it("adding an item to non-empty queue that is not full is successful") {
                val queue = BlockingQueue<String>(2)
                queue.enqueue("blah1")
                queue.enqueue("blah2")
                assertThat(queue.size(), equalTo(2))
            }

            it("adding an item to queue that is full blocks until queue becomes not full") {
                val queue = BlockingQueue<String>(2)
                queue.enqueue("blah1")
                queue.enqueue("blah2")

                val t1 = thread {
                    println("Enqueuing 'blah3'...")
                    queue.enqueue("blah3")
                    println("Enqueuing 'blah3'...done!")
                }
                val t2 = thread {
                    Thread.sleep(2000)
                    println("Dequeuing 'blah1'")
                    assertThat(queue.dequeue(), equalTo("blah1"))
                }

                t1.join()
                t2.join()

                assertThat(queue.size(), equalTo(2))
            }
        }

        context("dequeue") {
            it("removing an item from an empty queue blocks until queue becomes not empty") {
                val queue = BlockingQueue<String>(3)

                val t1 = thread {
                    println("Dequeuing 'blah1'...")
                    assertThat(queue.dequeue(), equalTo("blah1"))
                    println("Dequeuing 'blah1'...done!")
                }

                val t2 = thread {
                    Thread.sleep(2000)
                    println("Enqueuing 'blah1'")
                    queue.enqueue("blah1")
                }

                t1.join()
                t2.join()

                assertThat(queue.size(), equalTo(0))
            }

            it("removing an item from a non-empty queue is successful") {
                val queue = BlockingQueue<String>(2)
                queue.enqueue("blah1")
                queue.enqueue("blah2")
                assertThat(queue.dequeue(), equalTo("blah1"))
                assertThat(queue.size(), equalTo(1))
            }
        }

    }
})