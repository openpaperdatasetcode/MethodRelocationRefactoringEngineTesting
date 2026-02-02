package source;
import java.util.List;import java.util.ArrayList;import target.TargetRecord;
public record SourceRecord<T>(T data) {public static class SourceStaticNested {public static void process(U value) {}
}
private static <T> void staticMethod(TargetRecord<T> targetParam) {if (targetParam == null) {return;}
List<T> dataList = new ArrayList<>();dataList.add(targetParam.value());int count = OthersClass.processCollection(dataList);
switch (count) {case 1:SourceStaticNested.process(targetParam.value());break;default:break;}
Runnable r = new Runnable() {@Overridepublic void run() {System.out.println(targetParam.value());}};r.run();}}
class OthersClass {private static <T> int processCollection(List<T> collection) {return new OthersClass().countElements(collection);}
private <T> int countElements(List<T> collection) {return collection.size();}}
package target;
import java.util.List;
record TargetRecord<T>(T value) implements ListProcessor<T> {@Overridepublic void processList(List<T> list) {class TargetLocalInner {void printListSize() {System.out.println(list.size());}}new TargetLocalInner().printListSize();}}
interface ListProcessor<T> {void processList(List<T> list);}