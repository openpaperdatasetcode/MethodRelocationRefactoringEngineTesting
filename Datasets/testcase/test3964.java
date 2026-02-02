package source;
import target.TargetClass;
private class SourceClass {private int outerField = 10;
static class SourceNested1 {}
static class SourceNested2 {private TargetClass targetField = new TargetClass();
class SourceInner {@Deprecatedpublic void recursiveMethod(int depth) {if (depth <= 0) {return;}int innerVar = SourceClass.this.outerField;targetField.targetInstanceField = innerVar + depth;targetField.createLocalInner().localMethod();recursiveMethod(depth - 1);}}}}
package target;
private class TargetClass {int targetInstanceField;
SourceInnerDep dependsOnInner = new SourceInnerDep();
class SourceInnerDep {void innerDependentMethod() {}}
SourceInnerDep createLocalInner() {class LocalInner extends SourceInnerDep {void localMethod() {innerDependentMethod();}}return new LocalInner();}}