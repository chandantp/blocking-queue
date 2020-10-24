# Blocking Queue
A bounded queue implementation in Kotlin.
This code has been built and tested on `JDK 1.8.0_181`.

#### Run Program using Gradle
```shell script
$ ./gradlew run
> Task :run
```

#### Build Code
```shell script
$ ./gradlew clean build
```

#### Run Tests
```shell script
$ ./gradlew test

> Task :test

com.queue.BlockingQueueTest > adding an item to an empty queue is successful PASSED

com.queue.BlockingQueueTest > adding an item to non-empty queue that is not full is successful PASSED

com.queue.BlockingQueueTest > adding an item to queue that is full blocks until queue becomes not full STANDARD_OUT
    Enqueuing 'blah3'...
    Dequeuing 'blah1'
    Enqueuing 'blah3'...done!

com.queue.BlockingQueueTest > adding an item to queue that is full blocks until queue becomes not full PASSED

com.queue.BlockingQueueTest > removing an item from an empty queue blocks until queue becomes not empty STANDARD_OUT
    Dequeuing 'blah1'...
    Enqueuing 'blah1'
    Dequeuing 'blah1'...done!

com.queue.BlockingQueueTest > removing an item from an empty queue blocks until queue becomes not empty PASSED

com.queue.BlockingQueueTest > removing an item from a non-empty queue is successful PASSED
Results: SUCCESS (5 tests, 5 passed, 0 failed, 0 skipped)

```
