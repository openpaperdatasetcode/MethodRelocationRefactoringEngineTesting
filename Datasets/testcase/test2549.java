package same;
private class SourceClass {private int outerField = 50;static class StaticNested {}
class MemberInner {private int innerField;
// Constructor with this(arguments)MemberInner() {this(10);}
MemberInner(int val) {innerField = val;}
protected int process(TargetClass.InnerRec inner) throws IllegalArgumentException {// VariableDeclarationStatement with 2 super fields from target's parentprivate int first = inner.superField1;private int second = inner.superField2;
int result = 0;labeledLoop: {// Access outer thisresult += SourceClass.this.outerField;
// Overloaded method callsresult += inner.calculate(first);result += inner.calculate(first, second);
if (result < 0) {throw new IllegalArgumentException("Invalid result");}break labeledLoop;}return result;}}}
package same;
private class TargetParent {protected int superField1 = 10;protected int superField2 = 20;}
private class TargetClass extends TargetParent {static class InnerRec {// Inherit super fields from TargetParentvoid display() {System.out.println(superField1 + superField2);}
// Overloaded methodsint calculate(int a) {return a * 2;}
int calculate(int a, int b) {return a + b;}}}