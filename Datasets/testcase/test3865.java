package test.refactoring;
import java.util.ArrayList;import java.util.List;
interface Processable {Object handleTarget (TargetClass target, String... args);}
class ParentClass {
public static int staticProcess (TargetClass.TargetInner inner, java.util.function.Function<TargetClass.TargetInner, Integer> mapper) {return mapper.apply (inner);}}
protected class SourceClass extends ParentClass implements Processable {protected String outerProtected = "source_protected_data";
private Object thisVar;
private TargetClass targetField = new TargetClass ();
class SourceMemberInner {void invokeSourceMethod (String... args) {processTarget (targetField, args);}}
TargetClass recursiveResult = recursiveCall (targetField, 3);
thisVar = recursiveResult; }
public void createLocalInner (TargetClass target, String... args) {class SourceLocalInner {Object useVarargsMethod () {return processTarget (target, args);}}new SourceLocalInner ().useVarargsMethod ();}
@Overridepublic Object processTarget (TargetClass target, String... args) {super.toString (); TargetClass.TargetInner targetInner = target.new TargetInner ();targetInner.setInnerData (outerProtected); 
for (String arg : args) {variableCall (targetInner, arg);}
int callResult = ParentClass.staticProcess (targetInner, (inner) -> {try {
if (inner.getInnerData () == null) {throw new IllegalArgumentException ("Inner data is null");}return inner.getInnerData ().length ();} catch (Exception e) {return -1;}});
thisVar = callResult; return target;}
private TargetClass recursiveCall (TargetClass target, int depth) {if (depth <= 0) {return target;}OthersClass.handleTargetData (target, "recursion_" + depth);
return recursiveCall (target, depth - 1);}
private void variableCall (TargetClass.TargetInner inner, String data) {inner.setInnerData (inner.getInnerData () + "_" + data);}}
class TargetClass {
public class TargetInner {private String innerData;
public String getInnerData() {return innerData;}
public void setInnerData(String innerData) {this.innerData = innerData;}}}
class OthersClass {public static void handleTargetData (TargetClass target, String data) {TargetClass.TargetInner inner = target.new TargetInner ();inner.setInnerData (data);}}