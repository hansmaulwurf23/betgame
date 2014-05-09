package util.log4j;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Color-coded console appender for Log4J.
 */
public class ANSIConsoleAppender extends ConsoleAppender implements
		IColorScheme
{
	/**
	 * Wraps the ANSI control characters around the output from the super-class
	 * Appender.
	 */
	protected void subAppend(LoggingEvent event)
	{
		this.qw.write(getColour(event.getLevel()));
		super.subAppend(event);
		this.qw.write(END_COLOR);

		if (this.immediateFlush)
		{
			this.qw.flush();
		}
	}

	/**
	 * Get the appropriate control characters to change the colour for the
	 * specified logging level.
	 */
	private String getColour(Level level)
	{
		switch (level.toInt())
		{
		case Priority.FATAL_INT:
			return FATAL_COLOR;
		case Priority.ERROR_INT:
			return ERROR_COLOR;
		case Priority.WARN_INT:
			return WARN_COLOR;
		case Priority.INFO_INT:
			return INFO_COLOR;
		case Priority.DEBUG_INT:
			return DEBUG_COLOR;
		default:
			return TRACE_COLOR;
		}
	}
}