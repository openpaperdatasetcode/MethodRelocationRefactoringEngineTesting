package source;
import target.TargetEnum;import java.util.List;
public enum SourceEnum {INSTANCE;
public static class StaticNestedSource {public static final int STATIC_CONSTANT = 100;}
public class MemberInner {private int varargsMethod(TargetEnum... targets) {class InnerHandler {private void tryProcess(TargetEnum target) {try {if (target.superField != 1) {throw new IllegalArgumentException("Invalid super field");}System.out.println(target.getInner().getValue());} catch (IllegalArgumentException e) {e.printStackTrace();}}}
InnerHandler handler = new InnerHandler();int sum = 0;
for (int i = 0; i < targets.length; i++) {handler.tryProcess(targets[i]);sum += targets[i].getValue() + StaticNestedSource.STATIC_CONSTANT;}
for (TargetEnum target : targets) {TargetEnum recursiveResult = target.recursiveMethod(3);sum += recursiveResult.superField;super.toString();}
return sum;}}}
package target;
class ParentEnum {protected int superField;
public ParentEnum(int superField) {this.superField = superField;}}
enum TargetEnum extends ParentEnum {VALUE1(1), VALUE2(2);
private final int value;
TargetEnum(int value) {super(value);this.value = value;}
public int getValue() {return value;}
// Override violation: ParentEnum has no such method, but violates access consistencyint recursiveMethod(int depth) {if (depth <= 0) return this;return new TargetEnum(value).recursiveMethod(depth - 1);}
public class MemberInnerTarget {public String getValue() {return TargetEnum.this.name() + "_inner";}}
public MemberInnerTarget getInner() {return new MemberInnerTarget();}}