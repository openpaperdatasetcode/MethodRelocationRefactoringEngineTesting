package same.pkg;
import java.util.ArrayList;import java.util.List;
class ParentSourceClass {protected String parentProtectedField = "parent_protected_data";}
class SourceClass extends ParentSourceClass {private class SourceInnerRec {private Object processTargetVarargs(TargetClass... targetParams) {List rawList = new ArrayList();
for (TargetClass target : targetParams) {TargetClass.TargetLocalRec innerRec = target.new TargetLocalRec(3);rawList.add(innerRec.recursiveGetData());
rawList.add(super.parentProtectedField);rawList.add(SourceClass.this.getClass().getSimpleName());}
switch (rawList.size() % 3) {case 0:OthersClass.finalStaticProcess(rawList);break;case 1:TargetClass sampleTarget = targetParams[0];OthersClass.finalStaticProcess(sampleTarget.new TargetLocalRec(1).recursiveGetData());break;default:OthersClass.finalStaticProcess("default_process");break;}
return rawList;}}
public Object startVarargsProcessing(TargetClass... targets) {return new SourceInnerRec().processTargetVarargs(targets);}}
public class TargetClass {private String targetData = "target_base_data";
public class TargetLocalRec {private int depth;
public TargetLocalRec(int depth) {this.depth = depth;}
public String recursiveGetData() {if (depth <= 0) {return targetData;}depth--;return "rec_" + recursiveGetData();}}}
class OthersClass {public static final void finalStaticProcess(Object data) {System.out.println("Final static processing: " + data);}}