package test;
strictfp abstract class Source {class MemberInner {class InnerRec {strictfp int calculate(Target.InnerTarget.InnerRec targetRec) {int result = 0;
if (targetRec.value < 0) {throw new IllegalArgumentException("Negative value");}
try {result = targetRec.value * 2;if (result > 100) {result = 100;}} catch (ArithmeticException e) {result = -1;}
return result;}}}}
protected abstract class Target {class InnerTarget {class InnerRec {int value;
InnerRec(int val) {this.value = val;}}}}