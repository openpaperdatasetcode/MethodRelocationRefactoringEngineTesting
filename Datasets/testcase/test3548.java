package test;
import java.io.IOException;
public class SourceClass extends ParentSource {public class MemberInner {public String getInnerData() {return "inner_data";}}
{new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class running");}}.run();}
int instanceMethod(TargetClass target) throws IOException {TargetClass.StaticNested nested = target.new StaticNested();
// Access target's instance fieldString targetField = target.data;if (targetField == null) {throw new IOException("Target field is null");}
// Variable callvariableCall: int length = nested.process(targetField);
// Call method in object initializationOtherClass other = new OtherClass(nested);int result = other.calculate(length);
return result;}}
class ParentSource {}
abstract class TargetClass {String data;
public TargetClass(String data) {this.data = data;}
public class StaticNested {public int process(String input) {return input.length();}
public int thisMethod() {return data.length() * 2;}}}
final class OtherClass {private TargetClass.StaticNested nested;
public OtherClass(TargetClass.StaticNested nested) {this.nested = nested;}
public int calculate(int base) {return base + nested.thisMethod();}}