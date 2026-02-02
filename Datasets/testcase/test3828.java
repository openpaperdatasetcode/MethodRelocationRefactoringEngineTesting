package samepkg;
interface ParentInterface {int superField = 1;}
interface SourceInterface {class InnerClass {@Deprecatedprivate Object varargsMethod(ProtectedTargetInterface... targets) {ProtectedTargetInterface.TargetStaticNested nested = new ProtectedTargetInterface.TargetStaticNested();
if (ParentInterface.superField == 1) {nested.nestedField = ParentInterface.superField;}
switch (targets.length) {case 0:return null;default:ProtectedTargetInterface varCall = targets[0];return varCall;}}}}
protected interface ProtectedTargetInterface extends ParentInterface {static class TargetStaticNested {int nestedField;}}