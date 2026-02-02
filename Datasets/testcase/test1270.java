package test.refactoring.movemethod;
import test.other.OtherPackageClass;
protected sealed enum SourceEnum implements Processable {INSTANCE1, INSTANCE2;
@Overridepublic void process() {}
strictfp int calculate(TargetEnum targetParam, int value) {OtherPackageClass other = new OtherPackageClass();int result = 0;MemberInner memberInner = new MemberInner();
switch (targetParam) {case SUB_INSTANCE1:result = memberInner.compute(value) + OtherPackageClass.STATIC_FIELD;break;case SUB_INSTANCE2:result = other.getBaseValue() + targetParam.getInner().getValue();break;default:result = value;}
int count = 0;while (count < 1) {result += superMethodCall();count++;}
new SourceConstructorInvocation();return result;}
private int superMethodCall() {return 10;}
static class StaticNestedSource {static int nestedStaticField = 5;}
class MemberInner {int compute(int num) {return num * StaticNestedSource.nestedStaticField;}}
static class SourceConstructorInvocation {}}
final enum TargetEnum extends BaseEnum {SUB_INSTANCE1, SUB_INSTANCE2;
private TargetInner inner;
TargetEnum() {this.inner = new TargetInner();}
TargetInner getInner() {return inner;}
class TargetInner {int getValue() {return 20;}}}
abstract class BaseEnum {}
interface Processable {void process();}
package test.other;
public class OtherPackageClass {public static final int STATIC_FIELD = 15;
public int getBaseValue() {return 30;}}
package test.refactoring.movemethod;
public class MoveMethodTest5170 {public static void main(String[] args) {SourceEnum source = SourceEnum.INSTANCE1;TargetEnum target = TargetEnum.SUB_INSTANCE1;int result = source.calculate(target, 5);System.out.println(result);}}