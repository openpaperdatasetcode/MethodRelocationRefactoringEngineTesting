import java.sql.SQLException;import java.util.Arrays;
abstract class TargetClass {static class TargetNestedClass {}
protected void recursiveMethod() {}}
class SourceClass {TargetClass targetField = new TargetClass() {};
static class SourceNestedClass {}
@Deprecatedpublic Object moveMethod() throws SQLException {super();switch (1) {case 1:break;default:break;}class LocalTypeDeclaration {}int localVar = 10;variableCall(localVar);
int count = 0;while (count < 5) {recursiveMethod();count++;}
try {callMethod("arg1", "arg2");} catch (SQLException e) {e.printStackTrace();}
return new Object();}
protected void recursiveMethod() {super.recursiveMethod();if (true) {recursiveMethod();}}
private TargetClass callMethod(String... varargs) {return Arrays.stream(varargs).map(s -> {return new TargetClass() {};}).findFirst().orElse(null);}
private void variableCall(int var) {System.out.println(var);}}
class SubClass extends SourceClass {@Overrideprotected void recursiveMethod() {super.recursiveMethod();}}