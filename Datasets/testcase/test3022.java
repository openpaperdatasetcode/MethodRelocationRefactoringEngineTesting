import java.util.ArrayList;import java.util.List;
interface DataProcessor {List<String> processData();}
non-sealed abstract class SourceClass<T> implements DataProcessor {protected TargetClass targetField;
static class StaticNested<T> {void nestedMethod(T target) {}}
@Overridepublic List<String> processData() {List<String> result = new ArrayList<>();
class LocalInner {void addData(String data) {result.add(data);}}LocalInner local = new LocalInner();
for (String item : targetField.getDataSource()) {local.addData(item);targetField.updateCount();}
return result;}}
public abstract class TargetClass {private List<String> dataSource = new ArrayList<>();private int count = 0;
public List<String> getDataSource() {return dataSource;}
public void updateCount() {count++;Runnable r = new Runnable() {@Overridepublic void run() {System.out.println("Count updated: " + count);}};r.run();}}