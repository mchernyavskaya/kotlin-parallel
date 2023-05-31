import io.klogging.Level
import io.klogging.NoCoLogger
import io.klogging.config.DEFAULT_CONSOLE
import io.klogging.config.loggingConfiguration
import io.klogging.java.LoggerFactory
import java.time.Instant

private val logger: NoCoLogger = LoggerFactory.getLogger("Main")
fun main() {
    loggingConfiguration {
        DEFAULT_CONSOLE()
        minDirectLogLevel(Level.INFO)
    }
    val start = Instant.now()
    logger.info("Hello Parallel World!")
    logger.info("Processors: ${Runtime.getRuntime().availableProcessors()}")
    val tasks = (1..10).map { LongRunningTask(it) }
        .map { Thread { it.run() } }
    logger.info("Starting all tasks")
    tasks.forEach {
        it.start()
    }
    tasks.forEach {
        it.join()
    }
    val duration = Instant.now().toEpochMilli() - start.toEpochMilli()
    logger.info("Finished all tasks in $duration ms")
}

// class that runs a long-running task
class LongRunningTask(private val id: Int) {
    private val logger: NoCoLogger = LoggerFactory.getLogger("task-${id}")
    fun run() {
        logger.info("Starting long-running task $id")
        Thread.sleep(1000)
        logger.info("Finished long-running task $id")
    }
}

