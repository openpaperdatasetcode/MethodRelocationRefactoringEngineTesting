package same.pkg;
import java.util.ArrayList;import java.util.List;
// Source: abstract normal class with member/anonymous inner classesabstract class SourceClass {// Member inner class (source_feature)class SourceMemberInner {final void finalMethod() {}}
// Anonymous inner class (source_feature){Runnable anon = new Runnable() {@Overridepublic void run() {}};anon.run();}
// Recursive method: private modifier, returns void, contains target parameter (per_condition)private void recursiveMethod(TargetClass<Integer>.TargetInner.TargetInnerRec targetInnerRecParam, int depth) {if (depth <= 0) return;
variableCall(targetInnerRecParam);
// Constructor invocation (target's inner_rec class)TargetClass<Integer>.TargetInner.TargetInnerRec newRec = targetInnerRecParam.new TargetInnerRec(depth);
// Type declaration statementTypeDecl typeDecl = new TypeDecl();
// Raw type usageTargetClass rawTarget = new TargetClass();
// Loop with continue statementfor (int i = 0; i < depth; i++) {if (i % 2 == 0) continue;targetInnerRecParam.addItem("Item_" + i);}
// Recursive callrecursiveMethod(targetInnerRecParam, depth - 1);}
private void variableCall(TargetClass<Integer>.TargetInner.TargetInnerRec param) {int localVar = param.getRecDepth();}
// override_violation: trying to override final methodclass ChildInner extends SourceMemberInner {@Override // Compile error: final method cannot be overriddenvoid finalMethod() {}}
class TypeDecl {}}
// Target: public normal class with type parameter and member inner class (target_feature)public class TargetClass<T> {// Target's member inner classpublic class TargetInner {// Target's inner_rec class (for target_inner_rec)public class TargetInnerRec {private int recDepth;private List<String> items = new ArrayList<>();
public TargetInnerRec(int depth) {this.recDepth = depth;}
public int getRecDepth() {return recDepth;}
public void addItem(String item) {items.add(item);}}}}