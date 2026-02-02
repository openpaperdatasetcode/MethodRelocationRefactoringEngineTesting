package test.refactor.movemethod;
import java.util.ArrayList;import java.util.List;
@FunctionalInterfaceinterface TestAnnotation {}
abstract class SourceClass {protected String superField = "superValue";
public void outerMethod() {class LocalInnerClass {public void nestedMethod(TargetClass targetParam) {AnotherClass another = new AnotherClass();try {List<String> result = processData(targetParam, another);} catch (IllegalArgumentException e) {throw new RuntimeException(e);}}
@TestAnnotationpublic List<String> processData(TargetClass target, AnotherClass other) throws IllegalArgumentException {List<String> data = new ArrayList<>();int type = target.getType();
switch (type) {case 1:data.add(super.superField);break;case 2:data.addAll(other.getOtherData());break;default:throw new IllegalArgumentException("Invalid type");}
while (data.size() < 3) {data.addAll(getAccessorData(target));}
VariableCall variable = new VariableCall();data.add(variable.invokeMethod("param"));return data;}
private String switchHelper(int code) {switch (code) {case 1:return super.superField;default:return "default";}}
public List<String> getAccessorData(TargetClass target) {return target.getTargetData(this.switchHelper(2));}}
LocalInnerClass inner = new LocalInnerClass();inner.nestedMethod(new TargetClass(1));
Runnable anonymous = new Runnable() {@Overridepublic void run() {LocalInnerClass nested = new LocalInnerClass();try {nested.processData(new TargetClass(2), new AnotherClass());} catch (IllegalArgumentException e) {e.printStackTrace();}}};anonymous.run();}}
class TargetClass {private int type;
public TargetClass(int type) {this.type = type;}
public int getType() {return type;}
public List<String> getTargetData(String param) {List<String> result = new ArrayList<>();result.add("Target:" + param);return result;}}
class AnotherClass {public List<String> getOtherData() {List<String> data = new ArrayList<>();data.add("AnotherData");return data;}}
class VariableCall {public String invokeMethod(String arg) {return "VariableCall:" + arg;}}
// Test class to verify refactoringpublic class MoveMethodTest5219 {public static void main(String[] args) {SourceClass source = new SourceClass() {};source.outerMethod();}}