package kz.edu.nu.splat;

import kz.edu.nu.splat.parser.ParserTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Parser Tests")
@SelectClasses({
        ParserTest.class,
})
public class ParserTestSuite {
}
