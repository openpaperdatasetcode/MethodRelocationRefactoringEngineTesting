package test.same;
import java.sql.SQLException;
public class SourceClass {class FirstMemberInner {}class SecondMemberInner {}
<T, U> Object genericMethod(TargetClass target, T param) {class TypeDecl {U process(TargetClass.LocalInner.Rec rec) {return (U) rec.data;}}TypeDecl typeDecl = new TypeDecl();
TargetClass.LocalInner inner = target.getLocalInner();TargetClass.LocalInner.Rec rec = inner.new Rec();Object var = rec.data;
if (param == null) {return super.toString();}
int count = 0;do {int val = new TargetClass().protectedMethod(count);var = val;count++;} while (count < 2);
switch (count) {case 1:var = "one";break;case 2:var = "two";break;}
try {var = inner.instanceMethod(rec);if (var == null) {throw new SQLException("Method returned null");}} catch (SQLException e) {var = e.getMessage();}
return typeDecl.process(rec);}}
class TargetClass {LocalInner getLocalInner() {class LocalInner {record Rec() {Object data;}
Object instanceMethod(Rec rec) {return rec.data;}}return new LocalInner();}
protected int protectedMethod(int num) {return num * 2;}}