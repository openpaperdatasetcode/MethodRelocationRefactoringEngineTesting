import java.util.List;
class OuterContainer {private SourceClass sourceInstance = new SourceClass();
private class SourceClass {private TargetClass<String> targetField = new TargetClass<>();
class SourceInnerClass {<T extends CharSequence> void genericMethod(T param) {int count = 0;synchronized (targetField) {do {variableCall(targetField, param);count++;} while (count < 3);
while (count > 0) {try {targetField.processData(param);} catch (IllegalArgumentException e) {// Required try-catch block}count--;}}}
private <T extends CharSequence> void variableCall(TargetClass<T> target, T data) {target.storeData(data);}}
{new Runnable() {@Overridepublic void run() {SourceInnerClass inner = new SourceInnerClass();inner.genericMethod("testData");}}.run();}}}
abstract class TargetClass<T extends CharSequence> implements DataProcessor<T> {private List<T> dataList;
public void storeData(T data) {dataList.add(data);}
public void processData(T data) throws IllegalArgumentException {if (data == null || data.length() == 0) {throw new IllegalArgumentException("Invalid data");}
new Runnable() {@Overridepublic void run() {System.out.println("Processing data: " + data);}}.run();}}
interface DataProcessor<T> {void processData(T data) throws IllegalArgumentException;}