package test;
public record SourceClass<T>(T data) {class SourceInner {private Object varargsMethod(TargetClass... targets) {// do statementint i = 0;do {TargetClass target = targets[i];i++;} while (i < targets.length && i < 3);
// NumberLiteral (3)private int num1 = 10;private double num2 = 3.14;private long num3 = 100L;
// OuterClass.this.xT outerData = SourceClass.this.data;
// Variable callfor (TargetClass target : targets) {String var = target.value();target.localInnerMethod();}
return outerData;}}
{new Object() {}; // Anonymous inner class}
void localInnerMethod() {class LocalInner {} // Local inner class}}
public record TargetClass(String value) {void localInnerMethod() {class TargetLocalInner {} // Local inner class}}