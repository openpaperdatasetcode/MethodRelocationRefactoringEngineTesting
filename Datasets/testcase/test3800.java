package test;
import diffpackage.OthersClass;import java.util.Arrays;import java.util.List;
public class SourceClass {class SourceMemberInner {}
public TargetClass normalMethod (TargetClass target) {
List<TargetClass> targetList = Arrays.asList (target, new TargetClass ());for (TargetClass t : targetList) {
String varCall = t.targetField;if (varCall.contains ("break")) {break;}}
OthersClass others = new OthersClass (target.targetField);String othersField = others.getOthersField ();
TargetGeneric rawTargetGen = new TargetGeneric ();rawTargetGen.setData (target.targetField);
TargetClass newTarget = new TargetClass ();newTarget.targetField = target.targetField + "_new";return newTarget;}
void methodWithLocalClass () {
class SourceLocalInner {void printTargetField (TargetClass target) {System.out.println (target.targetField);}}}}
public class TargetClass {String targetField = "targetInstanceVal";}

package diffpackage;
public class OthersClass {private String othersField;
public OthersClass (String field) {this.othersField = field;}
public String getOthersField() {return othersField;}}
class TargetGeneric<T> {private T data;
public void setData(T data) {this.data = data;}
public T getData() {return data;}}