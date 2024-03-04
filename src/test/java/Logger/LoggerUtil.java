package Logger;

import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public static void logInfo(String message){
        logger.info(message);
    }
}
