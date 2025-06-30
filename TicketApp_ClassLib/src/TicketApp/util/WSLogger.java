package TicketApp.util;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WSLogger {
    private static final Logger logger = Logger.getLogger(WSLogger.class.getName());
    
    public static void log(Exception e, Level level) {
        logger.log(level, "# ERROR ON WS -> {0}", e.getMessage());
    }
    
}