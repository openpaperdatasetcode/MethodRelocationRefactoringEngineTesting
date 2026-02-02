package test;
import java.io.IOException;
private enum SourceEnum {ENUM_VALUE;
public record SourceInnerRec() {final int normalMethod(TargetEnum.TargetInner targetInner) throws IOException {// Type declaration statementTargetEnum.TargetInner inner = targetInner;
// ConstructorInvocation with private modifier using 3 target fieldsprivate class PrivateConstructor {PrivateConstructor() {int sum = inner.this.field1 + inner.this.field2 + inner.this.field3;System.out.println("Sum of target fields: " + sum);}}new PrivateConstructor();
// Expression statementint value = inner.getFieldSum();
// Super keywordSystem.out.println(super.getClass().getSimpleName());
// Variable call (depends on inner class)value += inner.field1 * 2;
return value;}}
// First member inner classpublic class FirstMemberInner {void useInnerRec(TargetEnum.TargetInner inner) throws IOException {new SourceInnerRec().normalMethod(inner);}}
// Second member inner classpublic class SecondMemberInner {TargetEnum.TargetInner createTargetInner() {return TargetEnum.TARGET_ENUM.new TargetInner(1, 2, 3);}}}
protected enum TargetEnum {TARGET_ENUM;
public class TargetInner {public int field1;public int field2;public int field3;
public TargetInner(int f1, int f2, int f3) {this.field1 = f1;this.field2 = f2;this.field3 = f3;}
public int getFieldSum() {return field1 + field2 + field3;}}}