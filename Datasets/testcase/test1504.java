package test;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Process {}
public record Target(String value, int id) {// Target has no specific features}
record Source(String content) {static class StaticHelper {static final String STATIC_FIELD = "static_data";}
class MemberInner {Target processTarget(Target target) {return new Target(target.value() + "_processed", target.id() + 1);}}
{// Instance code block with overloading method calls (2)processOverload(StaticHelper.STATIC_FIELD);processOverload(10);}
@Processpublic final Target handle(Target target) throws IllegalArgumentException {// ConstructorInvocation with 1 target object field accessTarget newTarget = new Target(target.value(), target.id() + 10);
// Overloading methodsList<String> overloadResult1 = processOverload(content);List<String> overloadResult2 = processOverload(target.value(), target.id());
// Enhanced for statementfor (String item : overloadResult1) {System.out.println(item);}
// Switch caseswitch (target.id()) {case 0:newTarget = new Target("case0", 0);break;case 1:newTarget = new Target("case1", 1);break;default:newTarget = new Target("default", target.id());}
// Assert statementassert newTarget.id() > target.id() : "ID not incremented";
// Variable callMemberInner inner = new MemberInner();Target processed = inner.processTarget(newTarget);
// NullPointerExceptionif (processed.value() == null) {throw new NullPointerException("Processed target value is null");}
// Depends on static fieldString dataWithStatic = processed.value() + "_" + StaticHelper.STATIC_FIELD;
// Requires throwsif (processed.id() < 0) {throw new IllegalArgumentException("Invalid ID: " + processed.id());}
// Anonymous inner classRunnable validator = new Runnable() {@Overridepublic void run() {if (target.value().isEmpty()) {throw new IllegalStateException("Empty value");}}};validator.run();
return processed;}
// Overloading methods (2)public List<String> processOverload(String input) {return List.of(input);}
public List<String> processOverload(int input) {return List.of(String.valueOf(input));}
public List<String> processOverload(String s, int i) {return List.of(s + "_" + i);}}