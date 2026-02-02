package same.pkg;
import java.sql.SQLException;import java.util.ArrayList;import java.util.List;
class SourceClass {private TargetClass targetField = new TargetClass();
class SourceInner {class SourceInnerRec {private TargetClass.TargetInnerRec method() throws SQLException {variableCall();TargetClass.TargetInnerRec innerRec = targetField.new TargetInnerRec();
try {switch (1) {case 1:Object result = SourceClass.getStaticMethod().m2().m3();break;default:break;}
int i = 0;while (i < 1) {List<String> genericList = new ArrayList<>();genericList.forEach(str -> innerRec.genericMethod(str));i++;break;}} catch (Exception e) {}
return innerRec;}
private void variableCall() {}}}
void methodWithTwoLocals() {class LocalInner1 {}new LocalInner1();
class LocalInner2 {}new LocalInner2();}
protected static SourceIntermediate getStaticMethod() {return new SourceIntermediate();}
static class SourceIntermediate {SourceIntermediate2 m2() {return new SourceIntermediate2();}}
static class SourceIntermediate2 {Object m3() {return new Object();}}}
protected class TargetClass {class TargetInnerRec {<T> void genericMethod(T param) {}}}