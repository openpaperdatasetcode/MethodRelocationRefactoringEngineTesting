package test;
import java.util.ArrayList;import java.util.List;
abstract class SourceClass {public class SourceInner {Object varargsMethod(TargetClass.InnerClass... inners) {List<Object> results = new ArrayList<>();int sum = 0;
// Labeled statementprocessing: {// Enhanced for statementfor (TargetClass.InnerClass inner : inners) {if (inner == null) break processing;// Variable callsum += inner.value;}
// While statementint i = 0;while (i < inners.length) {// Expression statementresults.add(inners[i].value * 2);i++;}
// Static ForStatement with 2 super.field referencesclass StaticProcessor {static void process(TargetClass.InnerClass inner) {for (int j = 0; j < 2; j++) {results.add(inner.getSuperField1() + j);results.add(inner.getSuperField2() * j);}}}for (TargetClass.InnerClass inner : inners) {StaticProcessor.process(inner);}}
// Local inner class (depends on inner class)class ResultAggregator {Object aggregate() {results.add("Sum: " + sum);return results;}}
return new ResultAggregator().aggregate();}}
// Member inner classpublic class SourceMemberInner {}}
abstract class TargetClass extends TargetParent {// Static nested classpublic static class NestedStatic {}
public class InnerClass {int value;
public InnerClass(int value) {this.value = value;}
int getSuperField1() {return superField1;}
int getSuperField2() {return superField2;}}}
class TargetParent {protected int superField1 = 10;protected int superField2 = 20;}