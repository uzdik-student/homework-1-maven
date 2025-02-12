package kz.edu.nu.splat;

import kz.edu.nu.splat.lexer.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Lexical Analysis Tests")
@SelectClasses({
        LogicalBinOpTest.class,
        ComparisonBinOpTest.class,
        ArithmeticBinOpTest.class,
        UnaryOpTest.class,
        LiteralTest.class,
        ValidSymbolTest.class,
        InvalidSymbolTest.class,
        WhitespaceTest.class
})
public class LexerTestSuite {
}
