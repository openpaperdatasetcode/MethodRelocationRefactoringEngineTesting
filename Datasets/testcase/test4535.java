package same.pkg;
public enum SourceEnum permits ExtendedSourceEnum {INSTANCE;
class MemberInner {final Object recursiveMethod(TargetEnum.TargetInner.TargetInnerRec param) {variableCall(param);
if (param == null) {return null;}
class LocalInner {Object getValue() {return param.value;}}LocalInner local = new LocalInner();
TypeDeclaration typeDecl = new TypeDeclaration();return recursiveMethod(param.nextRec) != null ? typeDecl : local.getValue();}
private void variableCall(TargetEnum.TargetInner.TargetInnerRec param) {int localVar = param.count;}
class TypeDeclaration {}}}
enum ExtendedSourceEnum implements SomeInterface {}
interface SomeInterface {}
/**
Javadoc for TargetEnum*/final enum TargetEnum implements Runnable {INSTANCE;
class TargetInner {class TargetInnerRec {int count;Object value;TargetInnerRec nextRec;}}
@Overridepublic void run() {}}