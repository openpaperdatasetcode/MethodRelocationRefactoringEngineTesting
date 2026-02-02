package test;
import java.util.ArrayList;import java.util.List;
class ParentClass {}
class SourceClass extends ParentClass {class MemberInner {class InnerRec {protected List<String> moveMethod(TargetClass.Inner... params) {Object anonymous = new Object() {};
TargetClass.Inner targetParam = params[0];List<String> list = new ArrayList<>();list.add(targetParam.value);
variableCall(list);return list;}
private void variableCall(List<String> l) {}}}}
class TargetClass {class Inner {String value;}
Object anonymous = new Object() {};}