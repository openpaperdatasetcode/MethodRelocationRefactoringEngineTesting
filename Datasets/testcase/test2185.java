package test;
public record record SourceClass extends SuperClass implements Runnable {private int outerPrivate;TargetClass targetField;
static class StaticNested {}
class Inner {class RecursiveInner {/**
Javadoc for moveMethod
*/
final int moveMethod() {
class LocalInner {}
int val = targetField.value();
val += outerPrivate;
return overloadMethod(val) + callInnerConstructor();
}
final int moveMethod(String s) {return 0;}
private int callInnerConstructor() {switch (1) {case 1:return new FinalInner(5).getVal();default:return 0;}}}}
final class FinalInner {private int num;
FinalInner(int num) {this.num = super.hashCode() + num;}
int getVal() {return num;}}
@Overridepublic void run() {}}
class SuperClass {}
private record TargetClass(int value) {}