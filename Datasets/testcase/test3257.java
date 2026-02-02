package test;
public record SourceRecord(String data) {class SourceMemberInner {protected String outerProtectedField = "protectedData";
void varargsMethod(TargetRecord... targetParams) {new Runnable() {@Overridepublic void run() {for (TargetRecord target : targetParams) {target.new TargetMemberInner().doAction();}}}.run();
class LocalType {}LocalType local = new LocalType();
super();this(data);
for (int i = 0; i < targetParams.length; i++) {if (i % 2 == 0) continue;
TargetRecord target = targetParams[i];int result = publicRecursionMethod(target, 3);target.new TargetMemberInner().useValue(result);}
System.out.println(outerProtectedField); // Access outer protected}
public int publicRecursionMethod(TargetRecord target, int depth) {if (depth == 0) {return target.data().length();}return target.new TargetMemberInner().process(publicRecursionMethod(target, depth - 1)); // Recursion + instanceReference.methodName(arguments)}}}
record TargetRecord(String data) {class TargetMemberInner {void doAction() {}int process(int value) {return value * 2;}void useValue(int value) {}}}