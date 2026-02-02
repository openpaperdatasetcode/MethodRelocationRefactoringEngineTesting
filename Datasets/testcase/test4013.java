package test;
public class SourceClass<T> {private T sourceField;static String sourceStaticField = "sourceStaticVal";
class SourceMemberInner {}
protected TargetClass<T> recursiveMethod(TargetClass<T> target, int depth) {if (depth <= 0) {return target;}
TypeDeclared typeDecl = new TypeDeclared();; // Empty statement
if (target != null) {String varCall = target.targetField.toString();String staticAccess = SourceClass.this.sourceStaticField;
switch (depth) {case 1:target.targetField = sourceField;break;case 2:target.new TargetInner().innerMethod();break;default:break;}}
// Raw type usageTargetClass rawTarget = new TargetClass<>();rawTarget.targetField = target.targetField;
return recursiveMethod(target, depth - 1);}
void anonymousClassDemo() {Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(SourceClass.this.sourceStaticField);}};}}
class TargetClass<T> {T targetField;
class TargetInner {void innerMethod() {}}}
class TypeDeclared {}