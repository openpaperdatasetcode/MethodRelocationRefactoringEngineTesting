package source;
import target.TargetClass;
abstract class SourceClass {public static class StaticNestedSource {public static TargetClass createTargetInstance(int value) {return new TargetClass(value);}}
public class InnerSource {public TargetClass nestedInstanceMethod(int param) {try {TargetClass target = StaticNestedSource.createTargetInstance(param);
if (param > 0) {target = normalMethod(target);} else {target = new TargetClass(-param);}
OtherClass other = new OtherClass();TargetClass processedTarget = other.instanceMethod(target);
return target;} catch (IllegalArgumentException e) {return new TargetClass(0);}}
TargetClass normalMethod(TargetClass t) {t.setValue(t.getValue() * 2);return t;}}}
package target;
abstract class TargetClass {private int targetField;
public static class StaticNestedTarget {public static void validateValue(int value) {if (value < 0) throw new IllegalArgumentException("Value cannot be negative");}}
public TargetClass(int value) {StaticNestedTarget.validateValue(value);this.targetField = value;}
public int getValue() {return targetField;}
public void setValue(int value) {this.targetField = value;}}
package source;
import target.TargetClass;
class OtherClass {public TargetClass instanceMethod(TargetClass target) {target.setValue(target.getValue() + 10);return target;}}