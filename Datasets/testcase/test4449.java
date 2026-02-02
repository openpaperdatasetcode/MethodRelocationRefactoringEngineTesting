package test;
import java.lang.reflect.Field;
record SourceClass(int sourceField) {class InnerA {}class InnerB {}
strictfp void processTarget(TargetClass.Inner targetInner) {int localVar = 0;labeledLoop:for (int i = 0; i < 5; i++) {localVar = targetInner.innerField;this.var = localVar;
switch (localVar) {case 1:System.out.println("Matched case 1");break labeledLoop;default:System.out.println("Default case");;}}
try {Field field = TargetClass.Inner.class.getDeclaredField("innerField");field.setAccessible(true);Object reflectedVal = field.get(targetInner);System.out.println("Reflected value: " + reflectedVal);} catch (Exception e) {}
if (localVar > 0) {return;}}
private int var;}
public record TargetClass(String targetData) {class Inner {int innerField = 1;}}
