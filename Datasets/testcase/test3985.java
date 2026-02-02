package test;
import java.sql.SQLException;
class SourceClass {private TargetClass.TargetStaticNested targetStaticField;
static class SourceStaticNested {}
@DeprecatedSourceClass(TargetClass target) throws SQLException {super();this.targetStaticField = target.new TargetStaticNested();
for (int i = 0; i < 3; i++) {String varCall = targetStaticField.getInnerValue();targetStaticField.printValue(varCall);}
target.accessInstanceMethod();}
void methodWithLocalClass() {class SourceLocalInner {}}}
private class TargetClass {static class TargetStaticNested {private String innerValue = "targetStaticInnerVal";
String getInnerValue() {return innerValue;}
void printValue(String val) {}}
void accessInstanceMethod() {}}