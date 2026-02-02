package samepkg;
import java.util.ArrayList;import java.util.List;
public class SourceClass {int x = 5;
synchronized void instanceMethod() {super();TargetClass target = new TargetClass();int field = target.targetField;SourceClass.this.x = field;
class GenericWithBounds<T extends Number> {T value;}GenericWithBounds<Integer> gen = new GenericWithBounds<>();
InnerClass inner = new InnerClass();Object obj = inner;}
{Runnable r1 = new Runnable() {public void run() {}};Runnable r2 = new Runnable() {public void run() {}};}
class InnerClass {@Overridepublic Object callMethod(String param) {return param;}}
void collectionOperation() {List<String> list = new ArrayList<>();list.stream().map(p -> new InnerClass().callMethod(p)).toList();}}
protected class TargetClass {int targetField = 10;
void methodWithLocal() {class LocalInner {}new LocalInner();}}