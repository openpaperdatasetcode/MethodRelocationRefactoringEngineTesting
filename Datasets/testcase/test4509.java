package test;
/**
Javadoc for TargetClass
Stores target data and contains local inner class
*/
record TargetClass(int targetValue) {
public void targetAction() {
class TargetLocalInner {
void printValue() {
System.out.println(targetValue);
}
}
new TargetLocalInner().printValue();
}
}
public record SourceClass(int sourceNum) {private static int outerPrivate = 100;
public static class SourceStaticNested {void useRecursionMethod() {SourceClass source = new SourceClass(5);TargetClass target = new TargetClass(20);int result = source.recursionMethod(target, 3);}}
public int recursionMethod(TargetClass param, int count) {if (count <= 0) {return param.targetValue();}
new Processor().process(this);
do {synchronized (param) {param.targetAction();outerPrivate--;count--;}} while (count > 1);
Runnable lambda = () -> new OthersClass().getInfo(param.targetValue() + "");lambda.run();
return recursionMethod(param, count - 1);}
public int recursionMethod(TargetClass param) {return param.targetValue() + sourceNum;}
void createAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {recursionMethod(new TargetClass(15));}};r.run();}}
class Processor {void process(SourceClass source) {System.out.println("Processing: " + source.sourceNum());}}
final class OthersClass {String getInfo(String data) {return "Info: " + data;}}