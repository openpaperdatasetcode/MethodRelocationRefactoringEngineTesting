package test.same;
import java.util.List;
public class SourceClass<T extends Number> implements Runnable {class FirstMemberInner {}class SecondMemberInner {}
protected int instanceMethod(TargetClass target) {class TypeDecl {int process(TargetClass.LocalInner inner) {return inner.field;}}TypeDecl typeDecl = new TypeDecl();
Object var = TargetClass.staticField;new Object() {{super();}};
int sum = 0;for (TargetClass.LocalInner inner : target.getLocals()) {sum += typeDecl.process(inner);if (inner.field > 0) {sum += inner::getCount;} else {sum += inner.getCount();}}
return sum;}
public void run() {}}
/**
Javadoc for TargetClass
Contains local inner class with field and method*/private class TargetClass {static int staticField = 1;
List<LocalInner> getLocals() {class LocalInner {int field;
int getCount() {return field * 2;}}return List.of(new LocalInner(), new LocalInner());}}