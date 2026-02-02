package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
public enum SourceEnum {INSTANCE;
protected int outerProtectedField = 10;protected TargetEnum targetField;
class MemberInner {void innerMethod() {}}
static {class StaticBlockInner {protected int instanceMethod(int a) {if (a == 2) {new MemberInner().innerMethod();return new TargetEnum.MemberInner().getValue();}return a;}}int val = new StaticBlockInner().instanceMethod(2);}
final List<String> normalMethod() {List<String> result = new ArrayList<>();typeDeclaration: {TargetEnum obj = targetField;if (obj.field == 1) {break typeDeclaration;}class LocalInner {}LocalInner local = new LocalInner();variableCall: {int var = outerProtectedField;result.add(String.valueOf(var));}}try {Method method = getClass().getMethod("normalMethod");method.invoke(this);} catch (Exception e) {}return result;}}
protected enum TargetEnum {VALUE;
int field = 1;
class MemberInner {int getValue() { return 5; }}}
class SubSource extends SourceEnum {public int callMethod(String... args) {int count = 0;do {Runnable runnable = this::normalMethod;runnable.run();count++;} while (count < args.length);return count;}}