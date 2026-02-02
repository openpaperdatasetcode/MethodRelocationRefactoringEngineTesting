package samepkg;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
protected class SourceClass {private int outerPrivateField = 10;
static class SourceStaticNested {}
class MemberInner {public Object overloadedMethod() {return new Object();}
public Object overloadedMethod(int param) {return new Object();}
default String callMethod() {return overloadedMethod().toString().toLowerCase();}}
default List<String> instanceMethod(TargetClass targetParam) {super();TargetClass varCall = targetParam;int privateAccess = this.outerPrivateField;
Supplier<Object> supplier = () -> new MemberInner().overloadedMethod(1).toString().hashCode();
MemberInner inner = new MemberInner();do {String str = inner.callMethod().toUpperCase();} while (false);
return new ArrayList<>();}}
public class TargetClass implements Runnable {static class TargetStaticNested {}
@Overridepublic void run() {}}