package kz.edu.nu.splat;

import kz.edu.nu.splat.parser.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Parser Tests")
@SelectClasses({
//        MainProgramTest.class,
//        VarDeclTest.class,
//        FuncDeclTest.class,
        ParseExprTest.class,
        ParseStmtTest.class,
})
public class ParserTestSuite {
}
