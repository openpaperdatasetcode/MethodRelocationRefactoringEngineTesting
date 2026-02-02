package test;
sealed class SourceClass permits SubSource {public void varargsMethod(TargetClass.TargetInner... inners) {if (inners.length == 0) return;
class LocalInner1 {void useInner(TargetClass.TargetInner inner) {System.out.println(inner.value);}}
class LocalInner2 {void callSuper() {super.toString();}}
new LocalInner1().useInner(inners[0]);new LocalInner2().callSuper();var innerVar = inners[0];innerVar.print();}
public void varargsMethod(String... args) {System.out.println(args.length);}}
class SubSource extends SourceClass {}
abstract class TargetClass implements MyInterface {static class TargetInner {String value;
void print() {System.out.println(value);}}
final String callMethod() {return "test";}}
interface MyInterface {}
class Usage {void initArray() {TargetClass.TargetInner[] array = {new TargetClass.TargetInner(),new TargetClass.TargetInner()};String result = TargetClass.TargetInner.methodName(array);}}
class TargetClass.TargetInner {static String methodName(TargetClass.TargetInner[] arr) {return arr.length + "";}}
