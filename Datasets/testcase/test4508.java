package test;
protected record TargetClass(int targetField) {public class TargetInner {public TargetClass getTargetInstance(int field) {return new TargetClass(field);}}private TargetClass() {this(0);}private TargetClass(int field1, int field2) {this(field1 + field2);}}
public sealed record SourceClass(String sourceData) permits SourceSubClass {public final Object instanceMethod(TargetClass param) {TargetClass.TargetInner targetInner = param.new TargetInner();TargetClass[] targetArray = {targetInner.getTargetInstance(10),targetInner.getTargetInstance(20)};
int switchVar = param.targetField();switch (switchVar) {case 10:return targetArray[0];case 20:return targetArray[1];default:return new Object();}}
public final Object instanceMethod(String strParam) {return strParam;}
public static class SourceStaticNested {void invokeInstanceMethod() {try {SourceClass source = new SourceSubClass("test");TargetClass target = new TargetClass(15);Object result = source.instanceMethod(target);} catch (Exception e) {}}}
public class SourceMemberInner {void useOuterMethod() {TargetClass target = new TargetClass(5);Object result = SourceClass.this.instanceMethod(target);}}}
final record SourceSubClass(String sourceData) extends SourceClass {public SourceSubClass(String sourceData) {super(sourceData);}}
