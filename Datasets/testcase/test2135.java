package test;
interface TestInterface<T> {}
public enum SourceEnum<T> implements TestInterface<T> {INSTANCE;
private int outerPrivateField;static class FirstStaticNested {}static class SecondStaticNested {}
class InnerClass {public void methodToMove(TargetEnum target) {super();SourceEnum.this.toString();int privateAccess = SourceEnum.this.outerPrivateField;
TargetEnum.StaticNested nested = target.new StaticNested();
Label: {int i = 0;while (i < 3) {System.out.println(nested.super.superField1);System.out.println(nested.super.superField2);System.out.println(nested.super.superField3);i++;if (i == 2) break Label;}}
try {target.variableCall();nested.innerMethod();new SourceEnum.InnerClass().synchronizedRecursive(3);} catch (Exception e) {}}
private synchronized void synchronizedRecursive(int n) {if (n <= 0) return;SourceEnum.INSTANCE.new InnerClass().synchronizedRecursive(n - 1);}}}
class SuperEnum {int superField1;String superField2;double superField3;}
enum TargetEnum {VALUE1, VALUE2;
class StaticNested extends SuperEnum {void innerMethod() {}}
void variableCall() {}}