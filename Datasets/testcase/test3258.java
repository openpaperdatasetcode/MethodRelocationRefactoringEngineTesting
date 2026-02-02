package test;
interface TestInterface {}
abstract class ParentClass {protected String superField = "parentField";public void methodToOverride(TargetClass.TargetInner inner) {}}
abstract class SourceClass extends ParentClass implements TestInterface {static class StaticNestedSource {}
@Overrideprotected void methodToOverride(TargetClass.TargetInner targetInnerParam) {class LocalInnerSource {}LocalInnerSource local = new LocalInnerSource();
privateDeclareVariable(targetInnerParam);
if (targetInnerParam != null) {targetInnerParam.doAction();System.out.println(super.superField); // Expression statement}
StaticNestedSource nested = new StaticNestedSource();nested.toString();}
private void privateDeclareVariable(TargetClass.TargetInner inner) {TargetClass.TargetInner var = inner;assert var != null : "Inner class instance must not be null";}}
public class TargetClass {class TargetInner {void doAction() {new Runnable() {@Overridepublic void run() {}};}}
void useMethod() {SourceClass source = new SourceClass() {};source.methodToOverride(new TargetInner());}}