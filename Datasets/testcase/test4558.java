package same.pkg;
private class SourceClass {private int sourceInstanceField = 20;
static class SourceStaticNested {}class SourceMemberInner {}
class SourceInner {TargetClass method(TargetClass.TargetInner targetInnerParam) {variableCall(targetInnerParam);access_instance_field();
TargetClass target = new TargetClass();TargetClass.StaticNested rawNested = new TargetClass.StaticNested(); // Raw type
label: {switch (sourceInstanceField) {case 20:targetInnerParam.innerMethod();break label;default:break;}}
return target;}
private void variableCall(TargetClass.TargetInner param) {String localVar = param.innerField;}
private void access_instance_field() {sourceInstanceField += 5;}}}
class TargetClass {String targetField = "targetValue";
class TargetInner {String innerField = "innerValue";void innerMethod() {}}
static class StaticNested<T> {T nestedGenericField;}}