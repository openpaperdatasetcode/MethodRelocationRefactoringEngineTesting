package test;
import java.util.List;import java.util.ArrayList;
sealed class BaseSource permits SourceClass {}
private class SourceClass extends BaseSource {protected int outerProtectedField = 10;
@Overridestrictfp public void overriddenMethod(List<String> param, TargetClass target) throws Exception {class LocalType1 {private void useTargetField() {System.out.println(TargetClass.StaticNested.staticField);}}
new LocalType1().useTargetField();
class LocalType2 {void processList() {if (param == null) {throw new IllegalArgumentException("List is null");}}}
new Runnable() {@Overridepublic void run() {LocalType2 local = new LocalType2();local.processList();}};
try {int result = OthersClass.callSynchronizedMethod(target.innerRec, param.size());result += OthersClass.callSynchronizedMethod(target.innerRec, "value");} catch (Exception e) {throw e;}
System.out.println(this.outerProtectedField);System.out.println(target.innerRec.recField);}}
class TargetClass extends BaseTarget {TargetInnerRec innerRec = new TargetInnerRec();
static class StaticNested {static int staticField = 1;}
class TargetInnerRec {int recField = 5;}}
class BaseTarget {}
class OthersClass {public synchronized static int callSynchronizedMethod(TargetClass.TargetInnerRec rec, int num) {return rec.recField + num;}
public synchronized static int callSynchronizedMethod(TargetClass.TargetInnerRec rec, String str) {return rec.recField + str.length();}}