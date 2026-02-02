package test;
import java.util.List;import java.util.function.Function;
@MyAnnotationpublic enum SourceEnum implements Runnable {INSTANCE;
class MemberInner {}
{new Runnable() {@Overridepublic void run() {}};}
@Overridepublic void run() {}
public final int process(TargetEnum target) {super();Function<Integer, Number> func = num -> SubClass.super.method(num, 2);
for (int i = 0; i < 5; i++) {TargetEnum.Inner inner = target.new Inner();TargetEnum.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();recursiveInner.action();}
for (TargetEnum.Inner.RecursiveInner item : target.getItems()) {int val = item.compute(2);}
return target.value;}}
/**
Javadoc for TargetEnum*/private enum TargetEnum {VALUE;
int value;
class Inner {class RecursiveInner {int compute(int param) {return param * 2;}
void action() {}}}
List<Inner.RecursiveInner> getItems() {return List.of(new Inner().new RecursiveInner());}}
class SuperClass {int method(int a, int b) {return a + b;}}
class SubClass extends SuperClass {}
@interface MyAnnotation {}
