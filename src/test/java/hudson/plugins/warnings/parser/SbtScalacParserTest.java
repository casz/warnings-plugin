package hudson.plugins.warnings.parser;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

import static org.junit.Assert.*;

import hudson.plugins.analysis.util.model.FileAnnotation;
import hudson.plugins.analysis.util.model.Priority;

/**
 * Tests the class {@link SbtScalacParser}.
 * Author: <a href="mailto:hochak@gmail.com">Hochak Hung</a>
 */
public class SbtScalacParserTest extends ParserTester {

    @Test
    public void basicFunctionality() throws IOException {
        Collection<FileAnnotation> warnings = new SbtScalacParser().parse(openFile());
        assertEquals(WRONG_NUMBER_OF_WARNINGS_DETECTED, 4, warnings.size());
        Iterator<FileAnnotation> iter = warnings.iterator();
        checkWarning(iter.next(), 111, "method stop in class Thread is deprecated: see corresponding Javadoc for more information.",
                "/home/user/.jenkins/jobs/job/workspace/path/SomeFile.scala", DEFAULT_CATEGORY, Priority.NORMAL);
        checkWarning(iter.next(), 9, "';' expected but identifier found.",
                "/home/user/.jenkins/jobs/job/workspace/another/path/SomeFile.scala", DEFAULT_CATEGORY, Priority.HIGH);
        checkWarning(iter.next(), 4, "implicit numeric widening",
                     "/home/user/.jenkins/jobs/job/workspace/Main.scala", DEFAULT_CATEGORY, Priority.NORMAL);
        checkWarning(iter.next(), 5, "Invalid literal number",
                     "/home/user/.jenkins/jobs/job/workspace/Main.scala", DEFAULT_CATEGORY, Priority.HIGH);
    }

    @Override
    protected String getWarningsFile() {
        return "sbtScalac.txt";
    }
}
