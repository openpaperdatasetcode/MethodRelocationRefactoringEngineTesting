package source;
import target.TargetClass;
protected class SourceClass {class FirstInner {class InnerRecursive {private TargetClass methodToMove(TargetClass targetParam) {Label: {if (targetParam.superField == 0) {break Label;}targetParam.variableCall();}
Runnable r1 = new Runnable() {public void run() {}};Runnable r2 = new Runnable() {public void run() {}};
return targetParam;}}}}
package target;
class SuperTarget {protected int superField;}
protected class TargetClass extends SuperTarget {class MemberInner {}
void variableCall() {}}