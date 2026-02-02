package test;
import java.util.List;import java.util.ArrayList;
@interface SourceAnnotation {class MemberInner {public List<String> collectData(String input) {List<String> dataList = new ArrayList<>();dataList.add("Collected: " + input);return dataList;}}
public int recursiveProcess(TargetAnnotation<?> target, int depth) {if (depth <= 0) {return target.getInnerValue();}
new MemberInner() {{List<String> processedList = collectData(target.getInnerData().toString());System.out.println("Instance block data: " + processedList);}};
class LocalHandler {public int handleCharCheck(char c) {switch (c) {case 'A':case 'B':return 10;default:return 5;}}}
LocalHandler handler = new LocalHandler();int charBonus = handler.handleCharCheck(target.getInnerData().toString().charAt(0));
TargetAnnotation<?> nextTarget = target.createNext();int total = target.getInnerValue() + charBonus + recursiveProcess(nextTarget, depth - 1);
return total;}}
private interface TargetAnnotation<T> {T getInnerData();int getInnerValue();TargetAnnotation<T> createNext();
class TargetInner {private T data;private int value;
public TargetInner(T data, int value) {super();this.data = data;this.value = value;}
public T getData() {return data;}
public int getValue() {return value;}}
default TargetAnnotation<T> createNext() {return new TargetAnnotation<T>() {private TargetInner inner = new TargetInner((T) ("Next_" + getInnerData()), getInnerValue() + 1);
@Overridepublic T getInnerData() {return inner.getData();}
@Overridepublic int getInnerValue() {return inner.getValue();}
@Overridepublic TargetAnnotation<T> createNext() {return SourceAnnotation.this.recursiveProcess(this, 1) > 15? new TargetAnnotation<T>() {@Overridepublic T getInnerData() {return (T) ("Final_" + inner.getData());}
@Overridepublic int getInnerValue() {return inner.getValue() + 2;}
@Overridepublic TargetAnnotation<T> createNext() {return this;}}: this;}};}}