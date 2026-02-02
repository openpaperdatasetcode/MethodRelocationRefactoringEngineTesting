package source;
import target.TargetClass;import java.util.Objects;
protected record SourceClass(String data) {static class SourceStaticNested {}
{new Runnable() {}; // Anonymous inner class}
@Overrideprotected int hashCode() {TargetClass.TargetInner.TargetInnerRec innerRec = new TargetClass("target").new TargetInner().new TargetInnerRec();
// NullPointerExceptionObjects.requireNonNull(innerRec.value, "Value cannot be null");
// Assert statementassert innerRec.count > 0 : "Count must be positive";
// Constructor invocationTargetClass target = new TargetClass(innerRec.value);
// Switch statementswitch (innerRec.count) {case 1:innerRec.value = "one";break;default:innerRec.value = "default";}
// Try statementtry {int parsed = Integer.parseInt(innerRec.value);} catch (NumberFormatException e) {// No new exception thrown}
// Variable callString var = innerRec.value;int countVar = innerRec.count;
return var.hashCode() + countVar;}}
package target;
protected record TargetClass(String value) {class TargetInner {class TargetInnerRec {String value;int count = 1;
void localInnerMethod() {class LocalInner {} // Local inner class}}}
public TargetClass(String value) {this.value = TargetClass.process(value); // Call method in constructor parameter list}
public static int process(String str) {return str.length();}}