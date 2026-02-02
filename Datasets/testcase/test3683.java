package test;
non-sealed abstract class SourceClass {TargetClass instanceMethod(TargetClass target) {// Three LambdaExpressionsRunnable lambda1 = () -> System.out.println(target.field);Runnable lambda2 = () -> target.setField(target.field + "_mod1");Runnable lambda3 = () -> target.setField(target.field + "_mod2");
lambda1.run();lambda2.run();lambda3.run();
// ConstructorInvocation with this.field = 1TargetClass newTarget = new TargetClass(1);newTarget.setField(target.field);
return newTarget;}}
protected abstract class TargetClass extends ParentClass {String field;
public TargetClass(int fieldInit) {super();this.field = String.valueOf(fieldInit);}
public String getField() {return field;}
public void setField(String field) {this.field = field;}}
abstract class ParentClass {protected ParentClass() {}}