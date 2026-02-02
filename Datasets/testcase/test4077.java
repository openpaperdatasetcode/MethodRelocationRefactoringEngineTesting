package test;
import java.io.IOException;
interface MyInterface {}
sealed interface PermitInterface permits SourceClass {}
final class SourceClass implements MyInterface, PermitInterface {class MemberInnerClass {record InnerRecord(int id) {@MyAnnotationdefault int varargsMethod(TargetClass.TargetInner... inners) throws IOException {@MyAnnotationint num = 1;TargetClass.TargetInner first = inners[0];first.value = 5;String str = first.toString();str.length();
Runnable anon = new Runnable() {@Overridepublic void run() {System.out.println(first.value);}};anon.run();
return num;}}}}
private class TargetClass {static class TargetInner {int value;public void varargsMethod() {}}}
@interface MyAnnotation {}