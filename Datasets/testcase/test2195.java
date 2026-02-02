package test;
import java.util.ArrayList;import java.util.List;import java.io.IOException;
public abstract class SourceClass {protected String outerProtectedField;
class MemberInner {class RecursiveInner {List<String> moveMethod(TargetClass target) throws IOException {super();List<String> result = new ArrayList<>();TargetClass.Inner targetInner = new TargetClass().new Inner();
if (target.field == null) {throw new IOException("Target field is null");}
result.add(target.field);result.add(outerProtectedField);targetInner.method();
return result;}}}
{new Runnable() {public void run() {}};}}
abstract class TargetClass {String field;
class Inner {void method() {}}}