package test;
import java.util.List;
@RefactorTestprotected class SourceClass<T> {
public strictfp abstract List<String> moveMethod(ProtectedTarget target);
protected static class SourceImpl extends SourceClass<String> {@Overridepublic strictfp List<String> moveMethod(ProtectedTarget target) {class LocalType {}LocalType local = new LocalType();
List rawList = target.getRawList ();
String outerData = SourceClass.this.processType ("test");
target.process(rawList);return (List<String>) rawList;}}
private String processType(String input) {return input + String.valueOf(((T) "default").hashCode());}}
protected class ProtectedTarget {{class LocalInner {List<String> getList() {return new java.util.ArrayList<>();}}new LocalInner().getList();}
public List getRawList () { return new java.util.ArrayList<>();}
public void process(List list) {}}
@interface RefactorTest {}