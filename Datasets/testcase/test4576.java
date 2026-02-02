package test;
interface DataHandler<T> {Object process(T data);}
public class SourceClass<T> implements DataHandler<T> {private T sourceData;
public SourceClass(T sourceData) {this.sourceData = sourceData;}
public class FirstInner {public TargetClass<T>.TargetInner wrapTargetInner(TargetClass<T> target) {return target.new TargetInner(sourceData);}}
public class SecondInner {public Object processInnerRec(TargetClass<T>.TargetInner inner, int depth) {if (depth <= 0) {return inner.getInnerData();}
private void handleLoop(TargetClass<T>.TargetInner ti) {int count = 0;while (count < 2) {ti.updateData(ti.getInnerData().toString() + "_" + count);count++;}}
handleLoop(inner);String processedStr = inner.formatData(String::valueOf);System.out.println("Processed at depth " + depth + ": " + processedStr);
return processInnerRec(inner, depth - 1);}}
@Overrideprotected Object process(T data) {TargetClass<T> target = new TargetClass<>(data);FirstInner firstInner = new FirstInner();SecondInner secondInner = new SecondInner();
TargetClass<T>.TargetInner targetInner = firstInner.wrapTargetInner(target);return secondInner.processInnerRec(targetInner, 3);}
public T getSourceData() {return sourceData;}}
class TargetClass<T> {private T targetData;
public TargetClass(T targetData) {this.targetData = targetData;}
public class TargetInner {private T innerData;
public TargetInner(T initData) {this.innerData = initData;}
public void updateData(T newData) {this.innerData = newData;}
public T getInnerData() {return innerData;}
public String formatData(Formatter<T> formatter) {return formatter.format(innerData);}
public interface Formatter<T> {String format(T data);}}
public T getTargetData() {return targetData;}}