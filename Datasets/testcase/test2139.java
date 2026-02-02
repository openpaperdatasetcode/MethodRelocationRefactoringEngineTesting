package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
record SourceRecord(int id) {class MemberInner {class InnerRecursive {private Object methodToMove(TargetRecord target) throws IOException {TargetRecord.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();
innerRec.variableCall();Object fieldVal = innerRec.dataField;
List<String> list1 = TargetRecord.MemberInner.InnerRecursive::overloadMethod;List<String> list2 = TargetRecord.MemberInner.InnerRecursive::overloadMethod;
return fieldVal;}}}
void createLocalInner() {class LocalInner {}}}
/**
Target record with Javadoc
Contains nested inner classes*/private record TargetRecord(String name) {class MemberInner {class InnerRecursive {Object dataField;
void variableCall() {}
private List<String> overloadMethod() {return new ArrayList<>();}
private List<String> overloadMethod(String param) {return new ArrayList<>();}}}}