package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface ProcessTargetAnnotation {}
public class SourceClass {class SourceInner {void useProcessMethod() {TargetClass target = new TargetClass();TargetClass.TargetInnerRec innerRec = target.new TargetInnerRec("rec-001");List<String> result = process(innerRec);}}
void initAnonymous() {Runnable anonTask = new Runnable() {@Overridepublic void run() {TargetClass.TargetInnerRec innerRec = new TargetClass().new TargetInnerRec("rec-002");process(innerRec);}};anonTask.run();}
@ProcessTargetAnnotationpublic List<String> process(TargetClass.TargetInnerRec innerRec) {List<String> dataList = new ArrayList<>();String[] sourceData = {"a", "b", null, "c", "d"};
for (String data : sourceData) {if (data == null) {continue;}innerRec.addItem(data);dataList.add(innerRec.getLastItem());}
if (dataList.size() > 3) {return dataList.subList(0, 3);}return dataList;}}
class TargetClass {class TargetInnerRec {private final String id;private final List<String> items = new ArrayList<>();
public TargetInnerRec(String id) {this.id = id;}
public void addItem(String item) {items.add(item);}
public String getLastItem() {if (items.isEmpty()) {return "";}return items.get(items.size() - 1);}}}