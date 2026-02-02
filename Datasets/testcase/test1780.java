package test;
import java.util.List;import java.util.ArrayList;
interface Processable {Object execute();}
abstract record Target<T>(T value) {class InnerClass {static String staticField = "inner_static";
List<String> process(T data) {return List.of(data.toString());}}}
class OtherClass extends Target.InnerClass {@OverrideList<String> process(Object data) {List<String> result = new ArrayList<>();result.add(data.toString() + "_overridden");return result;}}
public record Source(String id) implements Processable {class MemberInner {U transform(U input) {
return input;
}
}
public Object process(Target.InnerClass targetInner, String... args) {super.toString(); // Implicit super constructor invocation in recordMemberInner inner = new MemberInner();
// Raw type usageTarget rawTarget = new Target(null) {};
// ThrowStatement with target class static fieldif (args.length == 0) {throw new IllegalArgumentException(Target.InnerClass.staticField + " requires args");}
// Local inner classclass LocalHandler {Object handle(String arg) {return inner.transform(arg);}}
// Conditional expressions (2 instances)String firstArg = args.length > 0 ? args[0] : "default";boolean isValid = firstArg.isEmpty() ? false : true;
Object result = null;try {// Array initialization with others_class method callObject[] processed = {OtherClass.process(firstArg),new LocalHandler().handle(firstArg)};result = processed;} catch (Exception e) {result = e.getMessage();}
return result;}
@Overridepublic Object execute() {return null;}}