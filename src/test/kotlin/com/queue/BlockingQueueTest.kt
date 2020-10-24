package com.queue

import com.natpryce.hamkrest.absent
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BlockingQueueTest: Spek({

    describe("BlockingQueue") {

        context("enqueue") {
            it("adding an item to an empty queue is successful") {
                val queue = BlockingQueue<String>(3)
                assertThat(queue.enqueue("blah1"), equalTo(true))
                assertThat(queue.size(), equalTo(1))
            }

            it("adding an item to non-empty queue that is not full is successful") {
                val queue = BlockingQueue<String>(2)
                queue.enqueue("blah1")
                assertThat(queue.enqueue("blah2"), equalTo(true))
                assertThat(queue.size(), equalTo(2))
            }

            it("adding an item to queue that is full fails") {
                val queue = BlockingQueue<String>(2)
                queue.enqueue("blah1")
                queue.enqueue("blah2")
                assertThat(queue.enqueue("blah3"), equalTo(false))
                assertThat(queue.size(), equalTo(2))
            }
        }

        context("dequeue") {
            it("removing an item from an empty queue returns null") {
                val queue = BlockingQueue<String>(3)
                assertThat(queue.dequeue(), absent())
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