import java.util.Arrays;import java.util.List;
class Container {private static class SourceClass {@ProcessingAnnotationpublic final Object normalMethod(TargetClass target) {if (target == null) {return null;}
TargetClass.InnerClass inner = target.new InnerClass();
new Runnable() {@Overridepublic void run() {expressionBlock: {inner.data = "init_from_anonymous1";}}}.run();
new Runnable() {@Overridepublic void run() {variableCall(inner);}}.run();
List<TargetClass.InnerClass> innerList = Arrays.asList(inner);innerList.forEach(SourceClass::processInFor);
return inner.data;}
private static void processInFor(TargetClass.InnerClass inner) {inner.appendData("_processed");}
private void variableCall(TargetClass.InnerClass inner) {inner.setData("updated_from_variableCall");}}
public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();source.normalMethod(target);}}
public class TargetClass {
public class InnerClass {String data;
public void setData(String data) {this.data = data;}
public void appendData(String suffix) {this.data += suffix;}}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface ProcessingAnnotation {}