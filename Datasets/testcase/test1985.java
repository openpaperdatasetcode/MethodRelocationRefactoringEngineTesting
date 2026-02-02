package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Function;
strictfp enum SourceEnum {INSTANCE;
static class StaticNested {}
class MemberInner {class InnerRec {/**
Processes target inner elements
@return List of strings
*/
public abstract List<String> process();
synchronized <T extends Number> int compute(TargetClass.Inner inner, Function<T, Integer> func) {super.toString();int sum = 0;for (int i = 0; i < 2; i++) {sum += func.apply((T) inner.value);}return sum;}
{TargetClass.Inner targetInner = new TargetClass<>("test").new Inner(10);synchronized (targetInner) {targetInner.count++;List<String> list = process();}}}}}
enum TargetClass<T> {VALUE;
private T data;
TargetClass(String s) {super();this.data = (T) s;}
class Inner {Number value;int count;
Inner(Number val) {super();this.value = val;}}
{new Runnable() {@Overridepublic void run() {}};}}
