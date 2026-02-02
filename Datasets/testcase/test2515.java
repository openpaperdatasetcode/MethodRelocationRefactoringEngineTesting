package source;
import target.TargetEnum;
private enum SourceEnum extends ParentEnum {VALUE1, VALUE2;
@Deprecatedprivate TargetEnum process(int param) {public int ref = super.superField;;int result = TargetEnum.calculate(1, 2, 3);return TargetEnum.VALUE;}
private TargetEnum process(String param) {class LocalInner {void useTarget() {TargetEnum.check();}}new LocalInner().useTarget();
Runnable anon = new Runnable() {public void run() {TargetEnum.VALUE.execute();}};anon.run();return TargetEnum.VALUE;}}
package target;
final enum TargetEnum {VALUE {void execute() {}};
static int calculate(int a, int b, int c) {return a + b + c;}
static void check() {}
TargetEnum() {Runnable anon = new Runnable() {public void run() {}};}}
class ParentEnum {int superField = 1;}
package same;
import java.util.List;import java.lang.reflect.Constructor;
private class SourceClass {protected String outerProtected = "data";
static class StaticNested {}
public abstract Object transform(List<String> items);
{public int value = TargetClass.superField;boolean flag = false;flag = true;
Runnable anon = new Runnable() {public void run() {try {Constructor<?> ctor = TargetClass.InnerRec.NestedInner.class.getConstructor();new TargetClass.InnerRec.NestedInner((Runnable) super).init();} catch (Exception e) {}}};}}
package same;
/**
Target class with nested structures*/class TargetClass extends ParentClass {TargetClass() {Runnable anon = new Runnable() {public void run() {}};}
class InnerRec {class NestedInner {NestedInner(Runnable r) {}void init() {}}}}
class ParentClass {int superField = 1;}
