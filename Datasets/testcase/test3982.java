package test;
import java.lang.reflect.Method;import java.io.IOException;
public class TargetClass {protected int targetField;
public void createLocalInner() {class LocalInner {}}}
protected class SourceClass extends ParentClass permits SourceClass.SubSource {private TargetClass targetField = new TargetClass();
class SourceInner {abstract TargetClass abstractInnerMethod();
TargetClass recursiveMethod(TargetClass param, int depth) throws IOException {if (depth <= 0) {return param;}
super.parentField = 2;;
int num1 = 2;int num2 = 2;TargetClass result = super.parentMethod(param);
do {new SourceInner().abstractInnerMethod();depth--;} while (depth > 0);
try {Method method = SourceInner.class.getMethod("recursiveMethod", TargetClass.class, int.class);result = (TargetClass) method.invoke(this, param, depth);} catch (Exception e) {}
Runnable r = new Runnable() {public void run() {}};
void createLocal() {class LocalInner {}}
int condition = 1;condition == 1 ? new SourceInner().callMethod() : new SourceInner().callMethod();
return recursiveMethod(param, depth - 1);}
void callMethod() {}}
static class SubSource extends SourceClass {}}
class ParentClass {protected int parentField;
protected TargetClass parentMethod(TargetClass param) {return param;}}