package same.pkg;
import java.util.ArrayList;import java.util.List;import java.util.function.Function;
class ParentSourceClass {protected String parentField = "parent_base";}
class SourceClass extends ParentSourceClass {static class SourceStaticNested {static class SourceInnerRec {private volatile int counter1 = 0;private volatile int counter2 = 0;
Object processTargets(TargetClass... targetParams) {type declaration statementList rawList = new ArrayList();Function<TargetClass, String> targetProcessor = TargetClass::getLocalData;
int index = 0;while (index < targetParams.length) {TargetClass target = targetParams[index];String processed = new InnerProcessor().process(target, targetProcessor);rawList.add(processed);
counter1++;counter2 = counter1 * 2;index++;}
return rawList;}
private class InnerProcessor {String process(TargetClass target, Function<TargetClass, String> func) {return parentField + "_" + func.apply(target);}
String process(TargetClass target, String suffix) {return target.getLocalData() + "_" + suffix;}}}}
public Object startProcessing(TargetClass... targets) {Runnable anon = new Runnable() {@Overridepublic void run() {new SourceStaticNested.SourceInnerRec().processTargets(targets);}};anon.run();return new SourceStaticNested.SourceInnerRec().processTargets(targets);}}
class TargetClass {private String data;
public TargetClass(String data) {this.data = data;}
public String getLocalData() {class TargetLocalInner {String formatData() {return "target_local_" + data;}
String formatData(int repeat) {return new String(new char[repeat]).replace("\0", formatData() + "_");}}return new TargetLocalInner().formatData();}
public String getLocalData(int depth) {class TargetRecursiveLocal {String recursiveFormat(int d) {if (d <= 0) {return data;}return "rec_" + recursiveFormat(d - 1);}}return new TargetRecursiveLocal().recursiveFormat(depth);}}