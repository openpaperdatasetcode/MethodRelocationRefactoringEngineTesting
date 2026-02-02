package test;
import java.util.List;import java.util.ArrayList;import other.OtherClass;
interface MyInterface {}
public class SourceClass implements MyInterface {static class StaticNested {}class MemberInner {}
class SourceInner {class InnerRec {protected List<String> overloadingMethod(TargetClass.TargetInner inner) {super();class TypeDecl {}
variableCall = inner.field;this.var = variableCall;
OtherClass other = new OtherClass (inner.field + 1);
int i = 0;switch (i) {case 1:inner.instanceMethod();break;default:break;}
Runnable r = () -> {TargetClass.TargetInner result = ((SubClass) inner).privateMethod ();};
return new ArrayList<>();}
protected List<String> overloadingMethod(String str) {return new ArrayList<>();}
TargetClass.TargetInner variableCall;TargetClass.TargetInner var;
@Overridepublic boolean equals (Object obj) { return false;}}}}
abstract class TargetClass {class TargetInner {int field;void instanceMethod() {}}
{new Runnable () {}; }}
class SubClass extends TargetClass.TargetInner {private TargetClass.TargetInner privateMethod() {return this;}}

package other;
import test.TargetClass;
public class OtherClass {public OtherClass(int value) {}}