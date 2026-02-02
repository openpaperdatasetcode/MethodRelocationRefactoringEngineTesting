package same;
import java.util.ArrayList;import java.util.List;
private class SourceClass1<T> {class MemberInner {@Deprecatedprivate TargetClass process(TargetClass target, String... args) {class LocalHandler {}LocalHandler handler = new LocalHandler();
raw_type List items = new ArrayList();for (String arg : args) {this.handleItem(target, arg);items.add(arg);}
switch (target.getType()) {case "TYPE_A":target.setValue(args[0]);break;case "TYPE_B":target.setValue(args[1]);break;default:target.setValue("default");}
@SuppressWarnings("unchecked")T data = (T) target.getData();return target;}
private void handleItem(TargetClass target, String item) {target.addToHistory(item);}
private void handleItem(TargetClass target, Integer item) {target.addToHistory(item.toString());}}}
package same;
import java.util.List;import java.util.ArrayList;
abstract class TargetClass {/**
Represents the type of target
/
private String type;
/*
Stores historical data
*/
private List<String> history = new ArrayList<>();
public abstract String getType();
public abstract void setValue(String value);
public abstract Object getData();
public void addToHistory(String item) {history.add(item);}}

package same;
import java.util.List;import java.util.ArrayList;
private class SourceClass2 {protected int outerProtected = 100;static class StaticNested {}
{Runnable anon = new Runnable() {public void run() {}};}
protected List<String> process(TargetClass2 target) {List<String> result = new ArrayList<>();labeledLoop: for (int i = 0; i < 5; i++) {if (i == 3) break labeledLoop;result.add(process(target, i));}
raw_type List rawList = target.getRawData();return result;}
protected List<String> process(TargetClass2 target, int index) {List<String> list = new ArrayList<>();try {String data;if (index % 2 == 0) {data = target.fetchData();} else {data = target.fetchData(outerProtected);}list.add(data);} catch (Exception e) {e.printStackTrace();}return list;}}
package same;
import java.util.List;
public class TargetClass2 {class Inner {String format(String s) {return super.toString() + s;}}
private Inner inner = new Inner();
public String fetchData() {return inner.format("default");}
public String fetchData(int param) {return inner.format(String.valueOf(param));}
public List getRawData() {return List.of("raw1", "raw2");}}