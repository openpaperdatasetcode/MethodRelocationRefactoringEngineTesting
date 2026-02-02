package test.refactoring;
import java.util.List;
public record SourceClass(String data) {public static class StaticNested {class InnerClass {protected void process(TargetClass target) {for (int num : target.nested().nums()) {System.out.println(num);}
for (TargetClass.NestedItem item : target.nested().items()) {TargetClass result = item.overrideMethod().m2().m3();variableCall(result);}
int superVal = superClassMethod();System.out.println(superVal);}
private void variableCall(TargetClass target) {System.out.println(target.data());}}}
void createLocalInner() {class LocalInner {void useProcess(TargetClass target) {new StaticNested.InnerClass().process(target);}}new LocalInner().useProcess(new TargetClass("test", new NestedData()));}
protected int superClassMethod() {return 0;}}
class SubSource extends SourceClass {SubSource(String data) {super(data);}
@Overrideprotected int superClassMethod() {TargetClass.NestedStatic[] arr = {(param) -> param * 2};return arr[0].compute(5);}}
public record TargetClass(String data, NestedData nested) {public static class NestedStatic {int compute(int param) {return param;}}
public record NestedData(List<NestedItem> items, int[] nums) {}
public static class NestedItem {public NestedItem m1() {return this;}
public NestedItem m2() {return this;}
public TargetClass m3() {return new TargetClass("", new NestedData(List.of(), new int[0]));}
public TargetClass overrideMethod() {return new TargetClass("override", new NestedData(List.of(), new int[0]));}}}