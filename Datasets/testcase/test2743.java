package test.same;
import java.lang.reflect.Field;
private class SourceClass extends ParentClass {class InnerClass {protected TargetClass varargsMethod(TargetClass.MemberInner... inners) {TargetClass target = new TargetClass();try {for (TargetClass.MemberInner inner : inners) {int val = inner.field;if (val == 2) {Field field = TargetClass.MemberInner.class.getDeclaredField("field");field.setAccessible(true);field.set(inner, 3);}}} catch (Exception e) {}return target;}}}
public class TargetClass {class MemberInner {private int field;}}
class ParentClass {}