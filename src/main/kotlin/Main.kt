import io.klogging.Level
import io.klogging.NoCoLogger
import io.klogging.config.DEFAULT_CONSOLE
import io.klogging.config.loggingConfiguration
import io.klogging.java.LoggerFactory
import java.time.Instant
import java.util.concurrent.ForkJoinPool

private val logger: NoCoLogger = LoggerFactory.getLogger("Main")
fun main() {
    loggingConfiguration {
        DEFAULT_CONSOLE()
        minDirectLogLevel(Level.INFO)
    }
    logger.info("Hello Parallel World!")
    logger.info("Processors: ${Runtime.getRuntime().availableProcessors()}")
    logger.info("Common pool size: ${ForkJoinPool.commonPool().parallelism}")
    val start = Instant.now()
    (1..10).map { LongRunningTask(it) }
        .parallelStream()
        .forEach {
            it.run()
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

