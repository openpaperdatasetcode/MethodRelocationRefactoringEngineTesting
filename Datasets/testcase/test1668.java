package source;
import target.TargetClass;import java.util.List;
class SourceClass<T> implements Runnable permits SubSourceClass {private TargetClass targetField;
class MemberInner {final int instanceMethod() {static synchronized (this) {String field1 = this.targetField.field1;int field2 = this.targetField.field2;}
while (true) {overloadedMethod();overloadedMethod(1);overloadedMethod("str");new SourceClass<>().variableCall();break;}
variableCall();return 0;}
private synchronized void overloadedMethod() {}private synchronized void overloadedMethod(int i) {}private synchronized void overloadedMethod(String s) {}}
{new Runnable() {};}
void variableCall() {}
@Overridepublic void run() {}}
sealed class SubSourceClass extends SourceClass<String> permits {}
package target;
class TargetClass {String field1;int field2;}