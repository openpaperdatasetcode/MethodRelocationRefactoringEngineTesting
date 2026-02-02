package test.same;
import java.util.ArrayList;import java.util.List;
strictfp class SourceClass {static class StaticNested {}
Runnable anon = new Runnable() {public void run() {}};
public static final List<String> staticMethod(TargetClass<String> target) {List<String> result = new ArrayList<>();TargetClass.StaticNested<Integer> nested = new TargetClass.StaticNested<>();
new Object() {{super();}};
int count = 0;do {Object var = target.instanceMethod(count);result.add(var.toString());nested.setValue(count);count++;} while (count < 3);
target.overloadMethod("string");target.overloadMethod(123);
return result;}}
public class TargetClass<T> {static class StaticNested {
private U value;
void setValue(U val) {this.value = val;}}
T instanceMethod(int index) {return (T) ("value" + index);}
void overloadMethod(String str) {}void overloadMethod(int num) {}}
