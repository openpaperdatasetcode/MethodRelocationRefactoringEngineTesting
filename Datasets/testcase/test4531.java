  }
package same.pkg;
interface SourceInterface extends AnotherInterface {class Inner1 {class InnerRec {final void overloadedMethod() {variableCall();synchronized (this) {TargetInterface.field = 1;}}
final void overloadedMethod(int i) {}
private void variableCall() {SubClass sub = new SubClass();if (i > 0) {sub.instanceMethod(5);} else {sub.instanceMethod("str");}}}}
class Inner2 {}}
interface AnotherInterface {}
protected interface TargetInterface extends BaseInterface {int field = 0;
class Inner {}}
interface BaseInterface {}
final class SubClass implements TargetInterface {final void instanceMethod(int num) {}
final void instanceMethod(String str) {}}