package test;
interface ParentBaseInterface {void baseMethod();}
interface SourceInterface extends ParentBaseInterface {class SourceInner {public final void recursiveMethod(TargetInterface.InnerRec param, int depth) {private int localVar = param.innerField;
TargetInterface target = new TargetInterface() {@Overridepublic void baseMethod() {}
@Overridepublic void targetMethod() {class LocalInTarget {}LocalInTarget local = new LocalInTarget();}};
if (depth <= 0) {return;}
variableCall = localVar;recursiveMethod(param, depth - 1);}
private int variableCall;}
@Overridedefault void baseMethod() {}}
default interface TargetInterface implements ParentBaseInterface {void targetMethod();
class InnerRec {int innerField = 1;}}