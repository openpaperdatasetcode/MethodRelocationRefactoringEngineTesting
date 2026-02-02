package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
class SourceClass {private List<String> data;
public List<String> getData(TargetClass targetParam) throws IOException {TargetClass.InnerClass inner = targetParam.new InnerClass();Runnable r = () -> inner.instanceMethod();
List<String> list;list = targetParam.getList();return data;}
public void setData(List<String> data) {this.data = data;}}
non-sealed class TargetClass implements MyInterface {private List<String> list = new ArrayList<>();
class InnerClass {public TargetClass instanceMethod() {class LocalInner {}return TargetClass.this;}}
public List<String> getList() {return list;}}
interface MyInterface {}