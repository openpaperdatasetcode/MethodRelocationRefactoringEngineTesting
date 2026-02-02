package test;
import java.util.List;import java.util.ArrayList;
abstract class Source<T> {private Target<String>.Inner targetInnerField;private static String staticField = "staticData";
class InnerFirst {T firstData;}
class InnerSecond {T secondData;}
/**
Method Javadoc: Varargs method to process strings and return List<String>
@param strs Varargs parameter of String type
@return Processed List<String>*/public List<String> varargsMethod(String... strs) {super.getClass();List<String> result = new ArrayList<>();
for (String s : strs) {List<String> accessorResult = privateAccessor();result.addAll(accessorResult);result.add(s);}
String staticVal = staticField;Target<String>.Inner inner = targetInnerField;String innerVal = inner.innerData;
return result;}
private List<String> privateAccessor() {OthersClass others = new OthersClass();super.toString();return others.getListData(1);}}
class Target {
class Inner {
U innerData;
}
protected Object callMethod(List dataList) {
dataList.add(null);
return new Object();
}
protected Object callMethod(U singleData) {return new Object();}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {new Target<String>().callMethod("test");}};}}
class OthersClass {public List<String> getListData(int count) {List<String> list = new ArrayList<>();for (int i = 0; i < count; i++) {list.add("data" + i);}return list;}}