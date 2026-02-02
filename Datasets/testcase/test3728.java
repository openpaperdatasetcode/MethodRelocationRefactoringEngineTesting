import java.util.Objects;
protected class SourceClass extends ParentClass {private String outerField = "source_outer_data";
public static class StaticNested {public <T> void helper(TargetClass<T> target) {target.staticNested.setData("helper_init");}}
public class MemberInner {public <T> TargetClass<T> process(TargetClass<T> target) {typeDeclaration: {class TypeDeclaration {void updateTarget(TargetClass<T> t) {expressionStatement: {t.setTempData(SourceClass.this.outerField);}variableCall(t);}}new TypeDeclaration().updateTarget(target);}return target;}}
@Overridepublic <T> TargetClass<T> overridingMethod(TargetClass<T> target) {StaticNested.helper(target);
super.parentMethod1(target);super.parentMethod2(target.staticNested);
MemberInner inner = new MemberInner();TargetClass<T> processedTarget = inner.process(target);
String targetData = processedTarget.getTempData();processedTarget.staticNested.appendData(targetData + "_suffix");
return processedTarget;}
private <T> void variableCall(TargetClass<T> target) {target.updateCount();}}
abstract class ParentClass {protected <T> void parentMethod1(TargetClass<T> target) {}protected <T> void parentMethod2(TargetClass.StaticNested<T> nested) {}public abstract <T> TargetClass<T> overridingMethod(TargetClass<T> target);}
class TargetClass<T> {private String tempData;private int count;public StaticNested<T> staticNested = new StaticNested<>();
public void setTempData(String data) {this.tempData = data;}
public String getTempData() {return tempData;}
public void updateCount() {this.count++;}
public int getCount() {return count;}
public static class StaticNested {
private U data;
public void setData(U data) {this.data = data;}
public U getData() {return data;}
public void appendData(String suffix) {if (data instanceof String) {data = (U) ((String) data + suffix);}}}}