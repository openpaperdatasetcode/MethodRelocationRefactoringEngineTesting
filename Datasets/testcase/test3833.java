package test.refactoring;
import java.lang.reflect.Method;import java.util.Arrays;import java.util.List;
public enum SourceEnum<T> {INSTANCE;
static class StaticNested {
U value;
StaticNested(U value) {this.value = value;}}
class MemberInner {T data;
MemberInner(T data) {this.data = data;}}
strictfp Object recursiveMethod(TargetEnum.TargetInner targetInner, int depth) {if (depth <= 0) {return targetInner.getValue();}
Object result;synchronized (targetInner) {do {targetInner.increment();} while (targetInner.getCount() < 3);
List<String> items = Arrays.asList("a", "b", "c");for (String item : items) {variableCall(targetInner, item);}}
try {Method method = TargetEnum.TargetInner.class.getMethod("getValue");result = method.invoke(targetInner);} catch (Exception e) {result = null;}
return recursiveMethod(targetInner, depth - 1);}
private void variableCall(TargetEnum.TargetInner inner, String item) {inner.addItem(item);}}
enum TargetEnum {VALUE1, VALUE2;
static class TargetInner {private int count;private Object value;private List<String> items;
TargetInner(Object value) {this.value = value;this.items = new java.util.ArrayList<>();}
void increment() {count++;}
int getCount() {return count;}
Object getValue() {return value;}
void addItem(String item) {items.add(item);}}}