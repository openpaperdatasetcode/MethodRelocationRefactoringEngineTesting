package same.pkg;
import java.sql.SQLException;import java.util.List;import java.util.ArrayList;
public class Source {private String outerPrivate = "private field";
class InnerOne {}class InnerTwo {}
strictfp List<String> overloadedMethod(Target targetParam) throws SQLException {// SwitchExpression with numbers=2int num = 2;String result = switch (num) {case 1 -> "one";case 2 -> "two";default -> "other";};
// SQLExceptionif (targetParam.field == null) {throw new SQLException("Field is null");}
// Variable callTarget.Inner inner = targetParam.new Inner();List<String> varCall = inner.getStrings();
// Access outer privateString privateVal = outerPrivate;
// With boundsClass<? extends CharSequence> boundedType = targetParam.field.getClass();
// Access instance methodint size = inner.size();
return varCall;}
strictfp List<String> overloadedMethod(Target.Inner innerParam) {return innerParam.getStrings();}}
class Target {String field;
class Inner {private static List<String> staticMethod(String arg) {return new ArrayList<>(List.of(arg));}
List<String> getStrings() {return new ArrayList<>();}
int size() {return getStrings().size();}
// Call method in expressionList<String> callSuperMethod() {return protected List<String> result = super.toString().staticMethod("arg");}}}